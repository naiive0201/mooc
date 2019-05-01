package io.learning.hs.mooc;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

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

    private final WriterResourceAssembler assembler;

    public WriterController(WriterRepository repository, WriterResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping(value = "/writers", produces = "application/json; charset=UTF-8")
    Resources<Resource<Writer>> all() {
        List<Resource<Writer>> writers = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(writers,
                linkTo(methodOn(WriterController.class).all()).withSelfRel());
    }


    @PostMapping(value = "/writers", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> newEmployee(@RequestBody Writer newWriter) throws URISyntaxException {
        Resource<Writer> resource = assembler.toResource(repository.save(newWriter));

        return  ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    // Single item
    @GetMapping(value = "/writers/{id}", produces = "application/json; charset=UTF-8")
    Resource<Writer> one(@PathVariable Long id) {
        Writer writer = repository.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id));
        return assembler.toResource(writer);
    }

    @PutMapping("/writers/{id}")
    ResponseEntity<?>  replaceWriter(@RequestBody Writer newWriter, @PathVariable Long id) throws URISyntaxException {

        Writer updateWriter = repository.findById(id)
                .map(writer -> {
                    writer.setName(newWriter.getName());
                    writer.setRole(newWriter.getRole());
                    return repository.save(writer);
                })
                .orElseGet(() -> {
                    newWriter.setId(id);
                    return repository.save(newWriter);
                });

        Resource<Writer> resource = assembler.toResource(updateWriter);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);

    }

    @DeleteMapping("/writers/{id}")
    void deleteWriter(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
