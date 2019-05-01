package io.learning.hs.mooc.writing;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
public class WritingNotFoundException extends RuntimeException {
    public WritingNotFoundException(Long id) {
        super("Could not find Writing " + id);
    }
}
