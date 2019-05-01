package io.learning.hs.mooc.writing;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Component
public class WritingResourceAssembler implements ResourceAssembler<Writing, Resource<Writing>> {

    @Override
    public Resource<Writing> toResource(Writing writing) {
        // Unconditional links to single-item resource and aggregate root

        Resource<Writing> writingResource = new Resource<>(writing,
                linkTo(methodOn(WritingController.class).one(writing.getId())).withSelfRel(),
                linkTo(methodOn(WritingController.class).all()).withRel("writings"));
            // Conditional links based on state of the order
            if (writing.getStatus() == Status.IN_PROGRESS) {
                writingResource.add(
                        linkTo(methodOn(WritingController.class)
                                .delete(writing.getId())).withRel("delete"));
                writingResource.add(
                        linkTo(methodOn(WritingController.class)
                                .complete(writing.getId())).withRel("complete"));

            }

        return writingResource;
    }
}
