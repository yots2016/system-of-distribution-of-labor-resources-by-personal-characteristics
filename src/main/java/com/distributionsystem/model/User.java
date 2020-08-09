package com.distributionsystem.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

}