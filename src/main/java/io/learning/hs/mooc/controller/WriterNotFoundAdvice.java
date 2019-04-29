package io.learning.hs.mooc.controller;

import io.learning.hs.mooc.controller.WriterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@ControllerAdvice
public class WriterNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(WriterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String writerNotFoundHandler(WriterNotFoundException ex) {
        return ex.getMessage();
    }
}
