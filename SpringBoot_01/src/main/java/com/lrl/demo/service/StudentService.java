package com.lrl.demo.service;


import com.lrl.demo.domain.Student;
import com.lrl.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 用户注册
     * @param student
     * @return
     */
    public Map save(Student student){
        Map map = new HashMap();
        if(StringUtils.hasText(student.getNumber())&&StringUtils.hasText(student.getPassword())){
           Student student1= this.studentRepository.findByNumberAndAndPassword(student.getNumber(),student.getPassword());

           if(student1 == null){
               map.put("msg","注册成功！");
              this.studentRepository.save(student);

           }else {
               map.put("msg","注册失败！");
           }
        }else {
            map.put("msg","参数不为空");
        }
        return map;
    }

    /**
     * 用户登录
     * @param number
     * @param password
     * @return
     */
    public Map login( String number , String password ){
        Map map = new HashMap();
        Student student = this.studentRepository.findByNumber(number);
        if(student!=null){
            Student student1 = this.studentRepository.findByNumberAndAndPassword(number,password);
            if(student1!=null){
                map.put("msg","登录成功！");
            }else {
                map.put("msg","登录失败！");
            }
        }else {
            map.put("msg","请注册用户！");
        }

        return map;
    }
    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }
}
