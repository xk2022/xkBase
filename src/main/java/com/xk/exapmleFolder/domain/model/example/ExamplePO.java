package com.xk.exapmleFolder.domain.model.example;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "example_user")
public class ExamplePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * var email1 = new EmailVO("user@example.com");
	 * System.out.println(email1); // ✅ 顯示 "us****@example.com"
	 * var email2 = new EmailVO("InvalidEmail");
	 */
	@Embedded
	private EmailVO email; // VO

	@Enumerated(EnumType.STRING)
	private RoleEnum role; // ENUM

}
