package jp.kobeu.cs27.localEvent.application.form;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * イベント登録・更新フォーム
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventForm {

    // イベントID
    private int eid;

    // イベント名
    private String name;

    // イベントの説明
    private String description;

    // イベントの開始日
    private String startday;

    // イベントの終了日
    private String endday;

    // イベントの開催時間の開始
    private String starttime;

    // イベントの開催時間の終了
    private String endtime;

    // イベントの会場
    private String place;

    // イベントの参加費
    private int fee;

    // イベントに駐車場があるか
    private boolean parking;

    // イベントのアクセス
    private String access;

    // イベントのエリアID
    private int aid;

    // イベントの主催者
    private String organizer;

    // イベントの定員
    private int capacity;


}
