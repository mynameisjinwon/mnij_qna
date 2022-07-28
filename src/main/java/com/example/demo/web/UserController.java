package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println(user);
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list") 
	public String userList(Model model) {
		/* 
		for(User user : users) {
			String userId = user.getUserId();
			String password = user.getPassword();
			String name = user.getName();
			String email = user.getEmail();
			
			model.addAttribute("userId", userId);
			model.addAttribute("password", password);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
		}
		이렇게 하는 줄 알았다.
		근데 아니었다.
		*/ 
		//그냥 list 통째로 보내고 view에서 mustache 반복문을 통해서 출력한다.
		model.addAttribute("users", users);
		return "userList";
	}
}
