# drei-spring-boot-csv-parser

Simple [Spring Boot](http://projects.spring.io/spring-boot/) CSV Parser.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Build the application

To build this application you have to first install all the dependencies mentioned above.
When done, you have to run following commands:

```shell
mvn install
```

Once executed you successfully built the Project and created a jar file.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `dcom.example.dreispringbootcsvparser.DreiSpringBootCsvParserApplication` class from your IDE. You also have to
pecify the path of the input file e.g. /src/test/resources/original.csv .

To execute the created jar file you have to exercute following command with your correct filepaths:

```shell
java -jar <jar_file_path/drei-spring-boot-csv-parser-0.0.1-SNAPSHOT.jar> <CSV file path>
```

