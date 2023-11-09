package jp.kobeu.cs27.localEvent.application.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザIDフォーム
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UidForm {
    
    //ユーザID
    @NotNull
    private int uid;
}
