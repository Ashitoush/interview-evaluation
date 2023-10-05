package com.ashitoush.interviewevaluation.repo;

import com.ashitoush.interviewevaluation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select * from users u where u.email = ?1")
    Optional<User> findUserByEmail(String email);
}
