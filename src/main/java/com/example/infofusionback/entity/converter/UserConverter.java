package com.example.infofusionback.entity.converter;

import com.example.infofusionback.entity.AbstractBOEntityConverter;
import com.example.infofusionback.entity.BO.UserBO;
import com.example.infofusionback.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserConverter extends AbstractBOEntityConverter<User, UserBO> {

    public static UserConverter create() {
        return new UserConverter();
    }

    @Override
    public User convertToEntity(UserBO userBO) {
        User user = new User();
        user.setId(userBO.getId());
        user.setD(userBO.getD());
        user.setPassword(userBO.getPassword());
        user.setEmail(userBO.getEmail());
        user.setRole(userBO.getRole());

        return user;
    }


    @Override
    public UserBO convertToBO(User entity) {
        return new UserBO(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getD(),
                entity.getRole()
        );
    }
}
