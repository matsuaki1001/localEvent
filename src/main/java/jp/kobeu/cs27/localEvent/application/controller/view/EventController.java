package jp.kobeu.cs27.localEvent.application.controller.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.application.form.EventTagForm;
import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.entity.User;
import jp.kobeu.cs27.localEvent.domain.service.AreaService;
import jp.kobeu.cs27.localEvent.domain.service.EventService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AreaService areaService;

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

        final int aid = form.getAid();
        try {
            Area area = areaService.getArea(aid);
            model.addAttribute("areaName", area.getName());
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isAreaNotFoundError", true);
            return "redirect:/event";
        }

        return "eventinputconfirm";

    }

    /**
     * イベントIDを受け取り、イベント詳細画面を表示する
     * 
     * @param model
     * @param attributes
     * @param form
     * @param bindingResult
     * @return
     * 
     */

    @GetMapping("/event/detail")
    public String showEventDetail(Model model, RedirectAttributes attributes, @RequestParam("eid") int eid) {

        // イベントが存在しない場合は例外を投げる
        if (!eventService.existsEvent(eid)) {
            attributes.addFlashAttribute("isEventNotFoundError", true);
            return "redirect:/eventlist";
        }

        // イベントを取得する
        Event event = eventService.getEvent(eid);
        model.addAttribute("eid", event.getEid());
        model.addAttribute("name", event.getName());
        model.addAttribute("description", event.getDescription());
        model.addAttribute("startday", event.getStartday());
        model.addAttribute("endday", event.getEndday());
        model.addAttribute("starttime", event.getStarttime());
        model.addAttribute("endtime", event.getEndtime());
        model.addAttribute("place", event.getPlace());
        model.addAttribute("fee", event.getFee());
        model.addAttribute("parking", event.isParking());
        model.addAttribute("access", event.getAccess());
        model.addAttribute("aid", event.getAid());
        model.addAttribute("organizer", event.getOrganizer());
        model.addAttribute("capacity", event.getCapacity());

        final int aid = event.getAid();
        try {
            Area area = areaService.getArea(aid);
            model.addAttribute("areaName", area.getName());
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isAreaNotFoundError", true);
            return "redirect:/eventlist";
        }

        return "eventdetail";

    }

    /**
     * イベントを登録する
     */
    @PostMapping("/event/register")
    public String registerTag(Model model, RedirectAttributes attributes, @ModelAttribute @Validated EventForm form,
            BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、イベント登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isEventFormError", true);
            return "redirect:/event";
        }

        // イベントを登録する
        try {
            eventService.addEvent(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isEventAlreadyExistsError", true);
            return "redirect:/event";
        }

        return "redirect:/";

    }

    /**
     * イベントとタグを紐付ける
     */
    @PostMapping("/event/tag")
    public String connectEventTag(Model model, RedirectAttributes attributes,
            @ModelAttribute @Validated EventTagForm form,
            BindingResult bindingResult) {

        // イベントIDとタグIDを変数に格納する
        final int eid = form.getEid();
        final int tid = form.getTid();

        // タグが既に存在するか確認する
        if (eventService.existsEventTag(eid, tid)) {
            attributes.addFlashAttribute("isEventTagAlreadyExistsError", true);
            return "redirect:/eventlist";
        }

        // フォームにバリデーション違反があった場合、タグ登録ページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isTagFormError", true);
            return "redirect:/eventlist";
        }

        // タグとイベントを紐付ける
        try {
            eventService.addTagToEvent(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isTagAlreadyExistsError", true);
            return "redirect:/eventlist";
        }

        return "redirect:/eventlist";

    }

    /**
     * イベントを削除する
     */
    @GetMapping("/event/delete/{eid}")
    public String deleteEvent(Model model, RedirectAttributes attributes, @PathVariable("eid") int eid) {

        // イベントが存在しない場合は例外を投げる
        if (!eventService.existsEvent(eid)) {
            attributes.addFlashAttribute("isEventNotFoundError", true);
            return "redirect:/eventlist";
        }

        // イベントを取得する
        Event event = eventService.getEvent(eid);
        model.addAttribute("eid", event.getEid());
        model.addAttribute("name", event.getName());
        return "eventdeleteconfirm";

    }

    /**
     * イベント削除確認画面を表示する
     */
    @GetMapping("/event/delete/confirm/{eid}")
    public String showDeleteEventConfirmPage(Model model, RedirectAttributes attributes,
            @PathVariable("eid") int eid) {

        // イベントが存在しない場合は例外を投げる
        if (!eventService.existsEvent(eid)) {
            attributes.addFlashAttribute("isEventNotFoundError", true);
            return "redirect:/eventlist";
        }
        // イベントを削除する
        eventService.deleteEvent(eid);
        return "redirect:/eventlist";
    }

    /**
     * イベントを更新する
     */
    @GetMapping("/event/update/confirm/{eid}")
    public String updateEvent(Model model, RedirectAttributes attributes, @PathVariable("eid") int eid,
            @ModelAttribute @Validated EventForm form, BindingResult bindingResult) {

        // フォームにバリデーション違反があった場合、イベントページに戻る
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("isEventFormError", true);

            return "redirect:/eventlist";
        }

        form.setEid(eid);
        // イベントを更新する
        try {
            eventService.updateEvent(form);
        } catch (ValidationException e) {
            attributes.addFlashAttribute("isEventAlreadyExistsError", true);
            return "redirect:/eventlist";
        }
        attributes.addFlashAttribute("isEventUpdateSuccess", true);
        return "redirect:/event/update/" + eid;

    }

    /**
     * イベントの更新のページを表示する
     */
    @GetMapping("/event/update/{eid}")
    public String confirmUserUpdate(
            Model model,
            RedirectAttributes attributes,
            @PathVariable("eid") int eid) {

        // イベントが存在しない場合は例外を投げる
        if (!eventService.existsEvent(eid)) {
            attributes.addFlashAttribute("isEventNotFoundError", true);
            return "redirect:/eventlist";
        }

        // イベントを取得する
        Event event = eventService.getEvent(eid);
        List<Area> areaList = areaService.getAllareas();

        // イベント情報をモデルに格納する
        model.addAttribute(new EventForm());
        model.addAttribute("eid", event.getEid());
        model.addAttribute("name", event.getName());
        model.addAttribute("description", event.getDescription());
        model.addAttribute("startday", event.getStartday());
        model.addAttribute("endday", event.getEndday());
        model.addAttribute("starttime", event.getStarttime());
        model.addAttribute("endtime", event.getEndtime());
        model.addAttribute("place", event.getPlace());
        model.addAttribute("fee", event.getFee());
        model.addAttribute("parking", event.isParking());
        model.addAttribute("access", event.getAccess());
        model.addAttribute("aid", event.getAid());
        model.addAttribute("organizer", event.getOrganizer());
        model.addAttribute("capacity", event.getCapacity());
        model.addAttribute("areaList", areaList);

        // イベント情報更新ページ
        return "eventupdate";
    }

}
