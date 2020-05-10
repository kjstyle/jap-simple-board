package com.kjstyle.jpaboard.service;

import com.kjstyle.jpaboard.domain.user.UserRepository;
import com.kjstyle.jpaboard.web.dto.UserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveReqDto userSaveReqDto) {
        return userRepository.save(userSaveReqDto.toEntity()).getUserNo();
    }
}
