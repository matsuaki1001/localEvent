package jp.kobeu.cs27.localEvent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * カテゴリを表すエンティティ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category{
        
        // カテゴリID
        @Id
        private String cid;
    
        // カテゴリ名
        private String name;

        // カテゴリの説明
        private String description;

        
}