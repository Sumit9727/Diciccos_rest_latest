package com.prakat.middleware.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.User;

@Component
public class AuthorizationUtils {
	
	static LinkedHashMap<Integer , User> cache = new LinkedHashMap<Integer , User>() {
		protected boolean removeEldestEntry(Map.Entry<Integer, User> eldest) {
			return size() > 50;
		}
	};
	public Integer generateOtp() {
		Random random = new Random();
		Integer otp= random.nextInt(8000)+1000;
		return otp ;
	}
	public User getUser(Integer otp) {
		return cache.getOrDefault(otp, null);
	}
	public void addUser(Integer otp , User user) {
		cache.put(otp, user);
	}
	public boolean verifyOtp(Integer otp) {
		User user = cache.getOrDefault(otp, null);
		if(user != null)
			return true;
		else
			return false;
	}
}
