# IES_103415
## Exercise 1.1

To create the script, it is necessary to install Maven (which requires the Java language to be installed, with JDK version 11 being recommended)
Verificar a versão do Java instalada

```java --version```

To install Maven, use the command

```sudo apt install maven```

Then check the version of Maven that was installed

```mvn --version```

## Exercise 1.2

Build tool: Tools to obtain dependencies, compile source code, package artifacts, etc. Tools to state the project dependencies on external artifacts

Create a project

```mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false```

A directory with the same name as the artifactId is created

```cd my-app```

Project Structure

```
my-app
|-- pom.xml
`-- src
|-- main
|   `-- java
|       `-- com
|           `-- mycompany
|               `-- app
|                   `-- App.java
`-- test
`-- java
`-- com
`-- mycompany
`-- app
`-- AppTest.java
```

### The POM
The pom.xml file is an XML file that contains information about the project and configuration details used by Maven to build the project. It contains default values for most projects.

### Maven Projects

archetype: an original pattern or model from which all other things of the same type are made. Generic model of a component in the system.

groupId: uniquely identifies the project among all projects. It must follow the package naming rules used in java, which means it starts with an inverted domain name that is controlled by the user. For example, com.mycompany.app, org.apache.maven, etc.

artifactId: unversioned jar name

A Maven project named my-app was created

groupId: com.mycompany.app

artifactId: my-app

version: 1.0-SNAPSHOT

Available in my-app

To compile

```mvn package```

To run the program (you must adapt the package and the class name)

```mvn exec:java -Dexec.mainClass="package.class"```

To run the program with input arguments

```mvn exec:java -Dexec.mainClass="package.class" -Dexec.args="arg0 arg1 arg2"```

At the end you will be presented:

```
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.953 s
[INFO] Finished at: 2019-11-24T13:05:10+01:00
[INFO] ------------------------------------------------------------------------
```

## Exercise 1.3

### Create new Git repository

```git init```

### Check status of files/directories

```git status```

### Add a specific directory

```git add meu_diretorio```

### Commit a file

```git commit meu_arquivo.txt```

### Remove directory

```git rm -r diretorio```

### View history

```git log```

### Add .gitignore file
This file is placed at the root of the repository and serves to ignore all unimportant files, i.e. files that will not be committed

In this exercise, the existence of another employee was simulated, for that a new folder was created with the name 'location2', in another directory on the computer.

```
git clone git@github.com:Souz11/IES_103415.git
```

The above command was used to get the files to the new location.
In this new location, a logger was created, and the operations executed were written to the terminal and to a file, 'logs.log'.
The Log4j2 helper library was used

Links used to create the files:

- https://www.baeldung.com/java-logging-intro
- https://howtodoinjava.com/log4j2/log4j2-xml-configuration-example/

To commit from this new location (thus simulating the existence of more than one contributor to the project) the commands described at the beginning were used.

In order to see all the messages sent in each commit, the following command can be used

```git log --reverse --oneline```

All messages from all project collaborators will be listed.

## Exercise 1.4

Introduction to Docker

### What are images? (Docker images)
You can understand the images as being a template (an OOP class) that allows starting a container. Each image is defined by a Dockerfile, a configuration file that contains all the commands a user needs to run to model the image.

Tutorial and Getting Started: https://docs.docker.com/get-started/ Followed the tutorial and the files are in this directory.

The Portainer app was also installed, available at https://www.portainer.io/, which is a web application and facilitates the control of containers.

### Some Docker commands:
See the containers that are currently running

```docker ps```

Create and start a container

```docker run```

remove a container

```docker rm```

View the image list

```docker images```

Create a new image from dockerfile

```docker build```

Download an image from a repository

```docker pull```

### What is docker compose?
It is a tool that allows you to define and run multi-container Docker applications. A YAML file is used to configure the services. Then, with a simple command it is possible to create and start all services.

Using docker compose consists of 3 processes:

1. Define your app's environment with a Dockerfile so it can be reproduced anywhere.

2. Define the services that make up your app in docker-compose.yml so they can be run together in an isolated environment.

3. Run docker compose up and the Docker compose command starts and runs your entire app. You can alternatively run docker-compose up using the docker-compose binary.

## Exercise 1.5

