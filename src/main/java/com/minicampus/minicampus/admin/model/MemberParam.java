package com.minicampus.minicampus.admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberParam extends CommonParam{
    String userId;
}
