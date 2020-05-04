# Descrição:
Gerador de teste de integração no formato Postman Collection v2.1.0 para testar entidades de cadastro (CRUD).

# Passo a passo para usar:
1. Verificar se o seu Web Service está no padrão /img/rest_pattern.png
2. Clonar o repositório: git clone https://gitlab.com/koerichti/testeintegracaogenerator.git;
3. Modificar o file_input/input.json editando entidades e server conforme necessidade.
4. Executar os comandos:
cd /fontes/testeintegracaogenerator ; java -cp target/testeintegracaogenerator-1.0-SNAPSHOT.jar Main ; newman run /fontes/testeintegracaogenerator/src/main/java/file_output/collection_postman_test.json 

5. (Opcional) - Importar o arquivo de saída no programa Postman (Collections), o arquivo de saída fica no caminho file_output/collection_postman_test.json
