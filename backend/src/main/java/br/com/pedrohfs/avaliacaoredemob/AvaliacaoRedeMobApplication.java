package br.com.pedrohfs.avaliacaoredemob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AvaliacaoRedeMobApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvaliacaoRedeMobApplication.class, args);
    }

}
