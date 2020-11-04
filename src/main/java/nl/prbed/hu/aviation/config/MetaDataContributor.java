package nl.prbed.hu.aviation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MetaDataContributor implements InfoContributor {
    private final ApplicationContext ctx;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("bean-definition-count", this.ctx.getBeanDefinitionCount());
        details.put("startup-date", this.ctx.getStartupDate());

        builder.withDetail("context", details);
    }
}
