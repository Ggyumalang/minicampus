package com.minicampus.minicampus.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordInput {

    private String userId;
    private String userName;

    private String id; //uuid를 받아오는 값
    private String password;
}
