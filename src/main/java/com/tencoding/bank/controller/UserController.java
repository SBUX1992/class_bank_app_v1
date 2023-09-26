package com.tencoding.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.bank.dto.SignInFormDto;
import com.tencoding.bank.dto.SignUpFormDto;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.repository.model.User;
import com.tencoding.bank.service.UserService;

@Controller
@RequestMapping({ "/user", "/" })
public class UserController {

	@Autowired // DI 처리
	private UserService userService;

	@Autowired // DI 처리
	private HttpSession session;

	// 회원 가입 페이지 요청
	// http://localhost:80/user/sign-up
	@GetMapping("/sign-up")
	public String signUp() {
		return "user/signUp";
	}

	// 로그인 페이지 요청
	// http://localhost:80/user/sign-in
	@GetMapping({ "/sign-in", "" })
	public String signIn() {
		return "user/signIn";
	}

	/**
	 * 회원 가입 처리
	 * 
	 * @param signUpFormDto
	 * @return 리다이렉트 처리 - 로그인 페이지
	 */
	@PostMapping("/sign-up")
	public String signUpProc(SignUpFormDto signUpFormDto) {

		// 1. 유효성 검사
		if (signUpFormDto.getUsername() == null || signUpFormDto.getUsername().isEmpty()) {
			throw new CustomRestfullException("username을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (signUpFormDto.getPassword() == null || signUpFormDto.getPassword().isEmpty()) {
			throw new CustomRestfullException("password를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (signUpFormDto.getFullname() == null || signUpFormDto.getFullname().isEmpty()) {
			throw new CustomRestfullException("fullname를 입력하세요", HttpStatus.BAD_REQUEST);
		}

		userService.signUp(signUpFormDto);
		return "redirect:/user/sign-in";
	}

	/**
	 * 로그인 로직 처리
	 * 
	 * @param signInFormDto
	 * @return 계좌 리스트 페이지로 리턴
	 */
	@PostMapping("/sign-in")
	public String signInProc(SignInFormDto signInFormDto) {

		// 1. 유효성 검사
		if (signInFormDto.getUsername() == null || signInFormDto.getUsername().isEmpty()) {
			throw new CustomRestfullException("username을 입력하시오", HttpStatus.BAD_REQUEST);
		}
		if (signInFormDto.getPassword() == null || signInFormDto.getPassword().isEmpty()) {
			throw new CustomRestfullException("password을 입력하시오", HttpStatus.BAD_REQUEST);
		}
		// 2. 서비스 -> 인증된 사용자 여부 확인
		// principal <-- 접근 주체
		User principal = userService.signIn(signInFormDto);
		principal.setPassword(null);
		// 3. 쿠기 + 세션
		session.setAttribute("principal", principal);

		return "redirect:/account/list";
	}

	/**
	 * 로그아웃 처리
	 * 
	 * @return 리다이렉트 - 로그인 페이지 이동
	 */
	@GetMapping("/logout")
	public String login() {
		session.invalidate();
		return "redirect:/user/sign-in";
	}

	// http://localhost:80/user/kakao/callback?code="abcd"
	@GetMapping("/kakao/callback")
	@ResponseBody // data 반환 명시
	public String kakaoCallback(@RequestParam String code) {
		System.out.println("메서드 동작");

		// POST 방식 - exchange() 메서드 활용
		// Header 헤더 구성
		// Body 구성

		RestTemplate rt = new RestTemplate();
		// 헤더 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// body 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "bad9831908a45b211ad3c212404258f7");
		params.add("redirect_uri", "http://localhost/user/kakao/callback");
		params.add("code", code);

		// HttpEntity 결합 (헤더 + 바디)
		HttpEntity<MultiValueMap<String, String>> reqMes = new HttpEntity<>(params, headers);

		// HTTP 요청
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, reqMes, String.class);
		
		// 액세스 토큰
		// 1. 회원 가입 여부 확인
		//  - 최초 사용자 라면 우리 회원 가입 맞는 형식을 만들어서 회원 가입 처리
		
		// 2. 로그인 -> 세션 메모리에 사용자를 등록 (세션 생성)

		return "카카오 액세스 토큰 받기 완료 : " + response;
	}

}