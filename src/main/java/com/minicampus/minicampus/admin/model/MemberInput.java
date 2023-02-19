package com.minicampus.minicampus.admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInput {
    private String userId;
    private String userStatus;
    private String password;
}
