package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import com.kjstyle.jpaboard.web.dto.UserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 등록 처리
     * @param userSaveReqDto
     * @return
     */
    @Transactional
    public Long save(UserSaveReqDto userSaveReqDto) {
        return userRepository.save(userSaveReqDto.toEntity()).getId();
    }

    /**
     * 사용자 리스트 (pageable)
     * @param pageable
     * @return
     */
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}