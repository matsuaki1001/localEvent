package jp.kobeu.cs27.localEvent.domain.dto;

import lombok.Data;

/**
 * イベント検索結果
 */
@Data
public class EventQueryResult {

    // イベントID
    private final String eid;

    // イベント名
    private final String name;

    // イベントの説明
    private final String description;

    // イベントの開始日時
    private final String start;

    // イベントの終了日時
    private final String end;

    // イベントの場所
    private final String place;

    // イベントの参加人数
    private final int capacity;

    // イベントの参加費
    private final int fee;

    // イベントの主催者
    private final String organizer;

    // イベントのカテゴリID
    private final Integer cid;

    // イベントの画像
    private final String image;

}
