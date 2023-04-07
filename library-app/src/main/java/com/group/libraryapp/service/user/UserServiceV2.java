package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.response.UserResponse;
import com.group.libraryapp.dto.calculator.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import org.springframework.stereotype.Service;
import com.group.libraryapp.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    /*
    아래 함수가 시작될 때 start transaction;
    함수가 예외없이 잘 끝나면 commit
    문제가 생기면 rollback
     */
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().
                map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        
        user.updateName(request.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userRepository.delete(user);
    }
}
