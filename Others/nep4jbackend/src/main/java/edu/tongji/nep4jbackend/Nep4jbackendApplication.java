package edu.tongji.nep4jbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories
@SpringBootApplication
public class Nep4jbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nep4jbackendApplication.class, args);
    }

}
