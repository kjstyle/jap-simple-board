package com.kjstyle.jpaboard.domain.posts;

import com.kjstyle.jpaboard.domain.BaseTimeEntity;
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
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    private String content;
    private String author;

    @Builder
    public Post(User user, String title, String content, String author) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
