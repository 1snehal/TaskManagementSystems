package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.pojo.JwtResponse;
import com.example.demo.pojo.Login;

	@RestController
	@RequestMapping("/api/auth")
	public class AuthControllers {

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody Login loginDto) throws AuthenticationException {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
	        String token = jwtUtil.generateToken(loginDto.getUsername());
	        return ResponseEntity.ok(new JwtResponse(token));
	    }
	}


