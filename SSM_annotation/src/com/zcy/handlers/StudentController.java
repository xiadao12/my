package com.zcy.handlers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zcy.beans.Student;
import com.zcy.services.StudentServiceImpl;

@Controller
@RequestMapping("/test")
public class StudentController{

	//@Autowired
	//@Qualifier("studentService")
	@Resource(name="studentServiceImpl")
	private StudentServiceImpl studentServiceImpl;
	
	public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl)
	{
		this.studentServiceImpl = studentServiceImpl;
	}

	@RequestMapping("/register.do")
	public String doRegister(String name,int age) {
		Student student = new Student(name,age);
		studentServiceImpl.addStudent(student);
		return "/welcome.jsp";
	}

}
