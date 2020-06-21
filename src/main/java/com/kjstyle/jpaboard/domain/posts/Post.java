package com.kjstyle.jpaboard.domain.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjstyle.jpaboard.domain.BaseTimeEntity;
import com.kjstyle.jpaboard.domain.board.Board;
import com.kjstyle.jpaboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity

public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    // https://blogdeveloperspot.blogspot.com/2018/12/spring-boot-2-jpa-no-serializer-found.html : 자세한 원인 파악 필요
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}