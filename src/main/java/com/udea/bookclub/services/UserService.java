package com.udea.bookclub.services;

import com.udea.bookclub.dtos.UserDTO;
import com.udea.bookclub.dtos.mappers.IUserMapper;
import com.udea.bookclub.exceptions.RepositoryException;
import com.udea.bookclub.models.User;
import com.udea.bookclub.repositories.IUserRepository;
import com.udea.bookclub.services.facade.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) throws RepositoryException {
        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new RepositoryException("Username already exist");
        }
        return userMapper.userToUserDTO(userRepository.save(userMapper.userDTOToUser(userDTO)));
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return userMapper.usersToUsersDTO(userRepository.findAll(pageable).toList());
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RepositoryException("User not found");
        }
        return userMapper.userToUserDTO(user.get());
    }

    @Override
    public UserDTO update(UserDTO userDTO) throws RepositoryException {
        if (userRepository.findById(userDTO.userId()).isEmpty()) {
            throw new RepositoryException("User not found");
        }
        Optional<User> user = userRepository.findByUsernameOrEmail(userDTO.username(), userDTO.email());
        if (user.isPresent()) {
            if (!user.get().getUserId().equals(userDTO.userId())){
                throw new RepositoryException("Username or email already exist");
            }
        }
        return userMapper.userToUserDTO(userRepository.save(userMapper.userDTOToUser(userDTO)));
    }
}
