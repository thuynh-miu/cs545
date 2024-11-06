package com.thuynh.lab5.service;

import com.thuynh.lab5.entity.dto.request.LoginRequest;
import com.thuynh.lab5.entity.dto.request.RefreshTokenRequest;
import com.thuynh.lab5.entity.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
