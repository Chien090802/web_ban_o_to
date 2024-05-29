package com.vti.testing.DATN.form.Account;

import com.vti.testing.DATN.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class CreatingAccountForm {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private Role role;
}
