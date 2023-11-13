package jp.kobeu.cs27.localEvent.domain.entity;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDate startday;

    // イベントの終了日
    private LocalDate endday;

    // イベントの開催時間の開始
    private LocalTime starttime;

    // イベントの開催時間の終了
    private LocalTime endtime;

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

    // ユーザのタグとイベントの一致回数
    private int count;

}
