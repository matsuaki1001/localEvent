package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    /**
     * タグを登録する
     * 
     * @param form
     * @return タグ一覧画面
     */
    @PostMapping("/tags")
    public String addTag(Model model, RedirectAttributes attributes,
    @ModelAttribute @Validated TagForm form, BindingResult bindingResult) {

        // 入力チェックエラーがある場合は、入力画面に戻す
        if(bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isTagFormError", true);
            return "redirect:/";

        }

        //タグを登録する
        try{
            tagService.addTag(form);
        }catch (ValidationException e) {
            //タグが既に存在する場合は、エラーフラグをオンにする
            attributes.addFlashAttribute("isTagAlreadyExists", true);
            return "redirect:/";
        }

        //タグ一覧画面にリダイレクトする
        return "tagpage";

    }

}
