package com.example.bespringboot.repo;
import com.example.bespringboot.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmailAndPassword(String email,String password);

    Optional<Staff> findTopByOrderByIdDesc();

    List<Staff> findByFirstNameStartingWith(String st);
}
