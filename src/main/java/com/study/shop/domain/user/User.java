package com.study.shop.domain.user;

import com.study.shop.web.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class User {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getUserEmail());
        user.setPassword(userDTO.getUserPassword());
        user.setName(userDTO.getUserName());
        return user;
    }
}
