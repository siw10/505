package com.cos.security1.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public int getNowHour() {
        Calendar cal = Calendar.getInstance();
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour+"현재시간");
        return hour;  
    }
    
    public boolean compareDate(Timestamp timestamp,LocalDateTime localDateTime) {
        LocalDateTime bookingTime=timestamp.toLocalDateTime();
        if(bookingTime.isEqual(localDateTime)){
           return true;
        }
        return false;
    }
    
    public Timestamp makeToTimestamp(int requestTime) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String today = sdf.format(date);
        String reservationdatetime=today+" "+requestTime+":0:0";

        return Timestamp.valueOf(reservationdatetime);

    }
    
    public JSONObject makeJson(boolean result,String sucupdatepwd) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result",result);
        jsonObject.put("messege", sucupdatepwd);
        return jsonObject;
    }
    
    public void emthySession(HttpSession httpSession) {
        httpSession.removeAttribute("bType");
    }
	
}
