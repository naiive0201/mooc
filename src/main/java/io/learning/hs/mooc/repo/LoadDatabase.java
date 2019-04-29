package io.learning.hs.mooc.repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(WriterRepository repo) {
        return args -> {
            log.info("Preloading " + repo.save(new Writer("Hyeonsoo", "admin")));
            log.info("Preloading " + repo.save(new Writer("Euna", "Reader")));
        };

    }
}
