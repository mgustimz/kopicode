package com.another.form.client.response.list;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class FormRestResponse {

    Long id;

    String registrationPeriod;

    String fullName;

    Timestamp birthDate;

    String placeOfBirth;

    String gender;

    String email;
}
