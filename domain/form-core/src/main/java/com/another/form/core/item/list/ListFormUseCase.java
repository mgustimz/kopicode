package com.another.form.core.item.list;

import com.another.form.core.item.entity.Form;
import com.another.form.core.item.gateway.FormCommandGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListFormUseCase implements ListForm {

    private final FormCommandGateway formGateway;

    @Override
    public void list(ListFormPresenter presenter) {
        validateRequest(presenter);

        doList(presenter);
    }

    private void doList(ListFormPresenter presenter) {
        List<Form> submittedForm = formGateway.list();
        List<FormResponse> response = submittedForm.stream()
                .map(FormResponse::valueOf)
                .collect(Collectors.toList());

        presenter.present(response);
    }

    private void validateRequest(ListFormPresenter presenter) {
        if (null == presenter) {
            throw new IllegalArgumentException("Presenter cannot be null");
        }
    }
}
