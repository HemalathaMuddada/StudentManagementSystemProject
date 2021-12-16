package com.project.sms.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailData {
	

 private   String toEmail;
 private   String subject;
 private    String content;
 Map<String, Object> model;
 
}

