package com.another.form.service.form.submit;

import com.another.form.client.request.submit.SubmitFormRestRequest;
import com.another.form.client.response.submit.SubmitFormRestResponse;
import com.another.form.core.item.submit.SubmitForm;
import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.service.constant.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubmitFormController {

    private final SubmitForm submitForm;

    @PostMapping(Routes.FORM)
    @ResponseStatus(HttpStatus.CREATED)
    public SubmitFormRestResponse create(@RequestBody SubmitFormRestRequest restRequest) {
        SubmitFormRequest itemRequest = getFormRequest(restRequest);
        SubmitFormRestPresenter restPresenter = new SubmitFormRestPresenter();
        submitForm.submit(itemRequest, restPresenter);

        return restPresenter.getResponse();
    }

    private SubmitFormRequest getFormRequest(SubmitFormRestRequest restRequest) {
        return new SubmitFormRequest(restRequest.getRegistrationPeriod(), restRequest.getFullName(), restRequest.getBirthDate(), restRequest.getPlaceOfBirth(), restRequest.getGender(), restRequest.getEmail());
    }

}
