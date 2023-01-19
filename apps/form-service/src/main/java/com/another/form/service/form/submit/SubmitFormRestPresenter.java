package com.another.form.service.form.submit;

import com.another.form.client.response.submit.SubmitFormRestResponse;
import com.another.form.core.item.submit.SubmitFormPresenter;
import com.another.form.core.item.submit.SubmitFormResponse;

class SubmitFormRestPresenter implements SubmitFormPresenter {

    private SubmitFormRestResponse restResponse;

    @Override
    public void present(SubmitFormResponse response) {
        restResponse = new SubmitFormRestResponse(response.getId());
    }

    public SubmitFormRestResponse getResponse() {
        return restResponse;
    }
}
