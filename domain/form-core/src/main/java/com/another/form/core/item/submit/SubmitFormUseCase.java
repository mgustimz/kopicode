package com.another.form.core.item.submit;

import com.another.form.core.item.entity.Form;
import com.another.form.core.item.gateway.FormCommandGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubmitFormUseCase implements SubmitForm {

    private final FormCommandGateway formGateway;

    @Override
    public void submit(SubmitFormRequest request, SubmitFormPresenter presenter) {
        validateRequest(request, presenter);

        doCreate(request, presenter);
    }

    private void doCreate(SubmitFormRequest request, SubmitFormPresenter presenter) {
        Form submittedForm = formGateway.submit(request);
        SubmitFormResponse response = SubmitFormResponse.valueOf(submittedForm);

        presenter.present(response);
    }

    private void validateRequest(SubmitFormRequest request, SubmitFormPresenter presenter) {
        if (null == request) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (null == presenter) {
            throw new IllegalArgumentException("Presenter cannot be null");
        }

        request.validate();
    }
}
