package com.knu.noticesender.subscribe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import com.knu.noticesender.subscribe.model.Subscribe;
import com.knu.noticesender.subscribe.model.Subscribe.SubscribeId;
import com.knu.noticesender.subscribe.model.SubscribeType;
import com.knu.noticesender.subscribe.model.Subscriber;
import com.knu.noticesender.subscribe.repository.SubscribeRepository;
import com.knu.noticesender.subscribe.repository.SubscriberRepository;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscriberRepository subscriberRepository;
    private final SubscribeRepository subscribeRepository;

    @Override
    @Transactional
    public void subscribe(SubscribeRequest req) {
        Subscriber subscriber = subscriberRepository.save(createSubscribe(req));
        for (SubscribeType subscribe : req.getTypes()) {
            subscribeRepository.save(createSubscribe(subscriber, subscribe));
        }
    }

    @Override
    public void unsubscribe(SubscribeRequest req) {
        for (SubscribeType subscribe : req.getTypes()) {
            try {
                subscribeRepository.deleteById(getSubscribeId(req.getSubId(), subscribe));
            } catch (EmptyResultDataAccessException e) {}
        }
    }

    @Override
    public List<SubscribeInfo> findAll() {
        List<SubscribeInfo> result = new ArrayList<>();
        for (Subscriber subscriber : subscriberRepository.findAll()) {
            List<SubscribeType> types = getTypes(subscriber);
            result.add(new SubscribeInfo(subscriber.getId(), types));
        }
        return result;
    }

    @Override
    public SubscribeInfo findById(String id) {
        Subscriber subscriber = subscriberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wrong SubId"));
        List<SubscribeType> types = getTypes(subscriber);

        return new SubscribeInfo(subscriber.getId(), types);
    }

    @Override
    public List<String> findAllSubIdByType(SubscribeType type) {
        List<Subscribe> subscribes = subscribeRepository.findSubscribesById_Type(type);
        return subscribes.stream().map(c -> c.getId().getSubscribeId()).collect(Collectors.toList());
    }

    private List<SubscribeType> getTypes(Subscriber subscriber) {
        List<SubscribeType> types = subscribeRepository
                .findSubscribesById_SubscribeId(subscriber.getId())
                .stream().map(c -> c.getId().getType())
                .collect(Collectors.toList());
        return types;
    }

    private Subscriber createSubscribe(SubscribeRequest req) {
        return Subscriber.builder()
                .id(req.getSubId())
                .build();
    }

    private Subscribe createSubscribe(Subscriber subscriber, SubscribeType subscribe) {
        return Subscribe.builder()
                .id(getSubscribeId(subscriber.getId(), subscribe))
                .subscriber(subscriber)
                .build();
    }

    private static SubscribeId getSubscribeId(String subscribeId, SubscribeType subscribe) {
        return new SubscribeId(subscribeId, subscribe);
    }

}
