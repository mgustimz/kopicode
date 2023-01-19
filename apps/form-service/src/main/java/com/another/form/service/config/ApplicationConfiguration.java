package com.another.form.service.config;

import com.another.form.core.item.gateway.FormCommandGateway;
import com.another.form.core.item.submit.SubmitForm;
import com.another.form.core.item.submit.SubmitFormUseCase;
import com.another.form.core.item.list.ListForm;
import com.another.form.core.item.list.ListFormUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SubmitForm submitForm(FormCommandGateway formGateway) {
        return new SubmitFormUseCase(formGateway);
    }

    @Bean
    public ListForm listForm(FormCommandGateway formCommandGateway) {
        return new ListFormUseCase(formCommandGateway);
    }
}
