package jp.co.canon.boilerplate.spring_boilerplate.entity;

import jp.co.canon.boilerplate.spring_boilerplate.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column @NotNull
    private String username;
    @Column @NotNull
    private String password;
    @Column @NotNull
    private String nickName;
    @Column @NotNull
    private String email;
    @Column
    private long createAt;

    @Column
    private long lastModifiedAt;

    @Column
    private String roles; //ROLE_USER, ROLE_ADMIN

    public User (UserDTO userDTO){
        id = userDTO.getId();
        username = userDTO.getUsername();
        nickName = userDTO.getNickName();
        email = userDTO.getEmail();
        createAt = userDTO.getCreateAt();
        lastModifiedAt = userDTO.getLastModifiedAt();
        roles = userDTO.getRoles();
    }

    public UserDTO toDto(){
        return UserDTO.builder()
                .id(id)
                .username(username)
                .nickName(nickName)
                .email(email)
                .createAt(createAt)
                .lastModifiedAt(lastModifiedAt)
                .roles(roles)
                .build();
    }

}
