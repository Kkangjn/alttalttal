package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface ServiceManager {
    String getAccessToken(HttpServletRequest request);
    String getRefreshToken(HttpServletRequest request);
    User getUserFromToken(String token);
    Long getUserIdFromToken(String token);

}
