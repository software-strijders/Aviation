package nl.prbed.hu.aviation.management.presentation.hateoas;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;

@AllArgsConstructor
public class HateoasDirector {
    private Builder builder;
    private Class<?> context;

    public void changeBuilder(Builder builder) {
        this.builder = builder;
    }

    public Iterable<Link> make(HateoasType type, String parameter) {
        builder = this.builder.create(context, parameter);
        return this.build(type).build();
    }

    public Iterable<Link> make(HateoasType type, String addon, String parameter) {
        builder = this.builder.create(context, addon, parameter);
        return this.build(type).build();
    }

    private Builder build(HateoasType type) {
        builder.selfLink();
        return switch (type) {
            case CREATE -> builder.findOneLink().findAllLink().updateLink().deleteLink();
            case UPDATE -> builder.findOneLink().findAllLink().deleteLink();
            case FIND_ONE -> builder.findAllLink().updateLink().deleteLink();
            case FIND_ALL -> builder;
        };
    }
}
