package com.another.form.service.form.list;

import com.another.form.core.item.list.FormResponse;
import com.another.form.core.item.list.ListForm;
import com.another.form.core.item.list.ListFormResponse;
import com.another.form.service.constant.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListFormController {

    private final ListForm listForm;

    @GetMapping(Routes.FORM)
    @ResponseStatus(HttpStatus.OK)
    public ListFormResponse create() {
        ListFormRestPresenter restPresenter = new ListFormRestPresenter();
        listForm.list(restPresenter);

        return restPresenter.getResponse();
    }

}
