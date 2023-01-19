package com.another.form.service.config;

import com.another.form.core.item.gateway.FormCommandGateway;
import com.another.form.persistence.form.DefaultFormCommandGateway;
import com.another.form.persistence.form.FormRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EntityScan(basePackages = "com.another.form.persistence")
@EnableJpaRepositories(basePackages = "com.another.form.persistence")
@EnableJpaAuditing
public class JpaConfiguration {

    @Bean
    public FormCommandGateway formGateway(FormRepository formRepository) {
        return new DefaultFormCommandGateway(formRepository);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("SYSTEM");
    }
}
