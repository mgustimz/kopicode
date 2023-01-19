package com.another.form.persistence.item;

import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.entity.Form;
import com.another.form.persistence.common.Randomizer;
import com.another.form.persistence.form.DefaultFormCommandGateway;
import com.another.form.persistence.form.FormEntity;
import com.another.form.persistence.form.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FormCommandGatewayTest {

    private final SubmitFormRequest submitFormRequest = Randomizer.get(SubmitFormRequest.class);

    @InjectMocks
    private DefaultFormCommandGateway itemGateway;

    @Mock
    private FormRepository formRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenRequest_whenCreate_shouldCallItemRepository() {
        prepareAndExecuteRequest();

        ArgumentCaptor<FormEntity> captor = ArgumentCaptor.forClass(FormEntity.class);
        verify(formRepository).save(captor.capture());
        FormEntity actual = captor.getValue();

        assertThat(actual.getFullName()).isEqualTo(submitFormRequest.getFullName());
        assertThat(actual.getBirthDate()).isEqualTo(submitFormRequest.getBirthDate());
        assertThat(actual.getPlaceOfBirth()).isEqualTo(submitFormRequest.getPlaceOfBirth());
    }

    @Test
    void givenRequest_whenCreate_shouldReturnCorrectResult() {
        Form actual = prepareAndExecuteRequest();

        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getFullName()).isEqualTo(submitFormRequest.getFullName());
        assertThat(actual.getBirthDate()).isEqualTo(submitFormRequest.getBirthDate());
        assertThat(actual.getPlaceOfBirth()).isEqualTo(submitFormRequest.getPlaceOfBirth());
    }

    private Form prepareAndExecuteRequest() {
        stubSavedEntity();

        return itemGateway.submit(submitFormRequest);
    }

    private void stubSavedEntity() {
        FormEntity saved = FormEntity.valueOf(submitFormRequest);
        saved.setId(1L);

        when(formRepository.save(any())).thenReturn(saved);
    }
}
