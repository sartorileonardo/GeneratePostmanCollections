package model;

import java.util.List;

public class TestIntegrationVO {
    private ServerApplicationConfigVO serverConfigVO;
    private List<EntityConfigVO> entities;

    public TestIntegrationVO() {
    }

    public TestIntegrationVO(ServerApplicationConfigVO serverConfigVO, List<EntityConfigVO> entities) {
        this.serverConfigVO = serverConfigVO;
        this.entities = entities;
    }

    public ServerApplicationConfigVO getServerConfigVO() {
        return serverConfigVO;
    }

    public void setServerConfigVO(ServerApplicationConfigVO serverConfigVO) {
        this.serverConfigVO = serverConfigVO;
    }

    public List<EntityConfigVO> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityConfigVO> entities) {
        this.entities = entities;
    }
}
