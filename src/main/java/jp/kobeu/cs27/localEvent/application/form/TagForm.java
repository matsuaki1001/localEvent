package jp.kobeu.cs27.localEvent.application.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * タグ登録・更新フォーム
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagForm {
    
    //タグID
    private int tid;

    //タグ名
    private String name;

    //タグの説明
    private String description;
}
