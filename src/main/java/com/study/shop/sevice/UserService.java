package com.study.shop.sevice;

import com.study.shop.domain.user.User;
import com.study.shop.repository.UserRepository;
import com.study.shop.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        User user = User.toUser(userDTO);
        userRepository.save(user);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }
}
