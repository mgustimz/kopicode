package com.another.form.core.item.submit;

import com.another.common.validator.ValidationAware;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Value
@EqualsAndHashCode(callSuper = false)
public class SubmitFormRequest extends ValidationAware<SubmitFormRequest> {

    @NotBlank(message = "Registration period cannot be empty")
    String registrationPeriod;

    @NotBlank(message = "Full name cannot be empty")
    String fullName;

    @NotNull(message = "Birth date cannot be empty")
    Timestamp birthDate;

    @NotBlank(message = "Place of birth cannot be empty")
    String placeOfBirth;

    @NotBlank(message = "Gender cannot be empty")
    String gender;

    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    String email;
}
