package com.zcy.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcy.beans.Student;
import com.zcy.dao.StudentDao;

@Service("studentServiceImpl")
public class StudentServiceImpl {
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	//事务
	@Transactional
	public void addStudent(Student student)
	{
		studentDao.insertStudent(student);
	}
}
