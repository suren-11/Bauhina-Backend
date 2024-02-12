package com.example.bespringboot.service;

import com.example.bespringboot.entity.OrderReport;
import com.example.bespringboot.repo.OrderRepo;
import com.example.bespringboot.repo.OrderReportRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderRepoService {

    private final OrderReportRepo orderReportRepo;

    public OrderReport saveOrderReport(OrderReport orderReport){ return orderReportRepo.save(orderReport);}

    public Optional<OrderReport> findByDate(String date) { return orderReportRepo.findByDate(date);
    }
}
