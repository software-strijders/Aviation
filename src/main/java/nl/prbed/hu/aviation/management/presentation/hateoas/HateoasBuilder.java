package nl.prbed.hu.aviation.management.presentation.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class HateoasBuilder implements Builder {
    private final List<Link> links = new ArrayList<>();
    private Class<?> context;
    private String function = "";
    private String parameter;

    @Override
    public Builder create(Class<?> context, String function, String parameter) {
        this.links.clear();
        this.context = context;
        this.function = function;
        this.parameter = parameter;
        return this;
    }

    @Override
    public Builder create(Class<?> context, String parameter) {
        this.links.clear();
        this.context = context;
        this.function = "";
        this.parameter = parameter;
        return this;
    }

    @Override
    public Builder selfLink() {
        links.add(linkTo(context).slash(function).withSelfRel());
        return this;
    }

    @Override
    public Builder createLink() {
        links.add(linkTo(context).withRel("create"));
        return this;
    }

    @Override
    public Builder updateLink() {
        links.add(linkTo(context).slash(function).slash(parameter).withRel("update"));
        return this;
    }

    @Override
    public Builder deleteLink() {
        links.add(linkTo(context).slash(function).slash(parameter).withRel("delete"));
        return this;
    }

    @Override
    public Builder findOneLink() {
        links.add(linkTo(context).slash(function).slash(parameter).withRel("find"));
        return this;
    }

    @Override
    public Builder findAllLink() {
        links.add(linkTo(context).slash(function).withRel("findAll"));
        return this;
    }

    @Override
    public Builder link(Link link) {
        links.add(link);
        return this;
    }

    @Override
    public Iterable<Link> build() {
        return links;
    }
}
