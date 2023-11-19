package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.EventForm;
import jp.kobeu.cs27.localEvent.application.form.EventTagForm;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import jp.kobeu.cs27.localEvent.domain.entity.EventTag;
import jp.kobeu.cs27.localEvent.domain.entity.Tag;
import jp.kobeu.cs27.localEvent.domain.repository.EventRepository;
import jp.kobeu.cs27.localEvent.domain.repository.EventTagRepository;
import jp.kobeu.cs27.localEvent.domain.repository.TagRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
     * @param IdForm イベントIDのフォーム
     */
    @Transactional
    public void deleteEvent(int eid) {

        // イベントが存在しない場合は例外を投げる
        if (!events.existsByEid(eid)) {
            throw new ValidationException(
                    EVENT_DOES_NOT_EXIST,
                    "delete the event",
                    String.format("event id %d not found", eid));
        }

        // イベントを削除する
        events.deleteByEid(eid);
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

    public void addTagToEvent(EventTagForm form) {

        // イベントIDを変数に格納する
        final int eid = form.getEid();
        final int tid = form.getTid();

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
        eventTags.save(new EventTag(0, eid, tid));
    }

    /**
     * タグとイベントの紐づけを解除する
     * 
     * @param form イベントタグのフォーム
     *
     * @return
     */
    @Transactional
    public void deleteTagFromEvent(EventTagForm form) {

        // イベントIDを変数に格納する
        final int eid = form.getEid();
        final int tid = form.getTid();

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

        // イベントからタグを削除する
        eventTags.deleteByEidAndTid(eid, tid);
    }

    /**
     * イベントタグのリストからイベントIDのリストを入手する
     * 
     * @param eventTagList
     * @return
     */
    public List<Integer> getEventIdListByEventTagList(List<EventTag> eventTagList) {
        return eventTagList.stream().map(eventTag -> eventTag.getEid()).toList();
    }

    /**
     * タグIDのリストに対応するイベントのリストを入手する
     * 
     * @param eventList
     * @return
     */
    public List<Event> getEventsByTagIdList(List<Integer> tidList) {
        List<EventTag> eventTagList = eventTags.findAllByTidIn(tidList);
        List<Integer> eidList = getEventIdListByEventTagList(eventTagList);
        return events.findAllByEidIn(eidList);

    }

    /**
     * イベントリストの中から現在から一ヶ月以内に開催されるイベントを取得する
     */
    public List<Event> getEventsByOneMonth(List<Event> eventList) {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthLater = now.plusMonths(1); // 1ヶ月後の日付を取得

        return eventList.stream()
                .filter(event -> !event.getStartday().isAfter(oneMonthLater) && event.getStartday().isAfter(now))
                .toList();
    }

    /**
     * イベントタグのリストからタグIDのリストを取得する
     *
     * @param eventTagList
     * @return
     */
    public List<Integer> getTagIdListByEventTagList(List<EventTag> eventTagList) {
        return eventTagList.stream().map(eventTag -> eventTag.getTid()).toList();
    }

    /**
     * イベントIDからタグのリストを取得する
     */
    public List<Tag> getTagsByEid(int eid) {
        List<EventTag> eventTagList = eventTags.findAllByEid(eid);
        List<Integer> tidList = getTagIdListByEventTagList(eventTagList);
        return tags.findAllByTidIn(tidList);
    }

    /**
     * イベントタグが存在するかどうかを返す
     */
    public boolean existsEventTag(int eid, int tid) {
        return eventTags.existsByEidAndTid(eid, tid);
    }

    /**
     * イベントリストからユーザのエリアIDと一致するイベントのリストを取得する
     */
    public List<Event> getEventsByAreaId(int aid, List<Event> eventList) {
        return eventList.stream().filter(event -> event.getAid() == aid).toList();
    }

    /**
     * イベントリストをシャッフルして3つのイベントを取得して、イベントの開始日順にソートする
     */
    public List<Event> getEventsByShuffle(List<Event> eventList) {
        // 可変のイベントリストを作る
        List<Event> newEventList = new ArrayList<>(eventList);
        // イベントリストをシャッフルする
        Collections.shuffle(newEventList);
        // イベントリストからイベントを3つ取得する
        List<Event> eventListByShuffle = newEventList.stream().limit(3).toList();
        // 可変のイベントリストを作る
        List<Event> newEventListByShuffle = new ArrayList<>(eventListByShuffle);
        // イベントリストを開始日順にソートする
        Collections.sort(newEventListByShuffle,
                (event1, event2) -> event1.getStartday().compareTo(event2.getStartday()));
        return newEventListByShuffle;
    }

    /**
     * イベントリストからイベントの開始日順にソートする
     */
    public List<Event> getEventsByStartday(List<Event> eventList) {
        // 可変のイベントリストを作る
        List<Event> newEventList = new ArrayList<>(eventList);
        // イベントリストを開始日順にソートする
        Collections.sort(newEventList,
                (event1, event2) -> event1.getStartday().compareTo(event2.getStartday()));
        return newEventList;
    }

}
