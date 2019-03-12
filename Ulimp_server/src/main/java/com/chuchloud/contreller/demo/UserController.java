package com.chuchloud.contreller.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chuchloud.entity.demo.User;
import com.chuchloud.service.demo.UserService;
import com.chuchloud.utils.Result;

@RestController
@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
	@Autowired
	private UserService userservice;

	@RequestMapping("/ListUser")
	@ResponseBody
	public List<User> ListUser() {
		return userservice.ListUser();
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	@ResponseBody
	public Result login(@RequestParam("username") String userName, @RequestParam("password") String password,
			HttpServletResponse response) throws IOException {
		System.err.println(userName + ": " + password);
		PrintWriter out = response.getWriter();
		if (password.equals(userservice.selectUserByUsername(userName).getPassword())) {
			response.setContentType("application/json;charset=utf-8");
			out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");

		} else {
			response.setContentType("application/json;charset=utf-8");
			out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
		}
		out.flush();
		out.close();
		return null;
	}
}
