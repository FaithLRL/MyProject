package com.lrl.demo.repository;


import com.lrl.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , String>{
    Student findByNumberAndAndPassword(String number , String password);

    Student findByNumber(String number);
}
