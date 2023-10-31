package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.application.form.TagForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.service.EventService;
import jp.kobeu.cs27.localEvent.domain.service.TagService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * タグ登録が可能か確認する
     * タグが登録済みであった場合、タグ登録ページに戻る
     * 
     * 
     */
    @GetMapping("/event/confirm")
    public String confirmTagResistration(Model model, RedirectAttributes attributes,
            @ModelAttribute @Validated EventForm form, BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isEventFormError", true);

            return "redirect:/event";
        }

        // タグIDを変数に格納する
        final int eid = form.getEid();

        // タグが既に存在するか確認する
        if (eventService.existsEvent(eid)) {
            attributes.addFlashAttribute("isEventAlreadyExistsError", true);

            return "redirect:/event";
        }

        model.addAttribute("eid", eid);
        model.addAttribute("name", form.getName());
        model.addAttribute("description", form.getDescription());
        model.addAttribute("startday", form.getStartday());
        model.addAttribute("endday", form.getEndday());
        model.addAttribute("starttime", form.getStarttime());
        model.addAttribute("endtime", form.getEndtime());
        model.addAttribute("place", form.getPlace());
        model.addAttribute("fee", form.getFee());
        model.addAttribute("parking", form.isParking());
        model.addAttribute("access", form.getAccess());
        model.addAttribute("aid", form.getAid());
        model.addAttribute("organizer", form.getOrganizer());
        model.addAttribute("capacity", form.getCapacity());

        return "eventconfirm";

    }

    /**
     * タグを登録する
     */
    @PostMapping("/event/register")
    public String registerTag(Model model, RedirectAttributes attributes, @ModelAttribute @Validated EventForm form,
            BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isEventFormError", true);
            System.err.println("あ");
            return "redirect:/event";
        }

        // タグを登録する
        try {
            eventService.addEvent(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isEventAlreadyExistsError", true);
            return "redirect:/event";
        }

        return "redirect:/";

    }

}
