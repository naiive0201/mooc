package io.learning.hs.mooc;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
public class WriterNotFoundException extends RuntimeException {

    WriterNotFoundException(Long id) {
        super("Could not find writer " + id);
    }
}
