package com.aitijhya.research_paper_ally.services;

import org.springframework.stereotype.Service;

import com.aitijhya.research_paper_ally.User.User;
import com.aitijhya.research_paper_ally.User.UserRepository;
import com.aitijhya.research_paper_ally.requestDTO.UserDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User registerUser(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.email())
                .firstName(userDTO.firstName())
                .middleName(userDTO.middleName())
                .lastName(userDTO.lastName())
                .build();

        return userRepository.save(user);
    }
}
