package com.example.user_service.service;

import com.example.user_service.dto.request.AccountRequestDto;
import com.example.user_service.dto.request.RegisterAccountDto;
import com.example.user_service.dto.response.AccountResponseDto;
import com.example.user_service.entity.Account;
import com.example.user_service.exception.AccountNotFoundException;
import com.example.user_service.mapper.AccountMapper;
import com.example.user_service.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), new ArrayList<>());
    }

    public Account saveAccount(RegisterAccountDto account){
        Account newAccount = new Account();
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(account.getPassword());
        newAccount.setRole(account.getRole());
        return accountRepository.save(newAccount);
    }

    public AccountResponseDto loadAccount(AccountRequestDto requestDto){
        Account account = accountRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + requestDto.getUsername()));
        return AccountMapper.mapToResponse(account);
    }

}
