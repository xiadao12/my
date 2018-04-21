package com.zcy.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zcy.beans.Student;
import com.zcy.services.StudentService;
import com.zcy.services.StudentServiceImpl;

public class StudentController implements Controller{

	private StudentServiceImpl studentServiceImpl;
	
	public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl)
	{
		this.studentServiceImpl = studentServiceImpl;
	}
	
/*	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}*/

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String name = request.getParameter("name");
		int age = Integer.valueOf(request.getParameter("age"));
		
		Student student = new Student(name,age);
		
		studentServiceImpl.addStudent(student);
		
		return new ModelAndView("/welcome.jsp");
	}

}
