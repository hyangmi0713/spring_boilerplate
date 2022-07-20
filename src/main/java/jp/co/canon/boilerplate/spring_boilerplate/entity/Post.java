package jp.co.canon.boilerplate.spring_boilerplate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String Content;

    @Column
    private String attachPath;

    @Column
    private long createAt;

    @Column
    private long lastModifiedAt;

}
