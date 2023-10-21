package jp.kobeu.cs27.localEvent.application.controller.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import jp.kobeu.cs27.localEvent.configuration.exception.*;
import jp.kobeu.cs27.localEvent.domain.service.*;
import jp.kobeu.cs27.localEvent.domain.entity.Event;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class localEventRestController {
    
}
