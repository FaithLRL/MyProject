package com.lrl.demo.controller;


import com.lrl.demo.domain.Student;
import com.lrl.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    /**
     * 注册用户
     */
    @GetMapping("/save")
    public void save(@RequestParam String number , @RequestParam String password){
        Student student = new Student();
        student.setNumber(number);
        student.setPassword(password);
        this.studentService.save(student);
    }
}
