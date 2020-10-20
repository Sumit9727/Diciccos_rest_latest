package com.prakat.middleware.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prakat.middleware.client.EmailClient;
import com.prakat.middleware.entity.AuthProvider;
import com.prakat.middleware.entity.RefreshToken;
import com.prakat.middleware.entity.Role;
import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.RefreshTokenRepository;
import com.prakat.middleware.repository.UserRepository;
import com.prakat.middleware.requestbeans.JwtRequest;
import com.prakat.middleware.requestbeans.OtpRequest;
import com.prakat.middleware.requestbeans.RegistrationRequest;
import com.prakat.middleware.responsebeans.JwtResponse;
import com.prakat.middleware.responsebeans.SuccessResponse;
import com.prakat.middleware.responsebuilder.SuccessResponseBuilder;
import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.security.TokenProvider;
import com.prakat.middleware.utils.AuthorizationUtils;
import com.prakat.middleware.utils.LoginUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@CrossOrigin
@Api(tags = "authentication")
public class AuthenticationController {
	public static final String googleLoginNote = "Login with Google access token";
	public static final String refreshTokenNote = "Refresh JWT token";
	public static final String facebookLoginNote = "Login with Facebook access token";
	public static final String loginNote = "Login with Email Id and Password";
	public static final String rgisterNote = "If new user, Registeration is Required";
	public static final String verifyOtpNote = "Verify the OTP obtain from Email Id";
	public static final String getTokenNote = "API used for internal purpose to generate JWT token";
	public static final String deleteRefreshTokenNote = "Delete refresh token for current logged user";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenRepository tokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private EmailClient emailClient;

	@Autowired
	private AuthorizationUtils utils ;

	@Autowired
	private LoginUtils loginUtils;

	@Autowired
	SuccessResponseBuilder responseBuilder ;

	@Autowired
	CustomUserDetailsService userService;

	@PostMapping(value = "/login")
	@ApiOperation(value = "Login",notes = loginNote)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmailId(),
						authenticationRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		JwtResponse response = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(response);
	}
	@PostMapping(value = "/login/google/{token}")
	@ApiOperation(value = "Login with Google",notes = googleLoginNote)
	public ResponseEntity<JwtResponse> googleLogin( @PathVariable String token) throws Exception {
		JwtResponse response  = loginUtils.loginWithGoogle(token);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/login/facebook/{token}")
	@ApiOperation(value = "Login with Facebook",notes = facebookLoginNote)
	public ResponseEntity<JwtResponse> facebookLogin(@PathVariable String token) throws Exception {
		JwtResponse response = loginUtils.loginWithFacebook(token);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/refreshtoken/{refreshtoken}")
	@ApiOperation(value = "Refresh token",notes = refreshTokenNote)
	public ResponseEntity<?> refreshtoken(@PathVariable String refreshtoken) throws Exception {
		JwtResponse response = loginUtils.getRefreshToken(refreshtoken);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value="/refreshtoken")
	@ApiOperation(value = "Delete refresh token",notes = deleteRefreshTokenNote, authorizations = {@Authorization(value="jwtToken")})
	public ResponseEntity<?> deleteRefreshToken() throws Exception {
		Integer userId = userService.getUserId();
		Optional<RefreshToken> userOptional = tokenRepository.findByUserId(userId);
		SuccessResponse response;
		if(userOptional.isPresent()) {
			tokenRepository.deleteById(userOptional.get().getId());
			response = responseBuilder.buildResponse("Refresh Token is deleted", null,null);
		}
		else
			response = responseBuilder.buildResponse("Refresh Token is registerd", null,null);
		return ResponseEntity.ok(response);
	}
	@PostMapping(value = "/register")
	@ApiOperation(value = "Register",notes = rgisterNote)
	public ResponseEntity<SuccessResponse> saveUser(@Valid @RequestBody RegistrationRequest registrationRequest) throws Exception {
		if(userRepository.existsByEmail(registrationRequest.getEmailId())) {
			throw new BussinessException("Email address already in use.");
		}
		// Creating user's account
		User user = new User();
		user.setName(registrationRequest.getUserName());
		user.setEmail(registrationRequest.getEmailId());
		user.setPassword(registrationRequest.getPassword());
		user.setImageUrl(registrationRequest.getProfilePicUrl());
		user.setProvider(AuthProvider.local);
		user.setRole(Role.ROLE_USER);
		Integer otp = utils.generateOtp();
		utils.addUser(otp, user);
		emailClient.sendEmail(user, otp);
		SuccessResponse response = responseBuilder.buildResponse("Please, Check your mailbox", null,null);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/otp/verify")
	@ApiOperation(value = "Verify OTP",notes = verifyOtpNote)
	public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpRequest otpRequest) throws Exception {
		Integer otp = otpRequest.getOtp();
		String emailId = otpRequest.getEmail();
		User user = utils.getUser(otp);	
		if(user == null || !(user.getEmail().equals(emailId)) ) {
			throw new BadCredentialsException(" Please Enter Valid Credentials");	
		}
		else{
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			User entity = userRepository.save(user);
			ResponseEntity<JwtResponse> jwtToken = null;
			if(entity !=null) {
				JwtRequest reuqest = new JwtRequest();
				reuqest.setEmailId(user.getEmail());
				reuqest.setPassword(password);
				jwtToken = createAuthenticationToken(reuqest);
			}
			return jwtToken;
		}	
	}

	@GetMapping(value = "/token")
	@ApiOperation(value = "JWT Token",notes = getTokenNote)
	public ResponseEntity<JwtResponse> getToken(@RequestParam(name = "jwt_token",required = true) String jwt_token,
			@RequestParam(name = "refresh_token",required = true) String refresh_token) throws Exception {
		if(jwt_token==null || refresh_token == null)
			throw new  BussinessException("Token can not be generated");
		return ResponseEntity.ok(new JwtResponse(jwt_token,refresh_token));
	}

}