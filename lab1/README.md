# IES_103415
## Exercício 1.1

Para a realização do guião do guião é necessária a instalação do Maven (que requer que a linguagem Java esteja instalada, sendo recomendada a versão 11 do JDK)

Verificar a versão do Java instalada

```java --version```

Para instalar o Maven foi usado o comando

```sudo apt install maven```

De seguida, verificar a versão do Maven que foi instalada

```mvn --version```

## Exercício 1.2

Usual Command Line Commands

```mvn --version```

Ver a versão Maven instalada

Criar um projeto

```mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false```

É criado um diretório com o mesmo nome do artifactId

```cd my-app```

Estrutura do Projeto

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
O ficheiro pom.xml é o ficheiro principal de configuração de um projeto Maven. É neste ficheiro que está a maior parte da informação necessária para a criação de um projeto.

Um exemplo de um ficheiro pom.xml: my-app/pom.xml

Build the Project Ao correr o comando,

```mvn package```

No final será apresentado:

```
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.953 s
[INFO] Finished at: 2019-11-24T13:05:10+01:00
[INFO] ------------------------------------------------------------------------
Ao contrário do primeiro comando (archetype:generate), este é apenas uma palavra, package. Para além de ser um goal, é também um fase, pelo que também serão executadas todas as fases anteriores.
```

### Run and Test

```java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App```

###Projetos Maven
archetype: um padrão ou modelo original do qual todas as outras coisas do mesmo tipo são feitas. Modelo genérico de uma componente no sistema.
groupId: identifica de forma única o projeto entre todos os projetos. Deve seguir as normas de nomes para os pacotes utilizadas em java, o que significa que começa por um domain name invertido que é controlado pelo utilizador. Por exemplo, com.mycompany.app, org.apache.maven, etc.
artifactId: nome do jar sem versão

Foi criado um projeto Maven com o nome my-app

groupId: com.mycompany.app
artifactId: my-app
version: 1.0-SNAPSHOT
Disponível em my-app

Para compilar

```mvn package```

Para correr o programa (deve-se adaptar o package e o nome da classe)

```mvn exec:java -Dexec.mainClass="package.class"```

Para correr o programa com argumentos de entrada

```mvn exec:java -Dexec.mainClass="package.class" -Dexec.args="arg0 arg1 arg2"```

## Exercício 1.3

### Criar novo repositório Git

```git init```

### Verificar estado dos arquivos/diretórios

```git status```

### Adicionar um diretório em específico

```git add meu_diretorio```

### Commit de um arquivo

```git commit meu_arquivo.txt```

### Remover diretório

```git rm -r diretorio```

### Exibir histórico

```git log```

### Exemplo ilustrativo

```
cd project_folder # move to the root of the working folder to be imported
git init # initialize a local git repo in this folder
git remote add origin <REMOTE_URL> #must adapt the url for your repo
git add. # mark all existing changes in this root to be commited
git commit -m "Initial project setup for exercise 1_3" #create the
commit snapshot locally
git push -u origin main #uploads the local commit to the shared repo
```

### Adicionar ficheiro .gitignore
Este ficheiro é colocado na raíz do repositório e serve para ignorar todos os ficheiros que não são importantes, ou seja, ficheiros que não vão ser commited

Neste exercício foi simulada a existência de outro colaborador, para isso foi criada uma nova pasta com o nome 'location2', noutro diretório do computador.

```
git clone git@github.com:pedromonteiro01/IES_97484.git
```

O comando acima foi usado para obter os ficheiros no novo local.
Nesta nova localização foi criado, então, um logger, sendo as operações executadas escritas no terminal e num ficheiro, 'logs.log'.
Foi usada a biblioteca auxiliar Log4j2

Links usados para criar os ficheiros:

- https://www.baeldung.com/java-logging-intro
- https://howtodoinjava.com/log4j2/log4j2-xml-configuration-example/

Para dar commit a partir desta nova localização (simulando, então, a existência de mais do que um colaborador para o projeto) foram usados os comandos descritos no início.

Para que seja possível ver todas as mensagens enviadas em cada commit pode ser usado o comando seguinte

```git log --reverse --oneline```

Serão listadas todas as mensagens, de todos os colaboradores do projeto.

## Exercício 1.4

Introdução ao Docker

### O que são imagens? (docker images)
Pode-se entender as imagens como sendo um template (uma classe OOP) que permite iniciar um container. Cada imagem é definida por um Dockerfile, um arquivo de configuração que contém todos os comandos que um utilizador precisa executar para modelar a imagem.

Começar por instalar o docker engine, disponível em https://docs.docker.com/engine/install/. Após a instalação, para uma melhor interação pode-se executar o docker sem ser necessário usar permissões, isto é, sem sudo - https://docs.docker.com/engine/install/linux-postinstall/.

Tutorial e Getting Started: https://docs.docker.com/get-started/ Foi seguido o tutorial e os ficheiros encontram-se neste diretório.

Foi instalada também a Portainer app, disponível em https://www.portainer.io/, que é uma web application e facilita o controlo dos containers.

### Alguns comandos docker:
Ver os containers que estão a correr no momento

```docker ps```

Criar e começar um container

```docker run```

Remover um container

```docker rm```

Ver a lista de imagens

```docker images```

Criar uma nova imagem a partir do dockerfile

```docker build```

Fazer download de uma imagem de um repositório

```docker pull```

Foi seguida a alternativa proposta no guião. Para isso foi criado um dockerfile, e 2 ficheiros que contém instruções SQL, visto que neste exemplo se correu o postgres

```
docker run --name pg-docker -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=sampledb
-e PGDATA=/tmp -d -p 5433:5432 -v ${PWD}:/var/lib/postgresql/data postgres:11
```

### O que é o docker compose?
É uma ferramenta que permite definir e correr multi-container Docker applications. É usado um ficheiro YAML para configurar os serviços. Depois, com um simples comando é possível criar e começar todos os serviços.

Usar o docker compose consiste em 3 processos:

1. Define your app’s environment with a Dockerfile so it can be reproduced anywhere.

2. Define the services that make up your app in docker-compose.yml so they can be run together in an isolated environment.

3. Run docker compose up and the Docker compose command starts and runs your entire app. You can alternatively run docker-compose up using the docker-compose binary.

Exemplo de um ficheiro docker-compose.yml

```
version: "3.9"  # optional since v1.27.0
services:
web:
build: .
ports:
- "5000:5000"
volumes:
- .:/code
- logvolume01:/var/log
links:
- redis
redis:
image: redis
volumes:
logvolume01: {}
```

## Exercício 1.5

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
