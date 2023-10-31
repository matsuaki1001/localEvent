package jp.kobeu.cs27.localEvent.domain.service;

import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.USER_ALREADY_EXISTS;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.USER_DOES_NOT_EXIST;

import java.util.List;

import javax.swing.text.html.HTML.Tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.entity.User;
import jp.kobeu.cs27.localEvent.domain.entity.UserTag;
import jp.kobeu.cs27.localEvent.domain.repository.TagRepository;
import jp.kobeu.cs27.localEvent.domain.repository.UserRepository;
import jp.kobeu.cs27.localEvent.domain.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository users;
    private final TagRepository tags;
    private final UserTagRepository userTags;

    /**
     * ユーザを追加する
     * 
     * @param form ユーザのフォーム
     * @return 追加したユーザ
     */

    public User addUser(UserForm form) {

        // ユーザIDを変数に格納する
        final int uid = form.getUid();

        // ユーザが既に存在する場合は例外を投げる
        if (users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_ALREADY_EXISTS,
                    "create the User",
                    String.format("User id %d already exists", uid));
        }

        // ユーザをDBに登録し、登録したユーザの情報を戻り値として返す
        return users.save(new User(uid, form.getName(), form.getEmail(), form.getPassword(), form.getAid()));

    }

    /**
     * ユーザを更新する
     * 
     * @param form ユーザのフォーム
     * @return 更新したユーザ
     */
    public User updateUser(UserForm form) {

        // ユーザIDを変数に格納する
        final int uid = form.getUid();

        // ユーザが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "update the user",
                    String.format("user id %d not found", uid));
        }

        // ユーザをDBに登録し、登録したユーザの情報を戻り値として返す
        return users.save(new User(uid, form.getName(), form.getEmail(), form.getPassword(), form.getAid()));
    }

    /**
     * ユーザを削除する
     * 
     * @param tid ユーザID
     */
    @Transactional
    public void deleteUser(int uid) {

        // ユーザが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "delete the User",
                    String.format("User id %d not found", uid));
        }

        // ユーザを削除する
        users.deleteById(uid);
    }

    /**
     * 指定したユーザがDBに存在するかどうかを返す
     * 
     * @param tid ユーザID
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean existsUser(int uid) {
        return users.existsByUid(uid);
    }

    /**
     * ユーザの情報を取得する
     * 
     * @param tid ユーザID
     * @return ユーザの情報
     */
    public User getUser(int uid) {

        // ユーザが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "get the User",
                    String.format("User id %d not found", uid));
        }

        // ユーザの情報を取得する
        return users.findById(uid).get();
    }

    /**
     * ユーザの一覧を取得する
     * 
     * @return ユーザの一覧
     */
    public List<User> getevents() {
        return users.findAllByOrderByUidAsc();
    }

    /**
     * ユーザとタグを紐付ける
     */
    public void addTagToUser(int uid, int tid) {

        // ユーザが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "get the User",
                    String.format("User id %d not found", uid));
        }

        // タグが存在しない場合は例外を投げる
        if (!tags.existsByTid(tid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "get the tag",
                    String.format("Tag id %d not found", tid));
        }

        // ユーザとタグを紐付ける
       userTags.save(new UserTag(0, uid, tid));
    }

}
