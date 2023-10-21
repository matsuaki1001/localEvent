package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.CategoryForm;
import jp.kobeu.cs27.localEvent.domain.entity.Category;
import jp.kobeu.cs27.localEvent.domain.repository.CategoryRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categories;

    /**
     * カテゴリを登録する
     * 
     * @param form カテゴリ登録フォーム
     * @return 登録したカテゴリ
     * 
     */

    public Category createCategory(CategoryForm form) {

        // カテゴリIDを変数に格納する
        final int cid = form.getCid();

        // カテゴリが登録済みであった場合、例外を投げる
        if (categories.existsByCid(cid)) {
            throw new ValidationException(
                    CATEGORY_ALREADY_EXISTS,
                    "create the category",
                    String.format(
                            "category %s already exists", cid));
        }

        // カテゴリを登録する
        return categories.save(
                new Category(
                        form.getCid(),
                        form.getName(),
                        form.getDescription()));

    }

    /**
     * カテゴリが存在するかどうかを確認する
     * 
     * @param cid カテゴリID
     * @return カテゴリが存在するかどうか
     */
    public boolean existsCategory(int cid) {
        return categories.existsByCid(cid);
    }

    /**
     * カテゴリを更新する
     * 
     * @param form カテゴリ更新フォーム
     * @return 更新したカテゴリ
     * 
     */
    public Category updateCategory(CategoryForm form) {

        // カテゴリIDを変数に格納する
        final int cid = form.getCid();

        // カテゴリが登録されていない場合、例外を投げる
        if (!categories.existsByCid(cid)) {
            throw new ValidationException(
                    CATEGORY_DOES_NOT_EXIST,
                    "update the category",
                    String.format(
                            "category %s not found", cid));
        }

        // カテゴリを更新する
        return categories.save(
                new Category(
                        form.getCid(),
                        form.getName(),
                        form.getDescription()));

    }

    /**
     * カテゴリを削除する
     * 
     * @param cid カテゴリID
     * 
     */
    @Transactional
    public void deleteCategory(int cid) {

        // カテゴリが登録されていない場合、例外を投げる
        if (!categories.existsByCid(cid)) {
            throw new ValidationException(
                    CATEGORY_DOES_NOT_EXIST,
                    "delete the category",
                    String.format(
                            "category %s not found", cid));
        }

        // カテゴリを削除する
        categories.deleteById(cid);

    }

    /**
     * カテゴリを取得する
     * 
     * @param cid カテゴリID
     * @return カテゴリ
     * 
     */
    public Category getCategory(int cid){
            
            // カテゴリが登録されていない場合、例外を投げる
            if (!categories.existsByCid(cid)) {
                throw new ValidationException(
                    CATEGORY_DOES_NOT_EXIST,
                    "get the category",
                    String.format(
                        "category %s not found", cid));
            }

            // カテゴリを取得する
            return categories.findById(cid).orElseThrow(() -> new ValidationException(
                CATEGORY_DOES_NOT_EXIST,
                "get the category",
                String.format(
                    "category %s not found", cid)));
    }

    /**
     * すべてのカテゴリを取得する
     * @return カテゴリのリスト
     */

     public List<Category> getAllCategories(){
         return categories.findAllByOrderByCidAsc();
     }




}
