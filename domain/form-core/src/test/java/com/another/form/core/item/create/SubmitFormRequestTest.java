package com.another.form.core.item.create;

import com.another.form.core.item.submit.SubmitFormRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubmitFormRequestTest {

    @Test
    void givenNullName_whenCreateItemRequest_shouldThrowException() {
        SubmitFormRequest request = new SubmitFormRequest(null, "dummy", new Timestamp(System.currentTimeMillis()), "dummy", "dummy", "dummy@gmail.com");

        Exception e = assertThrows(ConstraintViolationException.class, request::validate);

        assertThat(e.getMessage()).isEqualTo("registrationPeriod: Registration period cannot be empty");
    }

    @Test
    void givenNullPrice_whenCreateItemRequest_shouldThrowException() {
        SubmitFormRequest request = new SubmitFormRequest("dummy", "", new Timestamp(System.currentTimeMillis()), "dummy", "dummy", "dummy@gmail.com");

        Exception e = assertThrows(ConstraintViolationException.class, request::validate);

        assertThat(e.getMessage()).isEqualTo("fullName: Full name cannot be empty");
    }
}
