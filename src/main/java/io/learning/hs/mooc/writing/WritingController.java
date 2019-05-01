package io.learning.hs.mooc.writing;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class WritingController {

    private final WritingRepository writingRepository;
    private final WritingResourceAssembler assembler;

    public WritingController(WritingRepository writingRepository, WritingResourceAssembler assembler) {
        this.writingRepository = writingRepository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/writings", produces = "application/json; charset=UTF-8")
    Resources<Resource<Writing>> all() {
        List<Resource<Writing>> writings = writingRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(writings,
                linkTo(methodOn(WritingController.class).all()).withSelfRel());
    }

    @GetMapping(value ="/writings/{id}", produces = "application/json; charset=UTF-8")
    Resource<Writing> one(@PathVariable Long id) {
        return assembler.toResource(
                writingRepository.findById(id)
                .orElseThrow(() -> new WritingNotFoundException(id)));
    }

    @PostMapping("/writings")
    ResponseEntity<Resource<Writing>> newWriting(@RequestBody Writing writing) {
        writing.setStatus(Status.IN_PROGRESS);
        Writing newWriting = writingRepository.save(writing);

        return ResponseEntity
                .created(linkTo(methodOn(WritingController.class).one(newWriting.getId())).toUri())
                .body(assembler.toResource(newWriting));
    }

    @PutMapping("/writings/{id}/complete")
    ResponseEntity<ResourceSupport> complete(@PathVariable Long id) {
        Writing writing = writingRepository.findById(id)
                .orElseThrow(() -> new WritingNotFoundException(id));

        if(writing.getStatus() == Status.IN_PROGRESS) {
            writing.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toResource(writingRepository.save(writing)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed",
                        "You can't complete an order that is in the " + writing.getStatus() + " status"));
    }

    @DeleteMapping("/writings/{id}/delete")
    ResponseEntity<ResourceSupport> delete(@PathVariable Long id) {
        Writing writing = writingRepository.findById(id)
                .orElseThrow(() -> new WritingNotFoundException(id));

        if (writing.getStatus() == Status.IN_PROGRESS) {
            writing.setStatus(Status.DELETED);
            return ResponseEntity.ok(assembler.toResource(writingRepository.save(writing)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed",
                        "You can't delete an writing that is in the " + writing.getStatus() + " status"));

    }
}
