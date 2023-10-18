package jp.kobeu.cs27.localEvent.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * エラーコード
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_DOES_NOT_EXIST(11),
    USER_ALREADY_EXISTS(12),
    Event_DOES_NOT_EXIST(21),
    Event_ALREADY_EXISTS(22),
    Category_DOES_NOT_EXIST(31),
    Category_ALREADY_EXISTS(32),
    Tag_DOES_NOT_EXIST(41),
    Tag_ALREADY_EXISTS(42),
    CONTROLLER_VALIDATION_ERROR(97),
    CONTROLLER_REJECTED(98),
    OTHER_ERROR(99);

    private final int code;
}
