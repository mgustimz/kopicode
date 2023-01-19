package com.another.form.core.item.create;

import com.another.form.core.common.Randomizer;
import com.another.form.core.item.entity.Form;
import com.another.form.core.item.gateway.FormCommandGateway;
import com.another.form.core.item.submit.SubmitFormPresenter;
import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.submit.SubmitFormResponse;
import com.another.form.core.item.submit.SubmitFormUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubmitFormUseCaseTest {

    private final SubmitFormRequest request = Randomizer.get(SubmitFormRequest.class);

    @InjectMocks
    private SubmitFormUseCase useCase;

    @Mock
    private FormCommandGateway formCommandGateway;

    @Mock
    private SubmitFormPresenter presenter;

    @Mock
    private Form form;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNullRequest_whenCreate_shouldThrowException() {
        Executable task = () -> useCase.submit(null, presenter);

        Exception e = assertThrows(IllegalArgumentException.class, task);

        assertThat(e.getMessage()).isEqualTo("Request cannot be null");
    }

    @Test
    void givenNullPresenter_whenCreate_shouldThrowException() {
        Executable task = () -> useCase.submit(request, null);

        Exception e = assertThrows(IllegalArgumentException.class, task);

        assertThat(e.getMessage()).isEqualTo("Presenter cannot be null");
    }

    @Test
    void givenNullName_whenCreate_shouldThrowException() {
        SubmitFormRequest itemRequest = new SubmitFormRequest("dummy", "", new Timestamp(System.currentTimeMillis()), "dummy", "dummy", "dummy@gmail.com");
        Executable task = () -> useCase.submit(itemRequest, presenter);

        Exception e = assertThrows(ConstraintViolationException.class, task);

        assertThat(e.getMessage()).isEqualTo("fullName: Full name cannot be empty");
    }

    @Test
    void givenRequest_whenCreate_shouldCallItemGateway() {
        prepareAndExecute();

        verify(formCommandGateway).submit(new SubmitFormRequest("dummy", "dummy", Timestamp.valueOf("2023-01-19 17:38:48.602"), "dummy", "dummy", "dummy@gmail.com"));
    }

    @Test
    void givenRequest_whenCreate_shouldCallPresenter() {
        prepareAndExecute();

        verify(presenter).present(any());
    }

    @Test
    void givenRequest_whenCreate_shouldCallPresenterWithCorrectResponse() {
        prepareAndExecute();

        ArgumentCaptor<SubmitFormResponse> captor = ArgumentCaptor.forClass(SubmitFormResponse.class);
        verify(presenter).present(captor.capture());
        SubmitFormResponse actual = captor.getValue();

        assertThat(actual).isNotNull();
    }

    private void prepareAndExecute() {
        stubItem();

        useCase.submit(new SubmitFormRequest("dummy", "dummy", Timestamp.valueOf("2023-01-19 17:38:48.602"), "dummy", "dummy", "dummy@gmail.com"), presenter);
    }

    private void stubItem() {
        when(form.getId()).thenReturn(1L);
        when(form.getFullName()).thenReturn("dummy");
        when(form.getBirthDate()).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(form.getPlaceOfBirth()).thenReturn("dummy");
        when(form.getRegistrationPeriod()).thenReturn("dummy");
        when(form.getGender()).thenReturn("dummy");
        when(form.getEmail()).thenReturn("dummy@gmail.com");

        when(formCommandGateway.submit(any())).thenReturn(form);
    }
}
