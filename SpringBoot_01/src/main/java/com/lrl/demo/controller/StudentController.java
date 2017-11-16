package com.lrl.demo.controller;


import com.lrl.demo.domain.Student;
import com.lrl.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * y用户注册
     * @param number
     * @param password
     * @return
     */
    @GetMapping("/save")
    public Map save(@RequestParam String number , @RequestParam String password){
        Student student = new Student();
        student.setNumber(number);
        student.setPassword(password);
       return this.studentService.save(student);
    }

    /**
     * 用户登录
     * @param number
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Map login(@RequestParam String number , @RequestParam String password){
        return this.studentService.login(number,password);
    }

    @GetMapping("/findall")
    public List<Student> findAll(){
        return this.studentService.findAll();
    }
}
