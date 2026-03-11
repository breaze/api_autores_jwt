package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.dto.AuthResponse;
import com.breaze.PrimeraApiCasa.dto.LoginRequest;
import com.breaze.PrimeraApiCasa.dto.RegisterRequest;

public interface IAuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
