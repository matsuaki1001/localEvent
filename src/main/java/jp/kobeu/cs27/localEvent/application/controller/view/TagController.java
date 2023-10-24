package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobeu.cs27.localEvent.application.form.TagForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.service.TagService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    

}
