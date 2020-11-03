package nl.prbed.hu.aviation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetaDataContributor implements InfoContributor {

    private final ApplicationContext ctx;

    public MetaDataContributor(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("bean-definition-count", ctx.getBeanDefinitionCount());
        details.put("startup-date", ctx.getStartupDate());

        builder.withDetail("context", details);
    }
}
