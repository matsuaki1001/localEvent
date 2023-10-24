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
public class User {

    // ユーザーID
    @Id
    private int id;

    // ユーザー名
    private String name;

    // メールアドレス
    private String email;

    // パスワード
    private String password;

    // ユーザーの好みのカテゴリ
    @ManyToOne
    private Category category;

    // ユーザーの好みのタグ
    @OneToMany
    private List<Tag> tags;

}
