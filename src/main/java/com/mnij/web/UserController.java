package com.mnij.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnij.domain.User;
import com.mnij.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//회원가입 화면
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	//수정할 회원정보 출력
	@GetMapping("/form/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "user/updateForm";
	}
	
	//회원정보 수정
	@PutMapping("/{id}")
	public String updateUser(@PathVariable long id, User updateUser) {
		
		User user = userRepository.findById(id).get();
		user.update(updateUser);
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	//사용자 목록 출력
	@GetMapping("") 
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
		
	//users 로 post 요청이 들어오면 - user 객체 생성 회원가입
	@PostMapping("") 
	public String createUser(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}
	
	//로그인 화면
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		// DB에 없는 userId가 입력되면 다시 로그인 실패 화면으로 보낸다.
		if(user == null) {
			System.out.println("login Failure!");
			return "user/login_failed";
		}
		// 패스워드가 일치하지 않으면
		if(!password.equals(user.getPassword())) {
			System.out.println("login Failure!");
			return "user/login_failed";
		}
		//모든 정보가 일치하면 세션에 추가
		//키 : user 값 : userId 와 일치하는 user
		System.out.println("login Success!");
		session.setAttribute("user",  user);
		
		return "redirect:/";
	}
	
}
