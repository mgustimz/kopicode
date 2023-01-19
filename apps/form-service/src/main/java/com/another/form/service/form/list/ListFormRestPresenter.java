package com.another.form.service.form.list;

import com.another.form.core.item.list.FormResponse;
import com.another.form.core.item.list.ListFormPresenter;
import com.another.form.core.item.list.ListFormResponse;

import java.util.List;

class ListFormRestPresenter implements ListFormPresenter {

    private ListFormResponse restResponse;

    public ListFormResponse getResponse() {
        return restResponse;
    }

    @Override
    public void present(List<FormResponse> response) {
        restResponse = new ListFormResponse(response);
    }
}
