package com.example.user_service.controller;

import com.example.user_service.dto.request.AccountRequestDto;
import com.example.user_service.dto.request.RegisterAccountDto;
import com.example.user_service.dto.response.AccountResponseDto;
import com.example.user_service.entity.Account;
import com.example.user_service.service.AccountDetailsService;
import com.example.user_service.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final AccountDetailsService detailsService;

    public AuthController(KafkaTemplate<String, String> kafkaTemplate, AccountDetailsService detailsService) {
        this.detailsService = detailsService;
        this.jwtUtil = new JwtUtil(kafkaTemplate);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponseDto> login(@RequestBody AccountRequestDto accountRequestDto) {
        AccountResponseDto responseDto = detailsService.loadAccount(accountRequestDto);
        String jwt = jwtUtil.generateToken(responseDto.getUsername());
        responseDto.setToken(jwt);

        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody RegisterAccountDto account){
        return ResponseEntity.ok(detailsService.saveAccount(account));
    }
}
