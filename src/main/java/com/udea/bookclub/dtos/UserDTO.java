package com.udea.bookclub.dtos;

import jakarta.validation.constraints.Null;
import lombok.Builder;

@Builder
public record UserDTO(
        @Null
        Long userId,
        String username,
        String pictureUrl,
        String email,
        String password) {
}
