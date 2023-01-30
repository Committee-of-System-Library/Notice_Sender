package com.knu.noticesender.subscribe.repository;

import com.knu.noticesender.subscribe.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, String> { }
