package com.project.sms.model;

import java.util.Random;


import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import com.project.sms.validation.ValidPassword;

@Component("RandomPassword")
public class PasswordGenerator {
	public String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
}