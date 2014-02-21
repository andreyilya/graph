package graph.engine.converter;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
public abstract class AbstractConverter<DTO, ENTITY> {
    public abstract ENTITY assemble(DTO dto);
    public abstract DTO disassemble(ENTITY entity);
}
