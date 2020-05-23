package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import com.kjstyle.jpaboard.web.dto.UserCreateReqDto;
import com.kjstyle.jpaboard.web.dto.UserUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    /**
     * 회원삭제 시 삭제여부를 true로 변경
     *
     * @param id
     * @return
     */
    @Transactional
    public Long delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."));
        user.deleted();
        return userRepository.save(user).getId();
    }
}