package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import jp.kobeu.cs27.localEvent.application.form.AreaForm;
import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.application.form.EventTagForm;
import jp.kobeu.cs27.localEvent.application.form.TagForm;
import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.application.form.UserTagForm;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.entity.User;
import jp.kobeu.cs27.localEvent.domain.service.AreaService;
import jp.kobeu.cs27.localEvent.domain.service.EventService;
import jp.kobeu.cs27.localEvent.domain.service.UserService;
import jp.kobeu.cs27.localEvent.application.form.TagForm;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PageController {

   /**
    * ログイン画面を表示する
    */

   private final AreaService areaService;
   private final UserService userService;
   private final EventService eventService;

   @GetMapping("/")
   public String loginPage() {

      return "index";
   }

   @GetMapping("/tag")
   public String showTagPage(Model model) {

      model.addAttribute(new TagForm());

      return "tagpage";
   }

   @GetMapping("/area")
   public String showAreaPage(Model model) {

      model.addAttribute(new AreaForm());

      return "areapage";
   }

   @GetMapping("/event")
   public String showEventPage(Model model) {

      List<Area> areaList = areaService.getAllareas();
      model.addAttribute(new EventForm());
      model.addAttribute("areaList", areaList);

      return "eventpage";
   }

   @GetMapping("/user")
   public String showUserPage(Model model) {

      List<Area> areaList = areaService.getAllareas();
      model.addAttribute("areaList", areaList);
      model.addAttribute(new UserForm());

      return "userpage";
   }

   @GetMapping("/userlist")
   public String showUserListPage(Model model, RedirectAttributes attributes,
         @ModelAttribute @Validated UserTagForm form,
         BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {
         attributes.addFlashAttribute("isUserTagFormError", true);
         return "redirect:/userlist";
      }

      List<User> userList = userService.getusers();
      model.addAttribute("userList", userList);
      model.addAttribute("utid", form.getUtid());
      model.addAttribute("uid", form.getUid());
      model.addAttribute("tid", form.getTid());

      return "userlistpage";
   }

   @GetMapping("/eventlist")
   public String showEventListPage(Model model, RedirectAttributes attributes,
         @ModelAttribute @Validated EventTagForm form,
         BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {
         attributes.addFlashAttribute("isEventTagFormError", true);
         return "redirect:/eventlist";
      }

      List<Event> eventList = eventService.getevents();
      model.addAttribute("eventList", eventList);
      model.addAttribute("etid", form.getEtid());
      model.addAttribute("eid", form.getEid());
      model.addAttribute("tid", form.getTid());

      return "eventlistpage";
   }

}
