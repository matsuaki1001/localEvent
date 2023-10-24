package jp.kobeu.cs27.localEvent.domain.service;

import org.springframework.stereotype.Service;

import jp.kobeu.cs27.localEvent.application.form.AreaForm;
import jp.kobeu.cs27.localEvent.domain.entity.Area;
import jp.kobeu.cs27.localEvent.domain.repository.AreaRepository;
import jp.kobeu.cs27.localEvent.configuration.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import static jp.kobeu.cs27.localEvent.configuration.exception.ErrorCode.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository categories;

    /**
     * エリアを登録する
     * 
     * @param form エリア登録フォーム
     * @return 登録したエリア
     * 
     */

    public Area createArea(AreaForm form) {

        // エリアIDを変数に格納する
        final int aid = form.getAid();

        // エリアが登録済みであった場合、例外を投げる
        if (categories.existsByaid(aid)) {
            throw new ValidationException(
                    Area_ALREADY_EXISTS,
                    "create the Area",
                    String.format(
                            "Area %s already exists", aid));
        }

        // エリアを登録する
        return categories.save(
                new Area(
                        form.getAid(),
                        form.getName(),
                        form.getDescription()));

    }

    /**
     * エリアが存在するかどうかを確認する
     * 
     * @param aid エリアID
     * @return エリアが存在するかどうか
     */
    public boolean existsArea(int aid) {
        return categories.existsByaid(aid);
    }

    /**
     * エリアを更新する
     * 
     * @param form エリア更新フォーム
     * @return 更新したエリア
     * 
     */
    public Area updateArea(AreaForm form) {

        // エリアIDを変数に格納する
        final int aid = form.getAid();

        // エリアが登録されていない場合、例外を投げる
        if (!categories.existsByaid(aid)) {
            throw new ValidationException(
                    Area_DOES_NOT_EXIST,
                    "update the Area",
                    String.format(
                            "Area %s not found", aid));
        }

        // エリアを更新する
        return categories.save(
                new Area(
                        form.getAid(),
                        form.getName(),
                        form.getDescription()));

    }

    /**
     * エリアを削除する
     * 
     * @param aid エリアID
     * 
     */
    @Transactional
    public void deleteArea(int aid) {

        // エリアが登録されていない場合、例外を投げる
        if (!categories.existsByaid(aid)) {
            throw new ValidationException(
                    Area_DOES_NOT_EXIST,
                    "delete the Area",
                    String.format(
                            "Area %s not found", aid));
        }

        // エリアを削除する
        categories.deleteById(aid);

    }

    /**
     * エリアを取得する
     * 
     * @param aid エリアID
     * @return エリア
     * 
     */
    public Area getArea(int aid) {

        // エリアが登録されていない場合、例外を投げる
        if (!categories.existsByaid(aid)) {
            throw new ValidationException(
                    Area_DOES_NOT_EXIST,
                    "get the Area",
                    String.format(
                            "Area %s not found", aid));
        }

        // エリアを取得する
        return categories.findById(aid).orElseThrow(() -> new ValidationException(
                Area_DOES_NOT_EXIST,
                "get the Area",
                String.format(
                        "Area %s not found", aid)));
    }

    /**
     * すべてのエリアを取得する
     * 
     * @return エリアのリスト
     */

    public List<Area> getAllCategories() {
        return categories.findAllByOrderByaidAsc();
    }

}
