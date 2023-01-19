package com.another.form.core.item.list;

import com.another.form.core.item.entity.Form;
import lombok.Value;

import java.sql.Timestamp;

@Value
public class FormResponse {

    Long id;

    String registrationPeriod;

    String fullName;

    Timestamp birthDate;

    String placeOfBirth;

    String gender;

    String email;

    public static FormResponse valueOf(Form createdForm) {
        return new FormResponse(
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
