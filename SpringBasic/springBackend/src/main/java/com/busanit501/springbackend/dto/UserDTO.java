package com.busanit501.springbackend.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long uid;
    private String email;
    private String name;
    private String password;
}
