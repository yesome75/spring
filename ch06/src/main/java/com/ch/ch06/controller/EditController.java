package com.ch.ch06.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ch.ch06.model.Customer;
import com.ch.ch06.service.CustomerService;

@Controller
//클래스에 @RequestMapping정의하면 이 클래스의 모든 메서드에 공통으로 사용
@RequestMapping("/customer/{id}")
// 이 session있는 컨트롤러에서 editCustomer라는 이름으로 저장하여 같이 사용
@SessionAttributes("editCustomer")
public class EditController {
	@Autowired
	private CustomerService cs;
	@RequestMapping("/edit")   // /customer/{id}/edit와 같음
	public String edit(@PathVariable int id, Model model) {
		Customer customer = cs.select(id);
//		customer에 있는 데이터를 editCustomer라는 이름으로 jsp에 전달
		model.addAttribute("editCustomer", customer);
		return "redirect:enter";
	}
//	redirct:enter    @RequestMapping("/customer/{id}/enter")
//	redirct:/enter   @RequestMapping("/enter")
	@RequestMapping(value="/enter", method=RequestMethod.GET) // /customer/{id}/enter
//	@ModelAttribute는 객체로된 데이터를 받아올 때 사용
	public String enter(@ModelAttribute("editCustomer") Customer customer) {
		return "/customer/edit/enter";
	}
	@RequestMapping(value = "/enter", params = "event_process")
	public String review(@Valid @ModelAttribute("editCustomer") Customer customer, Errors errors) {
		if (errors.hasErrors()) return "customer/edit/enter";
		else return "customer/edit/review";
	}
	@RequestMapping(value = "review", params = "event_confirm")
	public String edit(@ModelAttribute("editCustomer") Customer customer, SessionStatus ss) {
		cs.update(customer);
		// 세션 메모리에 있던 editCustomer데이터 삭제
		ss.setComplete();
//		redirect:/customer는 class위에 있는 RequestMapping포함된 조건을 제외한다는 의미
		return "redirect:/customer";
	}
	@RequestMapping(value = "review", params = "event_cancel")
	public String cancel() {
//		/가 없으면 /customer/{id}/enter를 실행
		return "redirect:enter";
	}
} 
