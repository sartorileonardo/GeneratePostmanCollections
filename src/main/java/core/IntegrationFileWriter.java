package core;

import com.github.underscore.lodash.U;
import com.google.gson.Gson;
import model.EntityConfigVO;
import model.ServerApplicationConfigVO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IntegrationFileWriter {

    private static final String POSTMAN_VERSION_API = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";

    private static String generateId() {
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(getRandom().nextLong()) + "-" + Math.abs(getRandom().nextFloat()) + "-" + Math.abs(getRandom().nextInt()));
        return sb.toString();
    }

    private static Random getRandom() {
        Random random = new Random();
        return random;
    }

    private static Map<String, Object> generateInfo() {
        Map<String, Object> info = new LinkedHashMap<String, Object>();
        info.put("_postman_id", generateId());
        info.put("name", "Registers");
        info.put("description", "My Registers (CRUD)");
        info.put("schema", POSTMAN_VERSION_API);

        return info;
    }

    private static List<Map<String, Object>> generateEvent() {

        Map<String, Object> script1 = new LinkedHashMap<String, Object>();
        script1.put("id", generateId());
        script1.put("type", "text/javascript");
        script1.put("exec", Arrays.asList(""));

        Map<String, Object> event1 = new LinkedHashMap<String, Object>();
        event1.put("listen", "prerequest");
        event1.put("script", script1);

        Map<String, Object> script2 = new LinkedHashMap<String, Object>();
        script2.put("id", generateId());
        script2.put("type", "text/javascript");
        script2.put("exec", Arrays.asList(""));

        Map<String, Object> event2 = new LinkedHashMap<String, Object>();
        event2.put("listen", "test");
        event2.put("script", script2);

        List<Map<String, Object>> events = Arrays.asList(event1, event2);

        return events;
    }

    private static List<Map<String, Object>> generateVariable(ServerApplicationConfigVO serverConfig) {

        Map<String, Object> default_server_config = new LinkedHashMap<String, Object>();
        default_server_config.put("id", generateId());
        default_server_config.put("key", "default_server_config");
        default_server_config.put("value", "http://" + serverConfig.getIp() + ":" + serverConfig.getPort() + serverConfig.getDefaultPath());
        default_server_config.put("type", "string");

        Map<String, Object> filial = new LinkedHashMap<String, Object>();
        filial.put("id", generateId());
        filial.put("key", "filial");
        filial.put("value", serverConfig.getfilial());
        filial.put("type", "string");

        Map<String, Object> usuario = new LinkedHashMap<String, Object>();
        usuario.put("id", generateId());
        usuario.put("key", "usuario");
        usuario.put("value", serverConfig.getusuario());
        usuario.put("type", "string");


        Map<String, Object> idEntity = new LinkedHashMap<String, Object>();
        idEntity.put("id", generateId());
        idEntity.put("key", "id");
        idEntity.put("value", "");
        idEntity.put("type", "string");

        List<Map<String, Object>> variables = Arrays.asList(default_server_config, usuario, filial, idEntity);

        return variables;
    }

    private static List<Map<String, Object>> generateOperation(EntityConfigVO entity, ServerApplicationConfigVO serverConfig) {
        String operation = "";

        operation = "Get All";
        Map<String, Object> getAll = new LinkedHashMap<String, Object>();
        getAll.put("name", "Get All " + entity.getEntityName());
        getAll.put("event", generateEventFromHttpRequest(operation, entity.getPrimaryKeyName(), serverConfig));
        getAll.put("request", generateRequestHttp(entity, "GET", operation));
        getAll.put("response", Arrays.asList(""));

        operation = "Get One";
        Map<String, Object> getOne = new LinkedHashMap<String, Object>();
        getOne.put("name", "Get One " + entity.getEntityName());
        getOne.put("event", generateEventFromHttpRequest(operation, entity.getPrimaryKeyName(), serverConfig));
        getOne.put("request", generateRequestHttp(entity, "GET", operation));
        getOne.put("response", Arrays.asList(""));

        operation = "Add";
        Map<String, Object> add = new LinkedHashMap<String, Object>();
        add.put("name", "Add " + entity.getEntityName());
        add.put("event", generateEventFromHttpRequest(operation, entity.getPrimaryKeyName(), serverConfig));
        add.put("request", generateRequestHttp(entity, "POST", operation));
        add.put("response", Arrays.asList(""));

        operation = "Update";
        Map<String, Object> update = new LinkedHashMap<String, Object>();
        update.put("name", "Update " + entity.getEntityName());
        update.put("event", generateEventFromHttpRequest(operation, entity.getPrimaryKeyName(), serverConfig));
        update.put("request", generateRequestHttp(entity, "PUT", operation));
        update.put("response", Arrays.asList(""));

        operation = "Delete";
        Map<String, Object> delete = new LinkedHashMap<String, Object>();
        delete.put("name", "Delete " + entity.getEntityName());
        delete.put("event", generateEventFromHttpRequest(operation, entity.getPrimaryKeyName(), serverConfig));
        delete.put("request", generateRequestHttp(entity, "DELETE", operation));
        delete.put("response", Arrays.asList(""));

        List<Map<String, Object>> operations = Arrays.asList(getAll, getOne, add, update, delete);

        return operations;
    }

    private static Map<String, Object> generateRequestHttp(EntityConfigVO entity, String verb, String operation) {
        Map<String, Object> request = new LinkedHashMap<String, Object>();
        request.put("description", operation + " " + entity.getEntityName());
        request.put("method", verb);
        request.put("description", operation + " - " + entity.getEntityName());
        request.put("header", Arrays.asList(
                generateHeaderFromRequest("filial", "text", "{{filial}}"),
                generateHeaderFromRequest("usuario", "text", "{{usuario}}"),
                generateHeaderFromRequest("Content-Type", "text", "application/json")
        ));
        request.put("url", generateURL("{{default_server_config}}/" + entity.getPathName() + "/", "{{default_server_config}}", "", entity.getPathName(), verb, operation));
        if (verb.equalsIgnoreCase("PUT") || verb.equalsIgnoreCase("POST")) {
            request.put("body", generateBody());
        }

        return request;
    }

    private static Map<String, Object> generateBody() {
        Map<String, Object> bodyRequest = new LinkedHashMap<String, Object>();
        bodyRequest.put("mode", "raw");
        bodyRequest.put("raw", "{{dynamicBody}}");
        return bodyRequest;
    }

    private static String generateRawFromBody() {
        Gson json = new Gson();
        String rawBody = json.toJson("{{dynamicBody}}");
        return rawBody;
    }

    private static Map<String, Object> generateURL(String raw, String host, String other, String path, String verbo, String operation) {
        Map<String, Object> url = new LinkedHashMap<String, Object>();
        if (verbo.equalsIgnoreCase("PUT") || verbo.equalsIgnoreCase("DELETE") || operation.equalsIgnoreCase("Get One")) {
            url.put("raw", raw.concat("{{id}}"));
            url.put("path", Arrays.asList(path, "{{id}}"));
        } else {
            url.put("raw", raw);
            url.put("path", Arrays.asList(path, other));
        }
        url.put("host", Arrays.asList(host));


        return url;
    }

    private static Map<String, Object> generateHeaderFromRequest(String key, String type, String value) {
        Map<String, Object> header = new LinkedHashMap<String, Object>();
        header.put("key", key);

        if (value.equalsIgnoreCase("application/json")) {
            header.put("name", "Content-Type");
        }

        header.put("type", type);
        header.put("value", value);

        return header;
    }

    private static List<Map<String, Object>> generateEventFromHttpRequest(String operation, String primaryKeyName, ServerApplicationConfigVO serverConfig) {
        List<Map<String, Object>> events = new ArrayList<Map<String, Object>>();

        Map<String, Object> eventTest = new LinkedHashMap<String, Object>();
        eventTest.put("listen", "test");
        eventTest.put("script", generateScriptFromEvent(operation, primaryKeyName, serverConfig));
        events.add(eventTest);

        Map<String, Object> eventPrerequest = new LinkedHashMap<String, Object>();
        eventPrerequest.put("listen", "prerequest");
        eventPrerequest.put("script", generateScriptPrerequestFromEvent(operation));
        eventTest.put("id", generateId());
        events.add(eventPrerequest);

        return events;
    }

    private static Map<String, Object> generateScriptFromEvent(String operation, String primaryKeyName, ServerApplicationConfigVO serverConfig) {
        Map<String, Object> script = new LinkedHashMap<String, Object>();
        script.put("id", generateId());

        if (operation.equalsIgnoreCase("Get All")) {
            script.put("exec", Arrays.asList(
                    "pm.test(\"Status code is 200\", function () {",
                    "    pm.response.to.have.status(200);",
                    "});",
                    "pm.test(\"Response time is less than " + serverConfig.getTimeOutRequest() + "ms\", function () {",
                    "    pm.expect(pm.response.responseTime).to.be.below(" + serverConfig.getTimeOutRequest() + ");",
                    "});",
                    "",
                    "var jsonData = pm.response.json();",
                    "pm.environment.set(\"id\", jsonData[jsonData.sort().length-1]." + primaryKeyName + ");",
                    "console.log(pm.environment.get(\"id\"));"
            ));
        }

        if (operation.equalsIgnoreCase("Get One")) {
            script.put("exec", Arrays.asList(
                    "pm.test(\"Status code is 200\", function () {\n" +
                            "    pm.response.to.have.status(200);\n" +
                            "});\n" +
                            "pm.test(\"Response time is less than " + serverConfig.getTimeOutRequest() + "ms\", function () {\n" +
                            "    pm.expect(pm.response.responseTime).to.be.below(" + serverConfig.getTimeOutRequest() + ");\n" +
                            "});\n" +
                            "var responseBody = JSON.parse(responseBody);\n" +
                            "\n" +
                            "function generateRandomText(length){\n" +
                            "   var result           = '';\n" +
                            "   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';\n" +
                            "   var charactersLength = characters.length;\n" +
                            "   for ( var i = 0; i < length; i++ ) {\n" +
                            "      result += characters.charAt(Math.floor(Math.random() * charactersLength));\n" +
                            "   }\n" +
                            "   return result;\n" +
                            "}\n" +
                            "\n" +
                            "function generateRandomNumber(length){\n" +
                            "    return Math.floor(Math.random() * length);\n" +
                            "}" +
                            "\n" +
                            "function formatterPrimaryKey(entity){\n" +
                            "    if(typeof entity." + primaryKeyName + " == 'number'){\n" +
                            "        entity." + primaryKeyName + " = generateRandomText(generateRandomNumber(entity." + primaryKeyName + ".length));\n" +
                            "        console.log(\"Is not a number.\");\n" +
                            "    }",
                            "   return entity;\n" +
                            "}\n" +
                            "\n" +
                            "pm.environment.set('dynamicBody', responseBody);\n" +
                            "console.log(pm.environment.get(\"dynamicBody\"));"
            ));
        }

        if (operation.equalsIgnoreCase("Add")) {
            script.put("exec", Arrays.asList(
                    "pm.test(\"Status code is 200\", function () {",
                    "    pm.response.to.have.status(200);",
                    "});",
                    "pm.test(\"Response time is less than " + serverConfig.getTimeOutRequest() + "ms\", function () {",
                    "    pm.expect(pm.response.responseTime).to.be.below(" + serverConfig.getTimeOutRequest() + ");",
                    "});",
                    "pm.environment.set(\"id\", pm.response.json());"
            ));
        }

        script.put("type", "text/javascript");

        return script;
    }

    private static Map<String, Object> generateScriptPrerequestFromEvent(String operation) {
        Map<String, Object> script = new LinkedHashMap<String, Object>();
        script.put("id", generateId());

        if (operation.equalsIgnoreCase("Get All")) {
            script.put("exec", Arrays.asList(
                    "pm.environment.unset(\"id\");",
                    "pm.environment.unset(\"dynamicBody\");"
            ));
        }

        if (operation.equalsIgnoreCase("Get One")) {
            script.put("exec", Arrays.asList(
                    ""
            ));
        }

        if (operation.equalsIgnoreCase("Add")) {
            script.put("exec", Arrays.asList(
                    "var dynamicBody = pm.environment.get(\"dynamicBody\");",
                    "pm.environment.set(\"dynamicBody\", JSON.stringify(dynamicBody));",
                    "pm.environment.get(\"dynamicBody\");"
            ));
        }

        if (operation.equalsIgnoreCase("Update") || operation.equalsIgnoreCase("Delete")) {
            script.put("exec", Arrays.asList(
                    "pm.environment.get(\"id\")"
            ));
        }
        script.put("type", "text/javascript");

        return script;
    }


    public static Map<String, Object> generateTestIntegration(List<EntityConfigVO> entityList, ServerApplicationConfigVO serverConfig) {
        Map<String, Object> exit = new LinkedHashMap<String, Object>();
        exit.put("info", generateInfo());
        List<Map<String, Object>> itens = new ArrayList<Map<String, Object>>();

        for (EntityConfigVO entity : entityList) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("name", entity.getEntityName());
            map.put("item", generateOperation(entity, serverConfig));
            itens.add(map);
        }
        exit.put("item", itens);
        exit.put("variable", generateVariable(serverConfig));
        exit.put("event", generateEvent());

        return exit;
    }

    public static void writeJSON(Map<String, Object> jsonContent, String path, String fileName) {
        try {
            File f2 = new File(path.concat(fileName));
            FileWriter fw = new FileWriter(f2);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(U.toJson(jsonContent));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getJsonFile() throws Exception {
        try {
            return new File("src/main/java/file_input/input.json");
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao obter o arquivo de input.json. " + e.getMessage());
        }
    }
}
