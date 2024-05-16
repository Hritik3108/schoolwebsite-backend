package com.numetry.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.entity.AttendanceRecord;
import com.numetry.entity.Staff;
import com.numetry.exceptions.UserNotFoundException;
import com.numetry.repository.AttendanceRecordRepository;
import com.numetry.repository.StaffRepository;

@RestController
@RequestMapping("/api/v1/auth/school")
public class AttendanceController {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @PostMapping("/attendance/mark")
    public ResponseEntity<String> markAttendance(@RequestParam String staffId,
                                                  @RequestParam String status) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new UserNotFoundException("Staff not found"));

        AttendanceRecord aRecord = new AttendanceRecord();
        aRecord.setStaff(staff);
        aRecord.setDate(LocalDate.now());
        aRecord.setStatus(status);

        attendanceRecordRepository.save(aRecord);

        return ResponseEntity.ok("Attendance marked successfully");
    }
    
    @GetMapping("/attendance/get")
    public ResponseEntity<AttendanceRecord> getAttendanceByStaffId(@RequestParam String staffId, @RequestParam LocalDate date) {
        try {
            AttendanceRecord record = attendanceRecordRepository.findByStaffAndDate(staffRepository.findById(staffId), date);

            if (record!=null) {
                return new ResponseEntity<>(record, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
        	return null;
        }
    }

    }

