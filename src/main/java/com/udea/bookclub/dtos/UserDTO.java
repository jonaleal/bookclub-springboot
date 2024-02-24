package com.udea.bookclub.dtos;

import lombok.Builder;

@Builder
public record UserDTO(Long userId, String username, String pictureUrl, String email, String password) {
}
