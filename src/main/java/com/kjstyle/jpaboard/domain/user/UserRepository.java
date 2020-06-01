package com.kjstyle.jpaboard.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserId(String userId);

    Page<User> findUsersByNameContains(String searchKeyword, Pageable pageable);

    Page<User> findUserByUserIdContains(String searchKeyword, Pageable pageable);

    Page<User> findUserByEmailContains(String searchKeyword, Pageable pageable);
}