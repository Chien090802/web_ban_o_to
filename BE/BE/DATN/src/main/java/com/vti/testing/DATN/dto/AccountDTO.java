package com.vti.testing.DATN.dto;

import com.vti.testing.DATN.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String username;
    private String email;
    private String fullName;
    private Role role;
}