Foram criados 2 projetos maven para a resolução deste exercício. o projeto ipmaclient_api contém os dados da API, ou seja, contém os ficheiros responsáveis por obterem as temperaturas das cidades. O projeto weatherforecastbycity contém uma main simples, disponível em main, onde é chamada a função criada no primeiro projeto, que recebe como parametro o argumento da linha de comandos (args[0]).

Para a resolução deste exercício foi necessária a comunicação entre 2 projetos mavens. Para isso os ficheiros pom.xml foram alterados, bem como também foram usados alguns comandos.

Alterações em ipmaclient_api/pom.xml (para além das dependências necessárias)

```
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>fully.qualified.MainClass</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
</plugin>
```

Alterações em weatherforecastbycity/pom.xml

```
    <dependency>
      <groupId>com.ipmaapiclient</groupId>
      <artifactId>ipmaclient_api</artifactId>
      <version>1.1</version>
   </dependency>

  <repositories>
    <repository>
        <id>my-local-repo</id>
        <url>file://${basedir}/lib</url>
    </repository>
  </repositories>
  ```

## Review questions
A)
As principais fases de um projeto maven são: - validate: validate the project is correct and all necessary information is available - compile: compile the source code of the project - test: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed - package: take the compiled code and package it in its distributable format, such as a JAR - verify: run any checks on results of integration tests to ensure quality criteria are met - install: install the package into the local repository, for use as a dependency in other projects locally - deploy: done in the build environment, copies the final package to the remote repository for sharing with other developers and projects

B)
Sim, o maven é uma build automation tool usada principalmente para projetos Java, sendo que pode ser usada com qualquer outro tipo de linguagem (C#, Ruby, etc). É uma ferramente que permite gerir projetos, que se baseia no POM (Project Object Model). Pode ser usado para gerir e para correr projetos, tornando todo o processo mais fácil. Podem ser facilmente usados diferentes plugins e diferentes dependências de acordo com o nosso projeto.

C)
Começar por obter o código presente no repositório, isto é, fazer clone do repositório.

```git clone <REPOSITORY_URL>```

Caso já tenha o repositório no computador, descarregar as atualizações mais recentes

```git pull```

Entrar no diretório correto

```cd project_folder```

Adicionar os ficheiros alterados (será usado "." que significa todos os ficheiros)

```git add .```

Escrever a mensagem de commit

```git commit -m "This is a commit message!"```

Enviar os ficheiros para o repositório

```git push```

D)
Na minha opinião as mensagens de commit são importantes em todos os trabalhos, especialmente nos trabalhos de equipa para garantir uma boa comunicação, ou seja, todos os membros estarem em sintonia, isto é, saberem o que já foi feito, evitando pensar em algo que já foi implementado. Uma boa mensagem deve encaminhar para aquilo que é preciso ser feito.

Fonte: https://www.freecodecamp.org/news/writing-good-commit-messages-a-practical-guide/
Dicas/Regras para uma boa mensagem de commit:

1. Deve-se especificar o tipo de commit
- feat: adicionar uma nova feature 
-  fix: corrigir um erro
- style: mudanças relacionadas com o estilo da aplicação
- refactor: refazer uma secção específica da app 
-  test: tudo relacionado com testes
- docs: tudo relacionado com documentação
2. Separar o assunto do corpo da mensagem com uma linha em branco
3. A mensagem não deve conter erros relacionados com espaços em branco
4. Remover marcas de pontuação desnecessárias
5. Não terminar a linha do assunto com um ponto final
6. Usar maiúscula na primeira letra do assunto e em cada parágrafo
7. Usar o modo imperativo
8. Usar o corpo da mensagem para explicar as mundanças e o porquê
9. Não assumir que quem revê o código sabe a priori qual o problema, deve-se adicionar
10. Não pensar que o código é auto-explicativo
11. Seguir a convenção usada pela equipa

E)
É importante porque se usarmos sempre o mesmo container a cada vez que é executado docker stop/start é necessário reiniciar. Caso se use docker run vai ser criado um novo container vazio, ou seja, vão ser perdidos dados. É,então, necessário criar volumes com espaço suficiente (sendo que o espaço default é muito baixo) para database production e garantir que há uma boa estratégia de backup, evitando assim que haja dados que são perdidos.
