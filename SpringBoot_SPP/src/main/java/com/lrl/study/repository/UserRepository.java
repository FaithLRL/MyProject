package com.lrl.study.repository;

import com.lrl.study.domain.User;
import com.lrl.study.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUserName(String name);

    Page<User> findAllByNickNameContaining(String searchText, Pageable pageable);
}
