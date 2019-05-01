package io.learning.hs.mooc;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Component
public class WriterResourceAssembler implements ResourceAssembler<Writer, Resource<Writer>> {
    @Override
    public Resource<Writer> toResource(Writer writer) {
        return new Resource<>(writer,
                linkTo(methodOn(WriterController.class).one(writer.getId())).withSelfRel(),
                linkTo(methodOn(WriterController.class).all()).withRel("writers"));
    }
}
