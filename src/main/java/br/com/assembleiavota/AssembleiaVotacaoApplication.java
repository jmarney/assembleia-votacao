package br.com.assembleiavota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AssembleiaVotacaoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AssembleiaVotacaoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AssembleiaVotacaoApplication.class);
    }
}
