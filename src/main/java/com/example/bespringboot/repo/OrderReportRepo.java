package com.example.bespringboot.repo;

import com.example.bespringboot.entity.OrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderReportRepo extends JpaRepository<OrderReport,Long> {

    Optional<OrderReport> findByDate(String orderDate);

}
