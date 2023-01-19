package com.another.form.core.item.gateway;

import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.entity.Form;

import java.util.List;

public interface FormCommandGateway {

    Form submit(SubmitFormRequest request);

    List<Form> list();
}
