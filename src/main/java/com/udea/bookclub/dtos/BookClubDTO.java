package com.udea.bookclub.dtos;

import com.udea.bookclub.models.User;

import java.util.List;

public record BookClubDTO(Long clubId, String name, String description, List<String> tags, String meetLink, Long userId) {
}
