package com.numetry.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.entity.AttendanceRecord;
import com.numetry.entity.Staff;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    AttendanceRecord findByStaffAndDate(Optional<Staff> staff, LocalDate date);
}

