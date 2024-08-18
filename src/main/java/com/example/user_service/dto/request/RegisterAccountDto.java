package com.example.user_service.dto.request;

import com.example.user_service.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterAccountDto {
    private String username;
    private String password;
    private Role role;
}
