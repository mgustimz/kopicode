package com.another.form.persistence.item;

import com.another.form.persistence.config.JpaTestConfiguration;
import com.another.form.persistence.form.FormEntity;
import com.another.form.persistence.form.FormRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = JpaTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FormRepositoryTest {

    @Autowired
    private FormRepository formRepository;

    @Test
    void givenEntity_whenSave_shouldSaveToRepository() {
        saveEntity();

        assertThat(formRepository.count()).isEqualTo(1);
    }

    @Test
    void givenEntity_whenSave_shouldAddAuditEntity() {
        FormEntity actual = saveEntity();

        assertThat(actual.getCreateDate()).isNotNull();
        assertThat(actual.getCreatedBy()).isEqualTo("SYSTEM");
        assertThat(actual.getLastModifiedDate()).isNotNull();
        assertThat(actual.getLastModifiedBy()).isEqualTo("SYSTEM");
    }

    private FormEntity saveEntity() {
        FormEntity itemEntity = getItemEntity();

        return formRepository.save(itemEntity);
    }

    private FormEntity getItemEntity() {
        FormEntity formEntity = new FormEntity();
        formEntity.setFullName("dummy");
        formEntity.setBirthDate(new Timestamp(System.currentTimeMillis()));
        formEntity.setPlaceOfBirth("dummyPlace");
        return formEntity;
    }
}
