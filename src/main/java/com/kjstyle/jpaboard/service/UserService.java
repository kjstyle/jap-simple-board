package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import com.kjstyle.jpaboard.web.dto.UserCreateReqDto;
import com.kjstyle.jpaboard.web.dto.UserUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 생성 처리
     *
     * @param userCreateReqDto
     * @return
     */
    @Transactional
    public Long save(UserCreateReqDto userCreateReqDto) {
        return userRepository.save(userCreateReqDto.toEntity()).getId();
    }

    /**
     * 회원 정보 변경 처리
     *
     * @param userUpdateReqDto
     * @return
     */
    @Transactional
    public User save(UserUpdateReqDto userUpdateReqDto) {
        return userRepository.save(userUpdateReqDto.toEntity());
    }

    /**
     * 사용자 리스트 (pageable)
     *
     * @param pageable
     * @return
     */
    public Page<User> findAllWithPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * 사용자 리스트 (pageable)
     *
     * @param paginationParam
     * @return
     */
    public List<User> findAll(PaginationParam paginationParam) {
        // searchFilter 에 대한 파라미터 분해 처리
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}