package com.example.bespringboot.controller;

import com.example.bespringboot.entity.Customer;
import com.example.bespringboot.entity.Staff;
import com.example.bespringboot.service.StaffService;
// import com.fasterxml.jackson.core.JacksonException;
// import com.fasterxml.jackson.core.JsonParseException;
// import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staff")
@CrossOrigin("http://localhost:4200")
public class StaffController {

    private final StaffService staffService;
    @PostMapping(value = "/staffRegister")
    public Staff postStaff(@RequestBody Staff staff){
        System.out.println(staff);
        return staffService.postStaff(staff);}

    @GetMapping("/staff")
    public List<Staff> getAllStaffs(){
        return staffService.findAll();
    }

    @GetMapping(value = "/staff/staffLogin/{email},{password}")
    public ResponseEntity<Staff> login(@PathVariable String email, @PathVariable String password){
        Optional<Staff> staff = staffService.findByEmailAndPassword(email,password);
        if (staff.isPresent()){
            return ResponseEntity.ok(staff.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**Id getting from database*/
    /*@GetMapping(value = "/staff/staffLogin/Id")
    public ResponseEntity<Staff> Id(){
        Optional<Staff> staff = staffService.findTopByOrderByIdDesc();
        if (staff.isPresent()){
            String Id = staff.get().getId().toString();
            return ResponseEntity.ok(staff.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }*/

    
    @GetMapping("/a/{name}")
    public ResponseEntity<List<Staff>> getiing(@PathVariable String name){
        List<Staff> s = staffService.findName(name);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    /** updating a staff */
    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateCustomer(@PathVariable Long id, @RequestBody Staff staff){
        /** search and get the staff from database */
        Optional<Staff> selectedStaff = staffService.findById(id);

        /**checking the staff record is in the database
         * if the records are there asign the updated values*/
        if (selectedStaff.isPresent()){
            Staff updateStaff = selectedStaff.get();
            updateStaff.setFirstName(staff.getFirstName());
            updateStaff.setLastName(staff.getLastName());
            updateStaff.setAddress(staff.getAddress());
            updateStaff.setRole(staff.getRole());
            updateStaff.setPhone(staff.getPhone());
            updateStaff.setEmail(staff.getEmail());
            updateStaff.setPassword(staff.getPassword());
            staffService.postStaff(updateStaff);
            return new ResponseEntity<>(updateStaff, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        Optional<Staff> selectedStaff = staffService.findById(id);
        if (selectedStaff.isPresent()){
            Staff staff = selectedStaff.get();
            staffService.delete(staff);
        }
    }
}
