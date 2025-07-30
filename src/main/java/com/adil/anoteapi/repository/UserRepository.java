package com.adil.anoteapi.repository;

import com.adil.anoteapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
