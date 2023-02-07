package com.knu.noticesender.subscribe.repository;

import com.knu.noticesender.subscribe.model.Subscribe;
import com.knu.noticesender.subscribe.model.Subscribe.SubscribeId;
import com.knu.noticesender.subscribe.model.SubscribeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, SubscribeId> {
    List<Subscribe> findSubscribesById_SubscribeId(String subId);
    List<Subscribe> findSubscribesById_Type(SubscribeType type);
}
