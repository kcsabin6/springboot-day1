package com.rab3tech.service;

public interface EmailService {

	String sendProfileEmail(String to, String from, String name, byte[] oldpic, byte[] newpic);

}
