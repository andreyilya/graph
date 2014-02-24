package graph.engine.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
public abstract class AbstractConverter<DTO, ENTITY> {
    public abstract ENTITY assemble(DTO dto);

    public abstract DTO disassemble(ENTITY entity);

    public List<DTO> disassembleList(List<ENTITY> entities) {
        List<DTO> dtos = new ArrayList<>();
        for (ENTITY entity : entities) {
            dtos.add(disassemble(entity));
        }
        return dtos;
    }
}
