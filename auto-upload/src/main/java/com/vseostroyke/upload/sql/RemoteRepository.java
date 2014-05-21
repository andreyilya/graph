package com.vseostroyke.upload.sql;

import com.vseostroyke.upload.http.ContentItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class RemoteRepository {

    public void generateSql(ContentItem contentItem, String path){

    }
    public void saveContent(ContentItem contentItem){
//        String login = "krdraki756_29847";//Логин пользователя
//        String password = "ZRQeb2Bf4h";//Пароль пользователя
//   //     String url = "jdbc:mysql://kryshi.vseostroyke.by:3306/nayfke3756_29847";//URL адрес
//        String url = "jdbc:mysql://k29.hostenko.com:3306/nayfke3756_29847";//URL адрес
//        String driver = "com.mysql.jdbc.Driver";//Имя драйвера
//        try {
//            Class.forName(driver);//Регистрируем драйвер
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection c = null;//Соединение с БД
//        try{
//            c = DriverManager.getConnection(url, login, password);//Установка соединения с БД
//            Statement st = c.createStatement();//Готовим запрос
//            ResultSet rs = st.executeQuery("select * from wp_posts where id=1");//Выполняем запрос к БД, результат в переменной rs
//            while(rs.next()){
//                System.out.println(rs.getString("id"));//Последовательно для каждой строки выводим значение из колонки ColumnName
//            }
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            //Обязательно необходимо закрыть соединение
//            try {
//                if(c != null)
//                    c.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
