# lm-xpto-systems-api
 API REST para leitura (CSV) e manutenção de uma lista de cidades

## Tecnologias
- Ambiente de desenvolvimento (IDE): [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) Version: 2020.2.3 (Community Edition)
- Linguagem de programação: [Java 8](https://www.java.com/pt_BR/download/faq/java8.xml)
- Framework: [SpringBoot](https://spring.io/projects/spring-boot) 2.3.1
- Controle de dependências: [Maven](https://maven.apache.org/)
- Banco de dados: [H2](https://www.h2database.com/html/main.html). Banco de dados em memória, caso queira reinicializar os testes basta parar e subir a aplicação novamente. Para acessar o console do H2: http://localhost:9000/h2

## Padrões
- [RESTful](https://blog.caelum.com.br/rest-principios-e-boas-praticas/)

## Instalação
- Faça o download e a instalação do [Java](https://www.java.com/pt_BR/download/)
- Instale o [Maven](http://www.matera.com/blog/post/tutorial-instalacao-apache-maven-configuracao-eclipse)

## Executar a aplicação
- Faça o Download do repositório no diretório desejado através do comando 'git clone https://github.com/manoelleandromanoel/lm-xpto-systems-api.git'
- Abra o prompt de comando (cmd/PowerShell) e rode o comando: mvn clean package
- Posteriormente rode o comando: java -jar target/lm-xpto-systems-api-1.0.0.jar
- Para efetuar os testes da aplicação, foi utilizado a ferramenta [Postman](https://www.postman.com/). Caso queira facilitar os testes basta importar no Postman o arquivo que está no diretório: 'docs/LM XPTOSystems API.postman_collection.json'

## ROTAS DA API
1. LER o arquivo CSV para a base de dados
- **@POST** 'http://localhost:9000/cidades/upload'
Com o arquivo de 'Desafio Cidades - Back End.csv' que se encontra na raiz do projeto

2. Retornar as cidades que são capitais ordenadas por nome
- **@GET** 'http://localhost:9000/cidades/capitais'

3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;
- **@GET** 'http://localhost:9000/cidades/tamanho/estados/cidades'

4. Retornar a quantidade de cidades por estado;
- **@GET** 'http://localhost:9000/cidades/estados'

5. Obter os dados da cidade informando o id do IBGE;
- **@GET** 'http://localhost:9000/cidades/{ibge_id}'

6. Retornar o nome das cidades, baseado num estado selecionado;
- **@GET** 'http://localhost:9000/cidades/by/estado/{uf}' 

7. Permitir adicionar uma nova Cidade;
- **@POST** 'http://localhost:9000/cidades/'
Com o json de exemplo: { "ibge_id": 99999, "uf": "SP", "name": "CIDADE DE TESTE DO ADDCIDADE", "capital": true, "lon": -61.9998238963, "lat": -11.9355403048, "no_accents": "CIDADE DE TESTE DO ADDCIDADE", "alternative_names": "", "microregion": "Cacoal", "mesoregion": "Leste Rondoniense" }
 
8. Permitir deletar uma cidade;
- **@DELETE** 'http://localhost:9000/cidades/{ibge_id}'

9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;
- **@GET** 'http://localhost:9000/cidades?col=""&val=""'

10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;
- **@GET** 'http://localhost:9000/cidades/by/column?col=""&val=""'

11. Retornar a quantidade de registros total;
- **@GET** 'http://localhost:9000/cidades/total' 

12. Dentre todas as cidades, obter as duas cities mais distantes uma da outra com base na localização (distância em KM em linha reta).
- **@GET** 'http://localhost:9000/cidades/distancia' 