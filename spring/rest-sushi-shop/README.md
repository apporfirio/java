# Sobre

Pequena aplicação, back end, para venda de comida japonesa com pagamento na entrega. 



# Palavras-chave

Spring MVC, REST, JUnit, Mockito, Maven, Tomcat.



# Deployment local

É feito utilizando o plugin Maven de integração com o Tomcat.

- Comando para primeiro deployment: **mvn install tomcat7:deploy**. 

- Comando para deployments seguintes: **mvn install tomcat7:redeploy**.



# Pré-requisitos para deployment local

1) Instalar Tomcat 9 e criar o usuário **maven-war-deployer**. Para configurar o usuário, adicionar o trecho abaixo no arquivo
**tomcat-users.xml** localizado dentro da subpasta **conf** na pasta do Tomcat.

```xml
<user username="maven-war-deployer" password="senha-usuario-maven-war-deployer" roles="manager-script"/>
```


1) Instalar Maven 3.6 e adicionar o usuário Tomcat que foi criado acima no arquivo **settings.xml** localizado dentro da pasta **m2** do Maven.

```xml
<servers>
    <server>
        <id>local-tomcat-server</id>
        <username>maven-war-deployer</username>
        <password>senha-usuario-maven-war-deployer</password>
    </server>
</servers>
```  



# Endpoints

A **base-url** dos endpoints abaixo é dada por **localhost:8080/rest-sushi-shop/api**.

- GET base-url/product/{id}

