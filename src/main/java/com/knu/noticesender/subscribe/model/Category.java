package com.knu.noticesender.subscribe.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @EmbeddedId
    private CategoryId id;

    @MapsId("subscribeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subscribe subscribe;

    @Builder
    public Category(CategoryId id, Subscribe subscribe) {
        this.id = id;
        this.subscribe = subscribe;
    }

    @Data
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CategoryId implements Serializable {
        private static final long serialVersionUID = 1L;

        private String subscribeId;

        @Enumerated(EnumType.STRING)
        private CategoryType type;
    }
}


