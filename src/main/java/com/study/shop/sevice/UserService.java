package com.study.shop.sevice;

import com.study.shop.domain.item.Item;
import com.study.shop.domain.user.User;
import com.study.shop.domain.user.UserRepository;
import com.study.shop.web.dto.ItemDTO;
import com.study.shop.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final UserRepository userRepository = null;

    public void save(UserDTO userDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        User user = User.toUser(userDTO);
        userRepository.save(user);  // 여기서 save는 jpa가 제공해주는 메소드
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public UserDTO login(UserDTO userDTO) { //entity(user)는 service클래스, controller에서는 dto객체
        /*
            1. 회원이 입력한 이메일로 DB에서 조회
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            User user = byEmail.get();
            if (user.getPassword().equals(userDTO.getPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                UserDTO dto = UserDTO.toUserDTO(user);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다
            return null;
        }
    }

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList) {
            userDTOList.add(UserDTO.toUserDTO(user));
            /*
            UserDTO userDTO = UserDTO.toUserDTO(user);
            userDTOList.add(userDTO);
             */
        }
        return userDTOList;
    }

    public UserDTO updateForm(String myEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(myEmail);
        if (optionalUser.isPresent()) {
            return UserDTO.toUserDTO(optionalUser.get());
        } else {
            return null;
        }
    }

    public void update(UserDTO userDTO) {
        userRepository.save(User.toUpdateUser(userDTO));
    }

    public static UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);  // 옵셔널 객체를
        if (optionalUser.isPresent()) {                                     // 컨트롤러에 보내준다   */
            return UserDTO.toUserDTO(optionalUser.get());       // 윗 세줄을 한줄로 표현
        } else {
            return null;
        }
    }
}
