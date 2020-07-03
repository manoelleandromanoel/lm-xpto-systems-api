# lm-xpto-systems-api
 API para leitura (CSV) e manutenção de uma lista de cidades

## Tecnologias
- Ambiente de desenvolvimento (IDE): [Spring Tool Suite 3](https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3) Version: 3.9.11.RELEASE
- Linguagem de programação: [Java 8](https://www.java.com/pt_BR/download/faq/java8.xml)
- Banco de dados: Foi utilizado o [H2](https://www.h2database.com/html/main.html) que é em memória, caso queira reinicializar os testes basta parar e subir a aplicação novamente. Para acessar o console do H2: http://localhost:9000/h2
- Framework: [SpringBoot](https://spring.io/projects/spring-boot) 2.2.6
- Controle de dependências: [Maven](https://maven.apache.org/)

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

//TODO 3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;

//TODO 4. Retornar a quantidade de cidades por estado;

5. Obter os dados da cidade informando o id do IBGE;
- **@GET** 'http://localhost:9000/cidades/{ibge_id}'

6. Retornar o nome das cidades baseado em um estado selecionado;
- **@GET** 'http://localhost:9000/cidades/by/estado/{uf}' 

7. Permitir adicionar uma nova Cidade;
- **@POST** 'http://localhost:9000/cidades/'
Com o json de exemplo: { "ibge_id": 99999, "uf": "SP", "name": "CIDADE DE TESTE DO ADDCIDADE", "capital": true, "lon": -61.9998238963, "lat": -11.9355403048, "no_accents": "CIDADE DE TESTE DO ADDCIDADE", "alternative_names": "", "microregion": "Cacoal", "mesoregion": "Leste Rondoniense" }
 
8. Permitir deletar uma cidade;
- **@DELETE** 'http://localhost:9000/cidades/{ibge_id}'

9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;
- **@GET** 'http://localhost:9000/cidades?col=""&val=""'

//TODO 10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;

11. Retornar a quantidade de registros total;
- **@GET** 'http://localhost:9000/cidades/total' 

//TODO 12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);