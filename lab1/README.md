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
### A) 
Maven has three lifecycles: clean, site and default. Explain the main phases in the default lifecycle.

Validate-> validate the project is correct and all necessary information is available

Compile-> compile the source code of the project

Test-> test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed

Package-> take the compiled code and package it in its distributable format, such as a JAR.

Verify-> run any checks on results of integration tests to ensure quality criteria are met

Install-> install the package into the local repository, for use as a dependency in other projects locally

Deploy-> done in the build environment, copies the final package to the remote repository for sharing with other developers and projects.

### B) 
Maven is a build tool; is it appropriate to run your project to?

Yes, it is. Maven main purpose is to configure projects and handle the build activities and resulting artifacts but t can also activate different plugins which can be used to execute(run) specific classes.
###C) What would be a likely sequence of Git commands required to contribute with a new feature to a given project? (i.e., get a fresh copy, develop some increment, post back the added functionality)

```git pull```

To get the most recent changes of the code.

```git add .```

To add the files we want to update. Parameter "." means we're selecting everything from the repository.

```git commit -m "Lab01 completed"```

To commit the changes. It requires parameter -m followed by a commit message.

```git push```

To update the repository with the newly committed changes.

### D) 
There are strong opinions on how to write Git commit messages… Find some best practices online and give your own informed recommendations on how to write good commit messages (in a team project).

Commit messages are important because they give us a generalized idea of what is going on in the current code and to know what was updated or removed, so other colaboratores can be on the same page. The contributors to these repositories know that a well-crafted Git commit message is the best way to communicate context about a change to fellow developers

7 rules:
Separate subject from body with a blank line
Limit the subject line to 50 characters
Capitalize the subject line
Do not end the subject line with a period
Use the imperative mood in the subject line
Wrap the body at 72 characters
Use the body to explain what and why vs. how


Example:

__Bad__

```git commit -m "Fix bug"```

__Good__

```git commit -m "Add auto login for verified users - Closes BLG-20"```

### E) 
Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?

Volumes help you decouple the configuration of the Docker host from the container runtime. When you want to store your container's data on a remote host or a cloud provider, rather than locally. When you need to back up, restore, or migrate data from one Docker host to another, volumes are a better choice.
Dedicated resources makes data safer agaisnt problems like container deletion and it is aso easier to backup production databases.
