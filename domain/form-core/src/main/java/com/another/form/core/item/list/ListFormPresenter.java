package com.another.form.core.item.list;

import java.util.List;

@FunctionalInterface
public interface ListFormPresenter {

    void present(List<FormResponse> response);
}
