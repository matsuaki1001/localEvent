package jp.kobeu.cs27.localEvent.application.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import jp.kobeu.cs27.localEvent.configuration.exception.*;
import jp.kobeu.cs27.localEvent.domain.service.*;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.entity.UserTag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class localEventRestController {

    private final UserService userService;
    private final EventService eventService;

    /**
     * ユーザが持つイベントを表示する
     *
     * @param uid ユーザID
     * @return ユーザ情報
     */
    @GetMapping("/event")
    public Response<List<Event>> searchEvent(
            @RequestParam("uid") int uid) {

        List<UserTag> userTagList = userService.getUserTagsByUid(uid);
        List<Integer> tidList = userService.getTidsByUserTags(userTagList);
        List<Event> eventList = eventService.getEventsByTagIdList(tidList);

        // ユーザを検索し、結果をResponse型でラップして返す
        return ResponseCreator.succeed(eventList);

    }

}
