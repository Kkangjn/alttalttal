package com.alttalttal.mini_project.global.token;

import com.alttalttal.mini_project.domain.user.entity.User;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceManagerImpl implements ServiceManager {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    public String getAccessToken(HttpServletRequest request) {
        return request.getHeader(jwtUtil.ACCESS_HEADER) == null? "" : request.getHeader(jwtUtil.ACCESS_HEADER);
    }

    @Override
    public String getRefreshToken(HttpServletRequest request) {
        return request.getHeader(jwtUtil.REFRESH_HEADER) == null? "" : request.getHeader(jwtUtil.REFRESH_HEADER);
    }

    @Override
    public User getUserFromToken(String token) {
        String email = jwtUtil.getEmailFromToken(token);
        return userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );
    }

    @Override
    public Long getUserIdFromToken(String token) {
        User user = getUserFromToken(token);
        return user.getId();
    }
}
