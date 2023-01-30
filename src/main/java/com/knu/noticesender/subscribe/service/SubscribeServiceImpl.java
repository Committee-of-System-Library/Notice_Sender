package com.knu.noticesender.subscribe.service;

import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import com.knu.noticesender.subscribe.model.Category;
import com.knu.noticesender.subscribe.model.Category.CategoryId;
import com.knu.noticesender.subscribe.model.CategoryType;
import com.knu.noticesender.subscribe.model.Subscribe;
import com.knu.noticesender.subscribe.repository.CategoryRepository;
import com.knu.noticesender.subscribe.repository.SubscribeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void subscribe(SubscribeRequest req) {
        Subscribe subscribe = subscribeRepository.save(createSubscribe(req));
        for (CategoryType category : req.getCategories()) {
            categoryRepository.save(createCategory(subscribe, category));
        }
    }

    @Override
    public void unsubscribe(SubscribeRequest req) {
        for (CategoryType category : req.getCategories()) {
            try {
                categoryRepository.deleteById(getCategoryId(req.getSubId(), category));
            } catch (EmptyResultDataAccessException e) {}
        }
    }

    @Override
    public List<SubscribeInfo> getAllSubscribeInfo() {
        List<SubscribeInfo> result = new ArrayList<>();
        for (Subscribe subscribe : subscribeRepository.findAll()) {
            List<CategoryType> types = getTypes(subscribe);
            result.add(new SubscribeInfo(subscribe.getId(), types));
        }
        return result;
    }

    @Override
    public SubscribeInfo getSubscribeInfo(String subId) {
        Subscribe subscribe = subscribeRepository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Wrong SubId"));
        List<CategoryType> types = getTypes(subscribe);

        return new SubscribeInfo(subscribe.getId(), types);
    }

    private List<CategoryType> getTypes(Subscribe subscribe) {
        List<CategoryType> types = categoryRepository
                .findCategoriesById_SubscribeId(subscribe.getId())
                .stream().map(c -> c.getId().getType())
                .collect(Collectors.toList());
        return types;
    }

    private Subscribe createSubscribe(SubscribeRequest req) {
        return Subscribe.builder()
                .id(req.getSubId())
                .build();
    }

    private Category createCategory(Subscribe subscribe, CategoryType category) {
        return Category.builder()
                .id(getCategoryId(subscribe.getId(), category))
                .subscribe(subscribe)
                .build();
    }

    private static CategoryId getCategoryId(String subscribeId, CategoryType category) {
        return new CategoryId(subscribeId, category);
    }

}
