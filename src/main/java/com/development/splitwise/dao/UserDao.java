package com.development.splitwise.dao;

import com.development.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User>  findByPhone(Long phone);

    public User save(User user);
}
