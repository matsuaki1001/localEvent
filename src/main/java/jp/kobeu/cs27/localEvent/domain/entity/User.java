package jp.kobeu.cs27.localEvent.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    // ユーザー名
    private String name;

    // メールアドレス
    private String email;

    // パスワード
    private String password;

    // ユーザのエリアID
    private int aid;
}
