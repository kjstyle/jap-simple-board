package com.kjstyle.jpaboard.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kjstyle.jpaboard.domain.BaseTimeEntity;
import com.kjstyle.jpaboard.domain.posts.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @ApiModelProperty(value = "회원키", allowEmptyValue = false)
    @Column(name = "user_no")
    @JsonProperty("userNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @ApiModelProperty(value = "회원아이디", allowEmptyValue = false)
    @Column(nullable = false, unique = true)
    private String userId;

    @ApiModelProperty(value = "회원명", allowEmptyValue = false)
    @Column
    private String name;

    @ApiModelProperty(value = "회원이메일", allowEmptyValue = false)
    @Column(nullable = false, unique = true)
    private String email;

    private boolean isDeleted;

    @Builder
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.isDeleted = false;
    }

    @Builder
    public User(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.isDeleted = false;
    }

    public void deleted() {
        this.isDeleted = true;
    }
}