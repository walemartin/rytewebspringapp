package com.rytesoft.rytewebspringapp.respository;

import com.rytesoft.rytewebspringapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findAllByUsernameContainingIgnoreCase(String searchTerm);
}


