package com.cos.security1.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.security1.dto.ResponseDto;
import com.cos.security1.model.BookingVo;
import com.cos.security1.service.BookingService;

@RestController
public class BookingApiController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public ResponseDto<Integer> booking(@RequestBody BookingVo bookingVo){
		System.out.println("BookingApiController 호출됨 : booking 호출" + bookingVo.getBtype());
		bookingService.insertBooking(bookingVo);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
