package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.EntityConfigVO;
import model.ServerApplicationConfigVO;
import model.TestIntegrationVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Lucas Fernando Frighetto
 */

public class ParseUtils {

    public static TestIntegrationVO getMapToTestIntegrationVO(Map<String, Object> map) throws Exception {
        TestIntegrationVO vo = new TestIntegrationVO();
        List<EntityConfigVO> entities = new ArrayList<>();
        try{
            List<Map<String, Object>> entitiesMap = (List<Map<String, Object>>) map.get("entities");
            Map<String, Object> serverConfigMap = (Map<String, Object>) map.get("serverConfigVO");
            ServerApplicationConfigVO serverConfig = new ServerApplicationConfigVO(
                    (String) serverConfigMap.get("ip"),
                    (Integer) serverConfigMap.get("port"),
                    (String) serverConfigMap.get("defaultPath"),
                    (Integer) serverConfigMap.get("usuario"),
                    (Integer) serverConfigMap.get("filial"),
                    (Integer) serverConfigMap.get("timeOutRequest")
            );

            for (Map<String, Object> entityMap : entitiesMap) {
                EntityConfigVO entityConfigVO = new EntityConfigVO((String)entityMap.get("entityName"), (String)entityMap.get("pathName"), (String)entityMap.get("primaryKeyName"));
                entities.add(entityConfigVO);
            }

            vo.setServerConfigVO(serverConfig);
            vo.setEntities(entities);
            return vo;
        }catch (Exception e){
            throw new Exception("Ocorreu erro na conversao de map to VO (getMapToTestIntegrationVO). "+e.getMessage());
        }

    }

    public static ObjectMapper getInstanceObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}

