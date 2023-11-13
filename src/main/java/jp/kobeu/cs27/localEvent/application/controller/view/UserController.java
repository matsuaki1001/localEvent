package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.application.form.UserTagForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import jp.kobeu.cs27.localEvent.domain.service.AreaService;
import jp.kobeu.cs27.localEvent.domain.service.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AreaService areaService;

    /**
     * ユーザ登録が可能か確認する
     * ユーザが登録済みであった場合、ユーザ登録ページに戻る
     * 
     * 
     */
    @GetMapping("/user/confirm")
    public String confirmAreaResistration(Model model, RedirectAttributes attributes,
            @ModelAttribute @Validated UserForm form, BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isUserFormError", true);

            return "redirect:/user";
        }

        // タグIDを変数に格納する
        final int uid = form.getUid();

        // タグが既に存在するか確認する
        if (userService.existsUser(uid)) {
            attributes.addFlashAttribute("isUserAlreadyExistsError", true);

            return "redirect:/user";
        }

        model.addAttribute("uid", uid);
        model.addAttribute("name", form.getName());
        model.addAttribute("email", form.getEmail());
        model.addAttribute("password", form.getPassword());
        model.addAttribute("aid", form.getAid());

        int aid = form.getAid();

        Area area;
        try {
            area = areaService.getArea(aid);
            model.addAttribute("areaName", area.getName());
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isAreaNotFoundError", true);
            return "redirect:/user";
        }

        return "userinputconfirm";

    }

    /**
     * ユーザを登録する
     */
    @PostMapping("/user/register")
    public String registerArea(Model model, RedirectAttributes attributes, @ModelAttribute @Validated UserForm form,
            BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isUserFormError", true);

            return "redirect:/user";
        }

        // タグを登録する
        try {
            userService.addUser(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isUserAlreadyExistsError", true);
            return "redirect:/user";
        }

        return "redirect:/";

    }

    /**
     * ユーザとタグを紐付ける
     */
    @PostMapping("/user/tag")
    public String connectUserTag(Model model, RedirectAttributes attributes,
            @ModelAttribute @Validated UserTagForm form,
            BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isUserFormError", true);

            return "redirect:/user";
        }

        // タグを登録する
        try {
            userService.addTagToUser(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isUserAlreadyExistsError", true);
            return "redirect:/user";
        }

        return "redirect:/";

    }

  




}
