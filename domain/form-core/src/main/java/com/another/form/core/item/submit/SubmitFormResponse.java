package com.another.form.core.item.submit;

import com.another.form.core.item.entity.Form;
import lombok.Value;

import java.sql.Timestamp;

@Value
public class SubmitFormResponse {

    Long id;

    String registrationPeriod;

    String fullName;

    Timestamp birthDate;

    String placeOfBirth;

    String gender;

    String email;

    public static SubmitFormResponse valueOf(Form createdForm) {
        return new SubmitFormResponse(
                createdForm.getId(),
                createdForm.getRegistrationPeriod(),
                createdForm.getFullName(),
                createdForm.getBirthDate(),
                createdForm.getPlaceOfBirth(),
                createdForm.getGender(),
                createdForm.getEmail()
        );
    }
}
