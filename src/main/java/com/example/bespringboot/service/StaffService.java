package com.example.bespringboot.service;

import com.example.bespringboot.entity.Staff;
import com.example.bespringboot.repo.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepo staffRepo;

    /** save a staff */
    public Staff postStaff(Staff staff){return staffRepo.save(staff);}

    public List<Staff> findAll(){return staffRepo.findAll();}

    public Optional<Staff> findByEmailAndPassword(String email, String password){
        return staffRepo.findByEmailAndPassword(email, password);
    }

    public Optional<Staff> findTopByOrderByIdDesc(){return staffRepo.findTopByOrderByIdDesc();}

    public List<Staff> findName(String st){return staffRepo.findByFirstNameStartingWith(st);}

    /** searching staff by id */
    public Optional<Staff> findById(Long id) {return staffRepo.findById(id);
    }

    public void delete(Staff staff) { staffRepo.delete(staff);
    }
}
