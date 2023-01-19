package com.another.form.service.form.submit;

import com.another.form.client.request.submit.SubmitFormRestRequest;
import com.another.form.client.response.submit.SubmitFormRestResponse;
import com.another.form.core.item.submit.SubmitForm;
import com.another.form.core.item.submit.SubmitFormPresenter;
import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.submit.SubmitFormResponse;
import com.another.form.service.common.Randomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

class SubmitFormControllerTest {

    private final SubmitFormRestRequest restRequest = Randomizer.get(SubmitFormRestRequest.class);

    @InjectMocks
    private SubmitFormController controller;

    @Mock
    private SubmitForm submitForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenRequest_whenCreate_shouldCallCreateItem() {
        controller.create(restRequest);

        verify(submitForm).submit(any(), any());
    }

    @Test
    void givenRequest_whenCreate_shouldCallCreateItemWithCorrectRequest() {
        controller.create(restRequest);

        ArgumentCaptor<SubmitFormRequest> captor = ArgumentCaptor.forClass(SubmitFormRequest.class);
        verify(submitForm).submit(captor.capture(), any());
        SubmitFormRequest actual = captor.getValue();

        assertThat(actual.getFullName()).isEqualTo(restRequest.getFullName());
        assertThat(actual.getRegistrationPeriod()).isEqualTo(restRequest.getRegistrationPeriod());
        assertThat(actual.getBirthDate()).isEqualTo(restRequest.getBirthDate());
        assertThat(actual.getPlaceOfBirth()).isEqualTo(restRequest.getPlaceOfBirth());
        assertThat(actual.getGender()).isEqualTo(restRequest.getGender());
        assertThat(actual.getEmail()).isEqualTo(restRequest.getEmail());
    }

    @Test
    void givenRequest_whenCreate_shouldCallCreateItemWithCorrectPresenter() {
        controller.create(restRequest);

        ArgumentCaptor<SubmitFormPresenter> captor = ArgumentCaptor.forClass(SubmitFormPresenter.class);
        verify(submitForm).submit(any(), captor.capture());
        SubmitFormPresenter actual = captor.getValue();

        assertThat(actual).isInstanceOf(SubmitFormRestPresenter.class);
    }

    @Test
    void givenRequest_whenCreate_shouldReturnCorrectResult() {
        stubResponse();

        Object actual = controller.create(restRequest);

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(SubmitFormRestResponse.class);
    }

    private void stubResponse() {
        doAnswer(this::getResponseAnswer).when(submitForm).submit(any(), any());
    }

    private Void getResponseAnswer(InvocationOnMock invocation) {
        SubmitFormRestPresenter restPresenter = invocation.getArgument(1);
        SubmitFormResponse response = Randomizer.get(SubmitFormResponse.class);

        restPresenter.present(response);

        return null;
    }
}
