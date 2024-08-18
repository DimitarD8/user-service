package com.example.user_service.mapper;

import com.example.user_service.dto.request.AccountRequestDto;
import com.example.user_service.dto.response.AccountResponseDto;
import com.example.user_service.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AccountMapper {

    public static AccountResponseDto mapToResponse(Account account){
        AccountResponseDto responseDto = new AccountResponseDto();
        responseDto.setId(account.getId());
        responseDto.setUsername(account.getUsername());
        responseDto.setRole(account.getRole());
        return responseDto;
    }
}
