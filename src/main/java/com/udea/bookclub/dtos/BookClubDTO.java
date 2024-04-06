package com.udea.bookclub.dtos;

import jakarta.validation.constraints.Null;

import java.util.List;

public record BookClubDTO(
        @Null
        Long bookClubId,
        String name,
        String description,
        List<String> tags,
        String meetLink,
        Long userId) {
}
