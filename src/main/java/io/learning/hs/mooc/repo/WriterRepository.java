package io.learning.hs.mooc.repo;

import io.learning.hs.mooc.controller.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
public interface WriterRepository extends JpaRepository<Writer, Long> {

}
