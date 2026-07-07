package com.easybuy.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Users",uniqueConstraints=@UniqueConstraint(columnNames={"userEmail","mobileNumber"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name="userName",nullable=false)
	private String userName;
	@Column(name="userEmail",nullable=false)
	private String userEmail;
	@Column(name="password",nullable=false)
	private String password;
	@Column(name="mobileNumber",nullable=false)
	private long mobileNumber;
	@Column(name="userAddress",nullable=false)
	private String userAddress;
	@Column(name="role",nullable=false)
	private String role;

}
