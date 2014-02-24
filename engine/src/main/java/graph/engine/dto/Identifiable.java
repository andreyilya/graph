package graph.engine.dto;

import org.apache.commons.lang.StringUtils;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public abstract class Identifiable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (StringUtils.isBlank(this.getId()) || !(obj instanceof Identifiable)) {
            return false;
        } else {
            return this.id.equals(((Identifiable) obj).getId());
        }
    }
}
