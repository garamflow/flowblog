package com.github.garamflow.flowblog.dto;


public record AddUserRequest (
        String email,
        String password,
        String nickname
){
}
