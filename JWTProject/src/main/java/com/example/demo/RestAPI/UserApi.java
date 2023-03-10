package com.example.demo.RestAPI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AuthResquest_AuthRespone.AuthRequest;
import com.example.demo.AuthResquest_AuthRespone.AuthRespone;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.entites.User;
import com.example.demo.entites.UserDetailsImpl;
import com.example.demo.jwt.JWTTokenUtil;

@RestController
@RequestMapping("/auth")
public class UserApi {
	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepo;
	@Autowired AuthenticationManager authManager;
	@Autowired JWTTokenUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody @Valid AuthRequest request){
		try {
			Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			System.out.println(userDetails.getAuthorities());
				String accessToken =jwtUtil.generateAccessToken(userDetails);
				AuthRespone response = new AuthRespone("login success", accessToken);
				return ResponseEntity.ok(response);
		}catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
	}
	@PostMapping("/register")
	public String register(@RequestBody @Valid AuthRequest request){
		PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		String rawPassword=request.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		User newUser = new User(request.getEmail(),encodedPassword);
		newUser.addRoles(roleRepo.getById(1));
		userRepo.save(newUser);
		return "Create user success";
	
	}
}
