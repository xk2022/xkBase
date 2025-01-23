package com.xk.exapmleFolder.domain.model.example;

import lombok.Data;

@Data
public class ExampleBO {

	private String name;
	private EmailVO email;
	private RoleEnum role;

//    public UserBO(String name, Email email, Role role) {
//        this.name = name;
//        this.email = email;
//        this.role = role;
//    }

	public boolean isAdmin() {
		return this.role == RoleEnum.ADMIN;
	}

}
