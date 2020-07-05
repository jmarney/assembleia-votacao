package br.com.assembleiavota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AssembleiaVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssembleiaVotacaoApplication.class, args);
    }
}
