package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserAlbumsDTO {
    private Long id;
    private String imageLink;
    private String name;
    private Long userId;

}
