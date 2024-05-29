package com.vti.testing.DATN.dto;

import com.vti.testing.DATN.entity.Role;

import lombok.Data;

@Data
public class LoginInfoDto {

	private int id;

	private String fullName;

	private String username;

	private Role role;

	private String email;
}
