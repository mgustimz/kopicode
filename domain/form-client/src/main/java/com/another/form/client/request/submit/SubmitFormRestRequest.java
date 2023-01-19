package com.another.form.client.request.submit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Timestamp;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class SubmitFormRestRequest {

    String registrationPeriod;

    String fullName;

    Timestamp birthDate;

    String placeOfBirth;

    String gender;

    String email;
}
