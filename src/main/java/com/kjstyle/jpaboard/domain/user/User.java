package com.kjstyle.jpaboard.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kjstyle.jpaboard.domain.BaseTimeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * User Entity
 * 단순한 CURD의 경우 Dto로 직접 사용
 */
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

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts = new ArrayList<>();

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

    @Builder
    protected User(Long id, String userId, String name, String email, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.isDeleted = false;
        super.setCreatedDate(createdDate);
        super.setModifiedDate(modifiedDate);
    }

    public void deleted() {
        this.isDeleted = true;
    }
}