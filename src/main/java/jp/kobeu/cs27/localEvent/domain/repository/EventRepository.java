package jp.kobeu.cs27.localEvent.domain.repository;
import java.util.List;
import jp.kobeu.cs27.localEvent.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * イベントのリポジトリ
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    /**
     * すべてのイベントをID順に取得する
     * 
     * @param eid イベントID
     * 
     * @return イベントのリスト
     * 
     * 
     */
    List <Event> findAllByOrderByEidAsc();

    
}
