package com.example.infofusionback.service;

import java.util.List;
import java.util.Optional;

import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.entity.converter.UserConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.infofusionback.entity.User;
import com.example.infofusionback.repository.UserRepository;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public static UserServiceImpl create(final UserRepository userRepository, final UserConverter userConverter) {
        return new UserServiceImpl(userRepository, userConverter);
    }

    @Override
    public UserBO saveUser(UserBO userBO) {
        User user = userRepository.saveAndFlush(userConverter.convertToEntity(userBO));
        return userConverter.convertToBO(user);
    }
    @Override
    public Optional<UserBO> findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userConverter::convertToBO);
    }

   /* @Override
    public UserBO getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserBO> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }*/



	
}
