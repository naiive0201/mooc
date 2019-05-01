package io.learning.hs.mooc;
import io.learning.hs.mooc.writing.Status;
import io.learning.hs.mooc.writing.Writing;
import io.learning.hs.mooc.writing.WritingRepository;
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
    CommandLineRunner initDatabase(WriterRepository repo, WritingRepository repo2) {
        repo2.save(new Writing("MacBook Pro", "Good", Status.COMPLETED));
        repo2.save(new Writing("iPhone", "also good", Status.IN_PROGRESS));

        repo2.findAll().forEach(writing -> {
            log.info("Preloaded " + writing);
        });

        return args -> {
            log.info("Preloading " + repo.save(new Writer("Hyeonsoo", "Joo", "admin")));
            log.info("Preloading " + repo.save(new Writer("Euna", "Joo", "Reader")));
        };

    }
}
