package com.knu.noticesender.subscribe.service;

import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import com.knu.noticesender.subscribe.model.Category;
import com.knu.noticesender.subscribe.model.Category.CategoryId;
import com.knu.noticesender.subscribe.model.SubscribeType;
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
        for (SubscribeType category : req.getCategories()) {
            categoryRepository.save(createCategory(subscribe, category));
        }
    }

    @Override
    public void unsubscribe(SubscribeRequest req) {
        for (SubscribeType category : req.getCategories()) {
            try {
                categoryRepository.deleteById(getCategoryId(req.getSubId(), category));
            } catch (EmptyResultDataAccessException e) {}
        }
    }

    @Override
    public List<SubscribeInfo> findAll() {
        List<SubscribeInfo> result = new ArrayList<>();
        for (Subscribe subscribe : subscribeRepository.findAll()) {
            List<SubscribeType> types = getTypes(subscribe);
            result.add(new SubscribeInfo(subscribe.getId(), types));
        }
        return result;
    }

    @Override
    public SubscribeInfo findById(String id) {
        Subscribe subscribe = subscribeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wrong SubId"));
        List<SubscribeType> types = getTypes(subscribe);

        return new SubscribeInfo(subscribe.getId(), types);
    }

    @Override
    public List<String> findAllSubIdByType(SubscribeType type) {
        List<Category> categories = categoryRepository.findCategoriesById_Type(type);
        return categories.stream().map(c -> c.getId().getSubscribeId()).collect(Collectors.toList());
    }

    private List<SubscribeType> getTypes(Subscribe subscribe) {
        List<SubscribeType> types = categoryRepository
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

    private Category createCategory(Subscribe subscribe, SubscribeType category) {
        return Category.builder()
                .id(getCategoryId(subscribe.getId(), category))
                .subscribe(subscribe)
                .build();
    }

    private static CategoryId getCategoryId(String subscribeId, SubscribeType category) {
        return new CategoryId(subscribeId, category);
    }

}
