package jp.kobeu.cs27.localEvent.domain.repository;

import java.util.List;
import jp.kobeu.cs27.localEvent.domain.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * ユーザーのリポジトリ
 */
@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Integer> {
    /**
     * すべてのユーザーをID順に取得する
     * 
     * @param uid ユーザーID
     * 
     * @return ユーザーのリスト
     * 
     */
    List<UserTag> findAllByOrderByUtidAsc();
}