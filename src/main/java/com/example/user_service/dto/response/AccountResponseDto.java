package com.example.user_service.dto.response;

import com.example.user_service.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseDto {
    private String token;
    private Long id;
    private String username;
    private Role role;
}
