package com.example.travelfactory.repository;

import com.example.travelfactory.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {


}
