package jp.kobeu.cs27.localEvent.domain.repository;

import java.util.List;
import jp.kobeu.cs27.localEvent.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * カテゴリーのリポジトリ
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /**
     * すべてのカテゴリをID順に取得する
     * 
     * @param cid カテゴリID
     * 
     * @return カテゴリのリスト
     * 
     */
    List<Category> findAllByOrderByCidAsc();

    /**
     * カテゴリIDに対応するカテゴリがあるか
     * 
     * @param cid カテゴリID
     * @return カテゴリがあるかどうか
     */
    boolean existsByCid(String cid);

}
