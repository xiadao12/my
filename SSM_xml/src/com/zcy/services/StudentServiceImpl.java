package com.zcy.services;

import com.zcy.beans.Student;
import com.zcy.dao.StudentDao;

public class StudentServiceImpl {
	private StudentDao studentDao;
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}


	public void addStudent(Student student)
	{
		studentDao.insertStudent(student);
	}
}
