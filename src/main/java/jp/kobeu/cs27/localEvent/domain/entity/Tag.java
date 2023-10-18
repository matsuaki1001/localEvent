package jp.kobeu.cs27.localEvent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * タグを表すエンティティ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tag {

    // タグID
    @Id
    private String tid;

    // タグ名
    private String name;

    // タグの説明
    private String description;

    

}
