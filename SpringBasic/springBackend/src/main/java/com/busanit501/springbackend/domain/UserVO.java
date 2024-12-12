package com.busanit501.springbackend.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    private Long uid;
    private String email;
    private String name;
    private String password;
}
