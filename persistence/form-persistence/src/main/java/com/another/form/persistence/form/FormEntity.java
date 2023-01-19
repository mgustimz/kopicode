package com.another.form.persistence.form;

import com.another.form.core.item.submit.SubmitFormRequest;
import com.another.form.core.item.entity.Form;
import com.another.form.persistence.common.AuditTrail;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "forms")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormEntity extends AuditTrail implements Form, Serializable {

    private static final long serialVersionUID = 6689239907694270469L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String registrationPeriod;

    @Column
    private String fullName;

    @Column
    private Timestamp birthDate;

    @Column
    private String placeOfBirth;

    @Column
    private String gender;

    @Column
    private String email;

    public static FormEntity valueOf(SubmitFormRequest request) {
        return builder()
                .registrationPeriod(request.getRegistrationPeriod())
                .fullName(request.getFullName())
                .birthDate(request.getBirthDate())
                .placeOfBirth(request.getPlaceOfBirth())
                .gender(request.getGender())
                .email(request.getEmail())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FormEntity that = (FormEntity) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 1958895947;
    }
}
