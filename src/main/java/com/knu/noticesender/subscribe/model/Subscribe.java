package com.knu.noticesender.subscribe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Subscribe {
    @Id
    private String id;

    @Builder
    public Subscribe(String id) {
        this.id = id;
    }
}
