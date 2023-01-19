package com.another.form.persistence.form;

import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.entity.Form;
import com.another.form.core.item.gateway.FormCommandGateway;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DefaultFormCommandGateway implements FormCommandGateway {

    private final FormRepository formRepository;

    @Override
    public Form submit(SubmitFormRequest request) {
        FormEntity entity = FormEntity.valueOf(request);

        return formRepository.save(entity);
    }

    @Override
    public List<Form> list() {
        List<FormEntity> formEntities = formRepository.findAll();
        return new ArrayList<>(formEntities);
    }
}
