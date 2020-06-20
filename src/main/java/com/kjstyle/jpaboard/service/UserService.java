package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import com.kjstyle.jpaboard.exceptions.NoSuchUserException;
import com.kjstyle.jpaboard.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
     * @param userDtoCreate
     * @return
     */
    @Transactional
    public Long save(UserDto.Create userDtoCreate) {
        return userRepository.save(userDtoCreate.toEntity()).getId();
    }

    /**
     * 회원 정보 변경 처리
     *
     * @param userDtoUpdate
     * @return
     */
    @Transactional
    public User save(UserDto.Update userDtoUpdate) {
        return userRepository.save(userDtoUpdate.toEntity());
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

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException());
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

    public Page<User> findAllNameContains(String searchKeyword, Pageable pageable) {
        return userRepository.findUsersByNameContains(searchKeyword, pageable);
    }

    public Page<User> findAllUserIdContains(String searchKeyword, Pageable pageable) {
        return userRepository.findUserByUserIdContains(searchKeyword, pageable);
    }

    public Page<User> findAllEmailContains(String searchKeyword, Pageable pageable) {
        return userRepository.findUserByEmailContains(searchKeyword, pageable);
    }
}