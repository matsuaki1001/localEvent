package jp.kobeu.cs27.localEvent.domain.repository;

import java.util.List;

import jp.kobeu.cs27.localEvent.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * タグのリポジトリ
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    /**
     * すべてのタグをID順に取得する
     * 
     * @param tud タグID
     * 
     * @return タグのリスト
     * 
     */
    List<Tag> findAllByOrderByTidAsc();

    /**
     * タグIDに対応するカテゴリがあるか
     * 
     * @param cid タグID
     * @return タグがあるかどうか
     */
    boolean existsByTid(String tid);
}
