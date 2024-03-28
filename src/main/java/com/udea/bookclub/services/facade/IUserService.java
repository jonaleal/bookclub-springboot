package com.udea.bookclub.services.facade;

import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.exceptions.RepositoryException;
import com.udea.bookclub.models.User;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserDTO save(UserDTO userDTO) throws RepositoryException;

    List<UserDTO> findAll(Pageable pageable) throws RepositoryException;
    
    UserDTO findById(Long id) throws RepositoryException;

    UserDTO update(UserDTO userDTO) throws RepositoryException;

    UserDTO findByUsername(String username);
}
