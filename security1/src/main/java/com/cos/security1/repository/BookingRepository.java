package com.cos.security1.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.security1.model.BookingVo;
public interface BookingRepository extends JpaRepository<BookingVo,Integer>{
	
	@Query(value = "select bookingDate from booking group by bookingDate having count(bookingDate) > 2",nativeQuery = true)
	public ArrayList<String> findDisabledDays(); 
//
//	@Query(value = "select * from booking where username=?1 order by id desc",nativeQuery = true)
//    public List<BookingVo> findByUsername(String username);
	
}
