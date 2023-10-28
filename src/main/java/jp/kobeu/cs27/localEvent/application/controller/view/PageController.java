package jp.kobeu.cs27.localEvent.application.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobeu.cs27.localEvent.application.form.AreaForm;
import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.application.form.TagForm;
import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.application.form.TagForm;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PageController {

   /**
    * ログイン画面を表示する
    */

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

      model.addAttribute(new EventForm());

      return "eventpage";
   }

   @GetMapping("/user")
   public String showUserPage(Model model) {

      model.addAttribute(new UserForm());

      return "userpage";
   }

}
