package com.another.product.core.item.create;

import com.another.product.core.common.Randomizer;
import com.another.product.core.item.gateway.ItemCommandGateway;
import com.another.product.core.item.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateItemUseCaseTest {

    private final CreateItemRequest request = Randomizer.get(CreateItemRequest.class);

    @InjectMocks
    private CreateItemUseCase useCase;

    @Mock
    private ItemCommandGateway itemCommandGateway;

    @Mock
    private CreateItemPresenter presenter;

    @Mock
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNullRequest_whenCreate_shouldThrowException() {
        Executable task = () -> useCase.create(null, presenter);

        Exception e = assertThrows(IllegalArgumentException.class, task);

        assertThat(e.getMessage()).isEqualTo("Request cannot be null");
    }

    @Test
    void givenNullPresenter_whenCreate_shouldThrowException() {
        Executable task = () -> useCase.create(request, null);

        Exception e = assertThrows(IllegalArgumentException.class, task);

        assertThat(e.getMessage()).isEqualTo("Presenter cannot be null");
    }

    @Test
    void givenNullName_whenCreate_shouldThrowException() {
        CreateItemRequest itemRequest = new CreateItemRequest(null, BigDecimal.ONE, null);
        Executable task = () -> useCase.create(itemRequest, presenter);

        Exception e = assertThrows(ConstraintViolationException.class, task);

        assertThat(e.getMessage()).isEqualTo("name: Name cannot be empty");
    }

    @Test
    void givenRequest_whenCreate_shouldCallItemGateway() {
        prepareAndExecute();

        verify(itemCommandGateway).create(request);
    }

    @Test
    void givenRequest_whenCreate_shouldCallPresenter() {
        prepareAndExecute();

        verify(presenter).present(any());
    }

    @Test
    void givenRequest_whenCreate_shouldCallPresenterWithCorrectResponse() {
        prepareAndExecute();

        ArgumentCaptor<CreateItemResponse> captor = ArgumentCaptor.forClass(CreateItemResponse.class);
        verify(presenter).present(captor.capture());
        CreateItemResponse actual = captor.getValue();

        assertThat(actual).isNotNull();
    }

    private void prepareAndExecute() {
        stubItem();

        useCase.create(request, presenter);
    }

    private void stubItem() {
        when(item.getId()).thenReturn(1L);
        when(item.getName()).thenReturn("dummy");
        when(item.getPrice()).thenReturn(BigDecimal.ONE);
        when(item.getImageUrl()).thenReturn("dummy");

        when(itemCommandGateway.create(any())).thenReturn(item);
    }
}
