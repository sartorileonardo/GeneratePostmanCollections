import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import core.IntegrationFileWriter;
import model.EntityConfigVO;
import model.ServerApplicationConfigVO;
import model.TestIntegrationVO;
import utils.ParseUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@author: Leonardo Sartori
@contact: leonardos@koerich.com.br / leonardogt4@hotmail.com
 */
public class Main {

    public static void main(String[] args) throws Exception {

        File jsonFile = new File("src/main/java/file_input/input.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapObject = objectMapper.readValue(jsonFile,
                new TypeReference<Map<String, Object>>() {
                });
        TestIntegrationVO testIntegrationVO = ParseUtils.getMapToTestIntegrationVO(mapObject);
        IntegrationFileWriter.writeJSON(IntegrationFileWriter.generateTestIntegration(testIntegrationVO.getEntities(), testIntegrationVO.getServerConfigVO()), "src/main/java/file_output/", "collection_postman_test.json");
    }

}
