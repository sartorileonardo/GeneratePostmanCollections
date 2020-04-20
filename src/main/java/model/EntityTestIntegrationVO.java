package model;

import java.util.Map;

public class EntityTestIntegrationVO {
    private String entityName;
    private String pathName;
    private String primaryKeyName;

    public EntityTestIntegrationVO(String entityName, String pathName, String primaryKeyName) {
        this.entityName = entityName;
        this.pathName = pathName;
        this.primaryKeyName = primaryKeyName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }
}
