package com.lrl.demo.service;


import com.lrl.demo.domain.Student;
import com.lrl.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

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
}
