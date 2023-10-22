package jp.kobeu.cs27.localEvent.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String eid;

    // イベント名
    private String name;

    // イベントの説明
    private String description;

    // イベントの開始日時
    private String start;

    // イベントの終了日時
    private String end;

    // イベントの場所
    private String place;

    // イベントの参加人数
    private int capacity;

    // イベントの参加費
    private int fee;

    // イベントの主催者
    private String organizer;

    // イベントのカテゴリ
    @ManyToOne
    private Category category;

    // イベントの画像
    private String image;

    // イベントのタグ
    @OneToMany
    private List<Tag> tags;

}
