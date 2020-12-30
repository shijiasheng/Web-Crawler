package edu.tongji.nep4jbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableNeo4jRepositories
@SpringBootApplication
public class Nep4jbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nep4jbackendApplication.class, args);
    }

}
