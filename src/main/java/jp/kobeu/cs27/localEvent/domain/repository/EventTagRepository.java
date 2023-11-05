package jp.kobeu.cs27.localEvent.domain.repository;

import java.util.List;
import jp.kobeu.cs27.localEvent.domain.entity.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * イベントとタグの関連のリポジトリ
 */
@Repository
public interface EventTagRepository extends JpaRepository<EventTag, Integer> {
    /**
     * すべてのユーザーをID順に取得する
     * 
     * @param uid ユーザーID
     * 
     * @return ユーザーのリスト
     * 
     */
    List<EventTag> findAllByOrderByEtidAsc();

    /**
     * タグが一致するイベントタグをすべて削除する
     * 
     * @param tid タグID
     */
    void deleteByEtid(int etid);

    /**
     * イベントタグIDに対応するイベントタグを入手する
     * 
     * @param etid イベントタグID
     */
    EventTag findByEtid(int etid);

    /**
     * タグIDに対応するイベントタグを入手する
     * @param tid タグID
     */
    List<EventTag> findAllByTid(int tid);



}
