package com.udea.bookclub.dtos;

import com.udea.bookclub.models.User;
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
