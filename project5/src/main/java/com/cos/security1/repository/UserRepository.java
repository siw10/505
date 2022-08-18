package com.cos.security1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 IoC됨. 이유는 JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User,Integer>{
	// findBy규칙 -> Username문법
	// select * from user where username = ?     ?에는 파라미터로 들어오는 username이 들어감
	Optional<User> findByUsername(String username);	//Jpa name 함수
	
//	public Optional<User> findById(int id);
	// select * from user where email = ?
//	public User findByEmail();   //example
}
