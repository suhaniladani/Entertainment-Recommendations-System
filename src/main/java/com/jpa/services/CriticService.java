package com.jpa.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Critic;
import com.jpa.repositories.CriticRepository;
import com.jpa.security.WebSecurityConfig;

@CrossOrigin(origins = "*")
@RestController
public class CriticService {
	
	@Autowired
	WebSecurityConfig webSecurity;
	
	@Autowired
	CriticRepository criticRepository;
	
	
	@PostMapping("/api/critic/register")
	public Critic register(@RequestBody Critic critic, HttpSession session) {
		
		//user.setPassword(userDao.encryptPassword(user.getPassword()));
		Critic cc = criticRepository.save(critic);
		
		session.setAttribute("currentCritic", cc);
		
		return cc;
	}

}
