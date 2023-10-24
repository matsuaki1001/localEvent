package jp.kobeu.cs27.localEvent.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

/**
 * イベントを表すエンティティ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    // イベントID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // イベントのエリア
    @ManyToOne
    private Area area;

    // イベントの主催者
    private String organizer;

    // イベントの定員
    private int capacity;

    //　ユーザのタグとイベントの一致回数
    private int match;

}
