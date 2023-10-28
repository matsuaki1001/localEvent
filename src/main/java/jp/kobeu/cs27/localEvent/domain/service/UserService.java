package jp.kobeu.cs27.localEvent.domain.service;

import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.USER_ALREADY_EXISTS;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.USER_DOES_NOT_EXIST;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.kobeu.cs27.localEvent.application.form.UserForm;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import jp.kobeu.cs27.localEvent.domain.entity.User;
import jp.kobeu.cs27.localEvent.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository users;

    /**
     * タグを追加する
     * 
     * @param form タグのフォーム
     * @return 追加したタグ
     */

    public User addUser(UserForm form) {

        // タグIDを変数に格納する
        final int uid = form.getUid();

        // タグが既に存在する場合は例外を投げる
        if (users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_ALREADY_EXISTS,
                    "create the User",
                    String.format("User id %d already exists", uid));
        }

        // タグをDBに登録し、登録したタグの情報を戻り値として返す
        return users.save(new User(uid, form.getName(), form.getEmail(), form.getPassword(), form.getAid()));

    }

    /**
     * タグを更新する
     * 
     * @param form タグのフォーム
     * @return 更新したタグ
     */
    public User updateUser(UserForm form) {

        // タグIDを変数に格納する
        final int uid = form.getUid();

        // タグが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "update the user",
                    String.format("user id %d not found", uid));
        }

        // タグをDBに登録し、登録したタグの情報を戻り値として返す
        return users.save(new User(uid, form.getName(), form.getEmail(), form.getPassword(), form.getAid()));
    }

    /**
     * タグを削除する
     * 
     * @param tid タグID
     */
    @Transactional
    public void deleteUser(int uid) {

        // タグが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "delete the User",
                    String.format("User id %d not found", uid));
        }

        // タグを削除する
        users.deleteById(uid);
    }

    /**
     * 指定したタグがDBに存在するかどうかを返す
     * 
     * @param tid タグID
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean existsUser(int uid) {
        return users.existsByUid(uid);
    }

    /**
     * タグの情報を取得する
     * 
     * @param tid タグID
     * @return タグの情報
     */
    public User getUser(int uid) {

        // タグが存在しない場合は例外を投げる
        if (!users.existsByUid(uid)) {
            throw new ValidationException(
                    USER_DOES_NOT_EXIST,
                    "get the User",
                    String.format("User id %d not found", uid));
        }

        // タグの情報を取得する
        return users.findById(uid).get();
    }

    /**
     * タグの一覧を取得する
     * 
     * @return タグの一覧
     */
    public List<User> getevents() {
        return users.findAllByOrderByUidAsc();
    }

}
