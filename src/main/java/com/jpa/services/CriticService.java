package com.jpa.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Critic;
import com.jpa.models.User;
import com.jpa.repositories.CriticRepository;
//import com.jpa.security.WebSecurityConfig;

@CrossOrigin(origins = "*")
@RestController
public class CriticService {
	
//	@Autowired
//	WebSecurityConfig webSecurity;
	
	@Autowired
	CriticRepository criticRepository;
	
	
	@PostMapping("/api/critic/register")
	public Critic register(@RequestBody Critic critic, HttpSession session) {
		
		//user.setPassword(userDao.encryptPassword(user.getPassword()));
		Critic cc = criticRepository.save(critic);
		
		session.setAttribute("currentCritic", cc);
		
		return cc;
	}
	
	@GetMapping("/api/critic/{cid}/user")
	public List<User> findUserfollowers(
			@PathVariable("cid") int cid) {
		Optional<Critic> ocritic = criticRepository.findById(cid);
		if(ocritic.isPresent()) {
			Critic critic = ocritic.get();
			return critic.getUser();
		}
		return null;
	}
	
	@GetMapping("/api/critic")
	public List<Critic> findAllCritics(){
		return (List<Critic>) criticRepository.findAll();
	}
	
		@GetMapping("/api/critic/{cid}")
		public Critic findCriticById(
				@PathVariable("cid") int cid) {
			Optional<Critic> ocritic =  criticRepository.findById(cid);
			if(ocritic.isPresent()){
				return ocritic.get();
			}
			return null;
		}

}
