package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobeu.cs27.localEvent.application.form.TagForm;
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
    public String addTag(Model model, REdirestAttributes attributes,
    @ModelAttribute @Validated TagForm form, BindingResult binding) {

        // タグを登録する
        tagService.addTag(form);

        // タグ一覧画面にリダイレクトする
        return "redirect:/tags";

    }

}
