package com.jpa.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.jpa.models.Seller;
import com.jpa.repositories.SellerRepository;

@CrossOrigin(origins = "*")
@RestController
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	@PostMapping("/api/seller/register")
	public Seller register(@RequestBody Seller seller, HttpSession session) {
		
		//user.setPassword(userDao.encryptPassword(user.getPassword()));
		Seller cs = sellerRepository.save(seller);
		
		session.setAttribute("currentSeller", cs);
		
		return cs;
	}

}
