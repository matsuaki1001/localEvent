package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.AreaForm;
import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.repository.AreaRepository;
import jp.kobeu.cs27.localEvent.domain.repository.EventRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository events;

    /**
     * タグを追加する
     * 
     * @param form タグのフォーム
     * @return 追加したタグ
     */

    public Event addEvent(EventForm form) {

        // タグIDを変数に格納する
        final int eid = form.getEid();

        // タグが既に存在する場合は例外を投げる
        if (events.existsByEid(eid)) {
            throw new ValidationException(
                    EVENT_ALREADY_EXISTS,
                    "create the Event",
                    String.format("Event id %d already exists", eid));
        }

        // タグをDBに登録し、登録したタグの情報を戻り値として返す
        return events.save(new Event(eid, form.getName(), form.getDescription(), form.getStartday(), form.getEndday(),
                form.getStarttime(), form.getEndtime(), form.getPlace(), form.getFee(), form.isParking(),
                form.getAccess(), form.getAid(), form.getOrganizer(), form.getCapacity(), 0));

    }

    /**
     * タグを更新する
     * 
     * @param form タグのフォーム
     * @return 更新したタグ
     */
    public Event updateEvent(EventForm form) {

        // タグIDを変数に格納する
        final int eid = form.getEid();

        // タグが存在しない場合は例外を投げる
        if (!events.existsByEid(eid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "update the event",
                    String.format("event id %d not found", eid));
        }

        // タグをDBに登録し、登録したタグの情報を戻り値として返す
        return events.save(new Event(eid, form.getName(), form.getDescription(), form.getStartday(), form.getEndday(),
                form.getStarttime(), form.getEndtime(), form.getPlace(), form.getFee(), form.isParking(),
                form.getAccess(), form.getAid(), form.getOrganizer(), form.getCapacity(), form.getCapacity()));
    }

    /**
     * タグを削除する
     * 
     * @param tid タグID
     */
    @Transactional
    public void deleteEvent(int tid) {

        // タグが存在しない場合は例外を投げる
        if (!events.existsByEid(tid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "delete the Event",
                    String.format("Event id %d not found", tid));
        }

        // タグを削除する
        events.deleteById(tid);
    }

    /**
     * 指定したタグがDBに存在するかどうかを返す
     * 
     * @param tid タグID
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean existsEvent(int tid) {
        return events.existsByEid(tid);
    }

    /**
     * タグの情報を取得する
     * 
     * @param tid タグID
     * @return タグの情報
     */
    public Event getEvent(int tid) {

        // タグが存在しない場合は例外を投げる
        if (!events.existsByEid(tid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "get the Event",
                    String.format("Event id %d not found", tid));
        }

        // タグの情報を取得する
        return events.findById(tid).get();
    }

    /**
     * タグの一覧を取得する
     * 
     * @return タグの一覧
     */
    public List<Event> getevents() {
        return events.findAllByOrderByEidAsc();
    }

}
