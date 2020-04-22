package utils;

import model.EntityConfigVO;
import model.ServerApplicationConfigVO;
import model.TestIntegrationVO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                    (Integer) serverConfigMap.get("erkUsuario"),
                    (Integer) serverConfigMap.get("erkFilial"),
                    (Integer) serverConfigMap.get("timeOutRequest")
            );

            for (Map<String, Object> entityMap : entitiesMap) {
                Map<String, Object> newEntity = (Map<String, Object>) entityMap.get("entityConfigVO");
                EntityConfigVO entityConfigVO = new EntityConfigVO((String)newEntity.get("entityName"), (String)newEntity.get("pathName"), (String)newEntity.get("primaryKeyName"));
                entities.add(entityConfigVO);
            }

            vo.setServerConfigVO(serverConfig);
            vo.setEntities(entities);
            return vo;
        }catch (Exception e){
            throw new Exception("Ocorreu erro na conversao de map to VO (getMapToTestIntegrationVO). "+e.getMessage());
        }

    }

}

