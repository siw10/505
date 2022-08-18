package com.cos.security1.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="booking")
@Entity
@Data
public class BookingVo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	
	@Column(nullable = false, length = 100)
	private String username;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String phone;
	
	@Column(nullable = false, length = 50)
    private String bookingDate; // 날짜
	
	@Column(nullable = false, length = 50)
	private int price;
	
	@Column(nullable = false, length = 50)
	private String btype;
}
