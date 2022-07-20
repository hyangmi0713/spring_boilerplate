package jp.co.canon.boilerplate.spring_boilerplate.dto;

import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotEmpty
    private long id;
    private String username;
    private String nickName;
    private String email;
    private long createAt;
    private long lastModifiedAt;
    private String roles;
}
