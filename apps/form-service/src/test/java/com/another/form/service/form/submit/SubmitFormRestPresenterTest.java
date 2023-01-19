package com.another.form.service.form.submit;

import com.another.form.service.common.Randomizer;
import com.another.form.client.response.submit.SubmitFormRestResponse;
import com.another.form.core.item.submit.SubmitFormResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubmitFormRestPresenterTest {

    private final SubmitFormRestPresenter restPresenter = new SubmitFormRestPresenter();

    @Test
    void givenRequest_whenPresent_shouldReturnCorrectResult() {
        SubmitFormResponse response = Randomizer.get(SubmitFormResponse.class);

        restPresenter.present(response);
        SubmitFormRestResponse restResponse = restPresenter.getResponse();

        assertThat(restResponse.getId()).isEqualTo(response.getId());
    }
}
