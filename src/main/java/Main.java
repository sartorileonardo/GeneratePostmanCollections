import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.IntegrationFileWriter;
import model.TestIntegrationVO;
import utils.ParseUtils;

import java.util.Map;

/*
@author: Leonardo Sartori
@contact: leonardos@koerich.com.br / leonardogt4@hotmail.com
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Map<String, Object> mapObject = ParseUtils.getInstanceObjectMapper().readValue(IntegrationFileWriter.getJsonFile(), new TypeReference<Map<String, Object>>() {});
        TestIntegrationVO testIntegrationVO = ParseUtils.getMapToTestIntegrationVO(mapObject);
        IntegrationFileWriter.writeJSON(IntegrationFileWriter.generateTestIntegration(testIntegrationVO.getEntities(), testIntegrationVO.getServerConfigVO()), "src/main/java/file_output/", "collection_postman_test.json");
    }

}
