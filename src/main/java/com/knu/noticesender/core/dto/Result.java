package com.knu.noticesender.core.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {
    @Valid
    private T data;
}