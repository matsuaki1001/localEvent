package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.CategoryForm;
import jp.kobeu.cs27.localEvent.domain.entity.Category;
import jp.kobeu.cs27.localEvent.domain.repository.CategoryRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categories;

    /**
     * カテゴリを登録する
     * 
     * 
     */

     public Category createCategory(CategoryForm form){
        
        //カテゴリIDを変数に格納する
        final int cid = form.getCid();

        //カテゴリが登録済みであった場合、例外を投げる
        if(categories.existsByCid(cid)){
            throw new ValidationException(
                CATEGORY_ALREADY_EXISTS,
                "create the category",
                String.format(
                    "category %s already exists",cid
                ));      
        }

        

     }
}
