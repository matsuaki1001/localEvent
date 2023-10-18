package jp.kobeu.cs27.localEvent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String id;

    // ユーザー名
    private String name;

    // メールアドレス
    private String email;

    // パスワード
    private String password;

    
}
