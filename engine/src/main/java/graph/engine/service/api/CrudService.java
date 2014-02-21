package graph.engine.service.api;

/**
 * User: a.radkov
 * Date: 21.02.14
 */

public interface CrudService<DTO> {
    DTO save(DTO dto);

    DTO findOne(String id);

}
