# Description:
Integration test generator in Postman Collection v2.1.0 format to test registration entities (CRUD).

# Step by step to use:
1. Check if your Web Service is in the /img/rest_pattern.png standard
2. Clone the repository: git clone https://gitlab.com/koerichti/testeintegracaogenerator.git;
3. Modify file_input / admitjson by editing entities and servers as needed.
4. Run the commands:
cd / testeintegracaogenerator; java -cp target / testeintegracaogenerator-1.0-SNAPSHOT.jar Main; newman run /fontes/testeintegracaogenerator/src/main/java/file_output/collection_postman_test.json

5. (Optional) - Import the output file in the Postman program (Collections), the output file is in the path file_output / collection_postman_test.json
