package jp.kobeu.cs27.localEvent.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int cid;
    
        // カテゴリ名
        private String name;

        // カテゴリの説明
        private String description;

        
}