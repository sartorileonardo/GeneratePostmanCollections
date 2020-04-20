import core.IntegrationTestWriter;
import model.EntityTestIntegrationVO;
import model.ServerApplicationConfigVO;

import java.util.Arrays;
import java.util.List;
/*
@author: Leonardo Sartori
@contact: leonardos@koerich.com.br / leonardogt4@hotmail.com
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //My config server application'
        ServerApplicationConfigVO serverConfig = new ServerApplicationConfigVO(
                "localhost",
                8081,
                "/kwscontrole/erpweb",
                83688,
                18,
                5000);

        //Names from my entities
        List<EntityTestIntegrationVO> entityList = Arrays.asList(
                getEntityInstance("Telas", "telas", "codigo"),
                //getEntityInstance("Scripts", "scripts", "numero")
                getEntityInstance("Especies", "especies", "codigo")
        );

        generate(entityList, serverConfig);
    }
    private static void generate(List<EntityTestIntegrationVO> entityList, ServerApplicationConfigVO server) {
        IntegrationTestWriter.writeJSON(IntegrationTestWriter.generateTestIntegration(entityList, server), "src/main/java/file_output/", "collection_postman_test.json");
    }

    private static EntityTestIntegrationVO getEntityInstance(String entityName, String pathName, String primaryKeyName) {
        return new EntityTestIntegrationVO(entityName, pathName, primaryKeyName);
    }


}
