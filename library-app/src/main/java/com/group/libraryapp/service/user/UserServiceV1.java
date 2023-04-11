package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.calculator.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import com.group.libraryapp.service.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();

    }
    public void updateUser(UserUpdateRequest request){

        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){

        if (userJdbcRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUser(name);
    }
}
