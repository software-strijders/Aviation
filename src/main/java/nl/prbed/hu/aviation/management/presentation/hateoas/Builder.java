package nl.prbed.hu.aviation.management.presentation.hateoas;

import org.springframework.hateoas.Link;

public interface Builder {
    public Builder create(Class<?> context, String addon, String parameter);
    public Builder create(Class<?> context, String parameter);
    public Builder selfLink();
    public Builder createLink();
    public Builder updateLink();
    public Builder deleteLink();
    public Builder findOneLink();
    public Builder findAllLink();
    public Builder link(Link link);
    public Iterable<Link> build();

}
