# prj-gerador-servico
Projeto criado para expor novos serviços CRUD's a partir de um cadastro prévio do domínio


# Solução Arquitetural Desenhada

  Após analisar os requisitos solicitados cheguei a conclusão que a melhor solução arquitetural seria desenvolver uma aplicação que escrevesse os arquivos de outra aplicação responsável por expor os serviços CRUD's.
  Nas duas aplicações usei arquitetura de API Rest com Spring Boot. A aplicação pai ficou responsável por armazenar a arquitura da aplicação filho em seu classpath, desta forma eu garanto que as duas aplicações vão estar sempre juntas. 
  Para facilitar as evoluções futuras e a escalabilidades destas arquiteturas eu usei o padrão MVC para dividir a codificação destas API's em camadas.
  Para aumentar a coesão e reduzir o acoplamento eu utilizei recursos de injeção de dependências do framework Spring, para facilitar a injeção de dependencia eu apliquei o pattern facade, com ele ao invez de injetar uma classe contendo toda sua implementação eu crio interfaces isolando apenas as dependências que de fato eu irei injetar em cada camada.
  
# Tecnologias Utilizadas

  Controle de Versão: Git;
  Linguagem de Progração: Java EE - Versão da JDK 1.8.0_181;
  Gerenciador de Dependências: Maven - Versão 3.5.4;
  IDE: Eclipse Photon;
  Banco de Dados: MySQL;
  Documentação de API: Swagger;
  Manutenção de scripts DML e DDL: Liquibase;
  Acesso a dados, Exposição de Serviços, Injeção de Dependências e Agrupador de Dependências: Spring;  
  
# Intruções para Execução

  Criar as variáveis de ambiente: JAVA_HOME e MAVEN_HOME;
  Baixar a última versão do Eclipse;
  Importar o projeto gerador-gerador-servico no eclipse: O mesmo mesmo deve ser importado com um projeto maven;
  Criar o build do projeto no eclipse usando o comando: clean install;
  Criar o run do projeto spring boot usando o comanto: spring-boot:run;
  
  Acessar interface do Swagger da aplicação geradora de serviço:  
  
  http://localhost:8080/gerador-servicos/swagger-ui.html
  
  Payload para Teste:

{
  "descricaoDominio": "Carro",
  "id": 0,
  "listaAtributo": [
    {
      "descricao": "descricao",
      "id": 1,
      "isObrigatorio": false,
      "quantidadeDigitos": "100",
      "tipoDeDadoEnum": "STRING"
    },
    {
      "descricao": "status",
      "id": 2,
      "isObrigatorio": false,
      "quantidadeDigitos": "0",
      "tipoDeDadoEnum": "BOOLEAN"
    },
    {
      "descricao": "preco",
      "id": 3,
      "isObrigatorio": true,
      "quantidadeDigitos": "0",
      "tipoDeDadoEnum": "DOUBLE"
    },
    {
      "descricao": "dataCadastro",
      "id": 4,
      "isObrigatorio": false,
      "quantidadeDigitos": "0",
      "tipoDeDadoEnum": "DATE"
    }
  ]
}


  Acessar a aplicação de serviços gerados para verificar se o novo serviço foi criado corretamente:
  
  http://localhost:8081/servicos-gerados/swagger-ui.html


