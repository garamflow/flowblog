package com.github.garamflow.flowblog.service;

import com.github.garamflow.flowblog.domain.User;
import com.github.garamflow.flowblog.dto.AddUserRequest;
import com.github.garamflow.flowblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest request) {
        return userRepository.save(User.builder()
                        .email(request.email())
                        .nickname(request.nickname())
                        .password(bCryptPasswordEncoder.encode(request.password()))
                        .role(User.UserRole.USER)
                        .build()).getId();
    }
}
