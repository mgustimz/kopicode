package com.another.form.core.item.entity;

import java.sql.Timestamp;

public interface Form {

    Long getId();

    String getRegistrationPeriod();

    String getFullName();

    Timestamp getBirthDate();

    String getPlaceOfBirth();

    String getGender();

    String getEmail();
}
