(function ($) {
    var _mouseP;
    var particleSystem;
    var from;
    var to;
    var Renderer = function (canvas) {
        var canvas = $(canvas).get(0);
        var ctx = canvas.getContext("2d");
        var w = 10;			//ширина квадрата
        var that = {
            init: function (system) {
                //начальная инициализация
                particleSystem = system;
                particleSystem.screenSize(canvas.width, canvas.height);
                particleSystem.screenPadding(80);
                that.initMouseHandling();
            },

            redraw: function () {
                //действия при перересовке
                ctx.fillStyle = "white";	//белым цветом
                ctx.fillRect(0, 0, canvas.width, canvas.height); //закрашиваем всю область
                if (particleSystem.parameters().friction < 1) {

                    particleSystem.parameters({friction: particleSystem.parameters().friction + 0.0001}); // гравитация вкл
                }

                var nodeBoxes = {};
                particleSystem.eachNode(	//теперь каждую вершину
                    function (node, pt) {		//получаем вершину и точку где она
                        if (node.data['color'] != null) {
                            ctx.fillStyle = node.data['color'];
                        } else {
                            ctx.fillStyle = "orange";	//с его цветом понятно
                        }
                        ctx.fillRect(pt.x - w / 2, pt.y - w / 2, w, w);	//рисуем
                        ctx.fillStyle = "black";	//цвет для шрифта
                        ctx.font = 'italic 13px sans-serif'; //шрифт
                        ctx.fillText(node.data.name, pt.x + 8, pt.y + 8); //пишем имя у каждой точки
                        nodeBoxes[node.name] = [pt.x - w / 2, pt.y - w / 2, w, w];
                    });

                particleSystem.eachEdge(	//отрисуем каждую грань
                    function (edge, pt1, pt2) {	//будем работать с гранями и точками её начала и конца
                        ctx.strokeStyle = "rgba(5,5,5, .333)";	//грани будут чёрным цветом с некой прозрачностью
                        ctx.lineWidth = 1;	//толщиной в один пиксель
                        ctx.beginPath();		//начинаем рисовать
                        // find the start point

                        var tail = intersect_line_box(pt1, pt2, nodeBoxes[edge.source.name]);
                        var head = intersect_line_box(tail, pt2, nodeBoxes[edge.target.name]);
                        ctx.moveTo(tail.x, tail.y); //от точки один
                        ctx.lineTo(head.x, head.y); //до точки два
                        ctx.stroke();

                        ctx.fillStyle = "black";
                        ctx.font = 'italic 11px sans-serif';
                        ctx.fillText(edge.data.roadLength + " км", (pt1.x + pt2.x) / 2, (pt1.y + pt2.y) / 2);

                        // if (edge.data.directed){   //direction here
                        var weight = edge.data.weight;

                        ctx.save();
                        // move to the head position of the edge we just drew
                        var wt = !isNaN(weight) ? parseFloat(weight) : 1;
                        var arrowLength = 8 + wt;
                        var arrowWidth = 3 + wt;
                        ctx.fillStyle = "#cccccc";
                        ctx.translate(head.x, head.y);
                        ctx.rotate(Math.atan2(head.y - tail.y, head.x - tail.x));

                        // delete some of the edge that's already there (so the point isn't hidden)
                        ctx.clearRect(-arrowLength / 2, -wt / 2, arrowLength / 2, wt)

                        // draw the chevron
                        ctx.beginPath();
                        ctx.moveTo(-arrowLength, arrowWidth);
                        ctx.lineTo(0, 0);
                        ctx.lineTo(-arrowLength, -arrowWidth);
                        ctx.lineTo(-arrowLength * 0.8, -0);
                        ctx.closePath();
                        ctx.fill();
                        ctx.restore();
//                              }
                    });

            },

            initMouseHandling: function () {	//события с мышью
                var dragged = null;			//вершина которую перемещают
                var relation = false;
                var handler = {
                    clicked: function (e) {	//нажали
                        var pos = $(canvas).offset();	//получаем позицию canvas
                        _mouseP = arbor.Point(e.pageX - pos.left, e.pageY - pos.top); //и позицию нажатия кнопки относительно canvas
                        dragged = particleSystem.nearest(_mouseP);	//определяем ближайшую вершину к нажатию

                        //context menu here
                        $("canvas").contextMenu('contextMenu', {
                            bindings: {
                                'createNode': function (t) {
                                    $("#dialog").dialog("open");
                                },
                                'deleteNode': function (t) {
                                    $.post("delete-node/" + dragged.node.name, function () {
                                        sys.pruneNode(dragged.node);
                                    });
                                },

                                'from': function (t) {
                                    from = dragged.node.name;
                                    createRoute(from, to);
                                },
                                'to': function (t) {
                                    to = dragged.node.name;
                                    createRoute(from, to);
                                }

                            }, onShowMenu: function (e, menu) {
                                if (dragged.distance < w) {
                                    $('#createNode', menu).remove();
                                } else {
                                    $('#deleteNode', menu).remove();
                                    $('#from', menu).remove();
                                    $('#to', menu).remove();
                                }

                                return menu;

                            }

                        });

                        if (e.button == 0) {
                            if (dragged.distance < w) {
                                if (dragged && dragged.node !== null) {
                                    dragged.node.fixed = true;	//фиксируем её
                                }

                                $(canvas).bind('mousemove', handler.dragged);	//слушаем события перемещения мыши
                                $(window).bind('mouseup', handler.dropped);		//и отпускания кнопки
                            }
                        } else if (e.button == 2) {
                            if (dragged.distance < w) {
                                //TODO  relations drag/drop
                                relation = true;
                                $(window).bind('mouseup', handler.dropped);
                            }
                        }
                        return false;
                    },
                    dragged: function (e) {	//перетаскиваем вершину
                        var pos = $(canvas).offset();
                        var s = arbor.Point(e.pageX - pos.left, e.pageY - pos.top);

                        if (dragged && dragged.node !== null) {
                            var p = particleSystem.fromScreen(s);
                            dragged.node.p = p;	//тянем вершину за нажатой мышью
                        }

                        return false;
                    },
                    dropped: function (e) {	//отпустили
                        if (e.button == 0) {
                            if (dragged === null || dragged.node === undefined) {
                                return;
                            }	//если не перемещали, то уходим
                            if (dragged.node !== null) {
                                dragged.node.fixed = false;
                            }	//если перемещали - отпускаем
                            dragged = null; //очищаем
                            $(canvas).unbind('mousemove', handler.dragged); //перестаём слушать события
                            $(window).unbind('mouseup', handler.dropped);
                            _mouseP = null;
                            return false;
                        } else if (e.button == 2) {
                            var pos2 = $(canvas).offset();	//получаем позицию canvas
                            _mouseP2 = arbor.Point(e.pageX - pos2.left, e.pageY - pos2.top); //и позицию нажатия кнопки относительно canvas
                            dropped = particleSystem.nearest(_mouseP2);	//определяем ближайшую вершину к нажатию

                            if (dropped.distance < w && dragged.node.name != dropped.node.name) {
                                //TODO: lenght, direction
                                $.post("add-edge", "sourceCity=" + dragged.node.name + "&targetCity=" + dropped.node.name + "&roadLength=1", function (data) {
                                    //get from data
                                    sys.addEdge(data.sourceCity, data.targetCity, {"roadLength": data.roadLength});
                                }, 'json');
                            }
                            relation = false;
                            $(window).unbind('mouseup', handler.dropped);


                        }
                        return false;
                    }

                };
                // слушаем события нажатия мыши
                $(canvas).mousedown(handler.clicked);
            }

        };

        function createRoute(from, to) {
            if (to != null && to != null) {
                $.get("route-data/" + from + "/" + to, function (data) {
                    sys.eachNode(function (node) {
                        node.data["color"] = "orange";
                    });

                    sys.eachEdge(function (edge) {
                        edge.data["color"] = "black";
                    });

                    $.each(data.nodes, function (i, node) {
                        sys.getNode(node.id).data["color"] = "#0000ff";
                    });

                    //TODO: color to edge
//                    $.each(data.edges, function (i, edge) {
//                        sys.getEdge(edge.id).data["color"] = "#0000ff";
//                    });
                    that.redraw();

                });
                from = null;
                to = null;
            }
        }

        return that;
    };

    $(document).ready(function () {
        $("#queryGraphButton").bind('click', function () {
            $("#viewport")
                .attr('width', $("#graphContent").width())
                .attr('height', $(window).height());
            sys = arbor.ParticleSystem(1000); // создаём систему
            sys.parameters({gravity: false, friction: 0.98}); // гравитация вкл
            sys.renderer = Renderer("#viewport"); //начинаем рисовать в выбраной области

            $.getJSON("graph-data/" + $("#target").val() + "/" + $("#depth").val(),	//получаем с сервера файл с данными
                function (data) {
                    $.each(data.nodes, function (i, node) {
                        sys.addNode(node.id, {"name": node.name});	//добавляем вершину
                    });

                    $.each(data.edges, function (i, edge) {
                        if (edge.sourceCity != null && edge.targetCity != null) {
                            if (sys.getNode(edge.sourceCity) != null && sys.getNode(edge.targetCity) != null) {
                                sys.addEdge(sys.getNode(edge.sourceCity), sys.getNode(edge.targetCity), {"roadLength": edge.roadLength});	//добавляем грань
                            }
                        }
                    });
                });

        });
        $(function () {
            //TODO: unbind context menu
            $("#dialog").dialog({ autoOpen: false });
        });

        $("#createCity").click({_mouseP: _mouseP}, function () {

            $.post("add-node", $("#createCityForm").serialize(), function (data) {
                sys.addNode(data.id, {"name": data.name}).p = particleSystem.fromScreen(_mouseP);
                $("#dialog").dialog("close");
            }, 'json');

        });

    });

    // helpers for figuring out where to draw arrows (thanks springy.js)
    var intersect_line_line = function (p1, p2, p3, p4) {
        var denom = ((p4.y - p3.y) * (p2.x - p1.x) - (p4.x - p3.x) * (p2.y - p1.y));
        if (denom === 0) return false; // lines are parallel
        var ua = ((p4.x - p3.x) * (p1.y - p3.y) - (p4.y - p3.y) * (p1.x - p3.x)) / denom;
        var ub = ((p2.x - p1.x) * (p1.y - p3.y) - (p2.y - p1.y) * (p1.x - p3.x)) / denom;

        if (ua < 0 || ua > 1 || ub < 0 || ub > 1)  return false;
        return arbor.Point(p1.x + ua * (p2.x - p1.x), p1.y + ua * (p2.y - p1.y));
    };

    var intersect_line_box = function (p1, p2, boxTuple) {
        var p3 = {x: boxTuple[0], y: boxTuple[1]},
            w = boxTuple[2],
            h = boxTuple[3]

        var tl = {x: p3.x, y: p3.y};
        var tr = {x: p3.x + w, y: p3.y};
        var bl = {x: p3.x, y: p3.y + h};
        var br = {x: p3.x + w, y: p3.y + h};

        return intersect_line_line(p1, p2, tl, tr) ||
            intersect_line_line(p1, p2, tr, br) ||
            intersect_line_line(p1, p2, br, bl) ||
            intersect_line_line(p1, p2, bl, tl) ||
            false
    };

})(this.jQuery);
