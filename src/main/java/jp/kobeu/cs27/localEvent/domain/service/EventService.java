package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.entity.EventTag;
import jp.kobeu.cs27.localEvent.domain.repository.EventRepository;
import jp.kobeu.cs27.localEvent.domain.repository.EventTagRepository;
import jp.kobeu.cs27.localEvent.domain.repository.TagRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository events;
    private final TagRepository tags;
    private final EventTagRepository eventTags;

    /**
     * イベントを追加する
     * 
     * @param form イベントのフォーム
     * @return 追加したイベント
     */

    public Event addEvent(EventForm form) {

        // イベントIDを変数に格納する
        final int eid = form.getEid();

        // イベントが既に存在する場合は例外を投げる
        if (events.existsByEid(eid)) {
            throw new ValidationException(
                    EVENT_ALREADY_EXISTS,
                    "create the Event",
                    String.format("Event id %d already exists", eid));
        }

        // イベントをDBに登録し、登録したイベントの情報を戻り値として返す
        return events.save(new Event(eid, form.getName(), form.getDescription(), form.getStartday(), form.getEndday(),
                form.getStarttime(), form.getEndtime(), form.getPlace(), form.getFee(), form.isParking(),
                form.getAccess(), form.getAid(), form.getOrganizer(), form.getCapacity(), 0));

    }

    /**
     * イベントを更新する
     * 
     * @param form イベントのフォーム
     * @return 更新したイベント
     */
    public Event updateEvent(EventForm form) {

        // イベントIDを変数に格納する
        final int eid = form.getEid();

        // イベントが存在しない場合は例外を投げる
        if (!events.existsByEid(eid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "update the event",
                    String.format("event id %d not found", eid));
        }

        // イベントをDBに登録し、登録したイベントの情報を戻り値として返す
        return events.save(new Event(eid, form.getName(), form.getDescription(), form.getStartday(), form.getEndday(),
                form.getStarttime(), form.getEndtime(), form.getPlace(), form.getFee(), form.isParking(),
                form.getAccess(), form.getAid(), form.getOrganizer(), form.getCapacity(), form.getCapacity()));
    }

    /**
     * イベントを削除する
     * 
     * @param tid イベントID
     */
    @Transactional
    public void deleteEvent(int tid) {

        // イベントが存在しない場合は例外を投げる
        if (!events.existsByEid(tid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "delete the Event",
                    String.format("Event id %d not found", tid));
        }

        // イベントを削除する
        events.deleteById(tid);
    }

    /**
     * 指定したイベントがDBに存在するかどうかを返す
     * 
     * @param tid イベントID
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean existsEvent(int tid) {
        return events.existsByEid(tid);
    }

    /**
     * イベントの情報を取得する
     * 
     * @param tid イベントID
     * @return イベントの情報
     */
    public Event getEvent(int tid) {

        // イベントが存在しない場合は例外を投げる
        if (!events.existsByEid(tid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "get the Event",
                    String.format("Event id %d not found", tid));
        }

        // イベントの情報を取得する
        return events.findById(tid).get();
    }

    /**
     * イベントの一覧を取得する
     * 
     * @return イベントの一覧
     */
    public List<Event> getevents() {
        return events.findAllByOrderByEidAsc();
    }

    /**
     * タグとイベントを紐付ける
     */

     public void addTagToEvent(int tid, int eid) {
         // イベントが存在しない場合は例外を投げる
         if (!events.existsByEid(eid)) {
             throw new ValidationException(
                     EVENT_DOES_NOT_EXIST,
                     "get the event",
                     String.format("Event id %d not found", eid));
         }
 
         // タグが存在しない場合は例外を投げる
         if (!tags.existsByTid(tid)) {
             throw new ValidationException(
                     TAG_DOES_NOT_EXIST,
                     "get the tag",
                     String.format("Tag id %d not found", tid));
         }
 
         // イベントにタグを紐付ける
         eventTags.save(new EventTag(0,tid, eid));
     }


}
