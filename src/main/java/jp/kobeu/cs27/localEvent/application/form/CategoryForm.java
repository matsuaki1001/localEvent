package jp.kobeu.cs27.localEvent.application.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * カテゴリ登録・更新フォーム
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
    
    //カテゴリID
    private String cid;

    //カテゴリ名
    @NotBlank
    private String name;

    //カテゴリの説明
    private String description;
}
