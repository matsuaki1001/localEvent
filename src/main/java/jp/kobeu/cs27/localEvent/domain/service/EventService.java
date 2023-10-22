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

public class EventService {

    public void deleteTag(int tid) {
    }
    
}
