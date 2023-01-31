package com.knu.noticesender.subscribe.repository;

import com.knu.noticesender.subscribe.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, String> { }
