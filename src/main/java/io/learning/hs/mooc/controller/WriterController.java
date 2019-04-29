package io.learning.hs.mooc.controller;

import io.learning.hs.mooc.repo.WriterRepository;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@RestController
public class WriterController {
    private final WriterRepository repository;

    WriterController(WriterRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/writers")
    List<Writer> all() {
        return repository.findAll();
    }


    @PostMapping("/writers")
    Writer newWriter(@RequestBody Writer newWriter) {
        return repository.save(newWriter);
    }

    // Single item
    @GetMapping("/writers/{id}")
    Resource<Writer> one(@PathVariable Long id) {
        Writer writer = repository.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id));
        return new Resource<>(writer,
                linkTo(methodOn(WriterController.class).one(id)).withSelfRel(),
                linkTo(methodOn(WriterController.class).all()).withRel("writers"));
    }

    @PutMapping("/writers/{id}")
    Writer replaceWriter(@RequestBody Writer newWriter, @PathVariable Long id) {

        return repository.findById(id)
                .map(writer -> {
                    writer.setName(newWriter.getName());
                    writer.setRole(newWriter.getRole());
                    return repository.save(writer);
                })
                .orElseGet(() -> {
                    newWriter.setId(id);
                    return repository.save(newWriter);
                });
    }

    @DeleteMapping("/writers/{id}")
    void deleteWriter(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
