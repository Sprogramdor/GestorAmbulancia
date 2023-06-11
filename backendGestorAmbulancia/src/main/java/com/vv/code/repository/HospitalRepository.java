package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vv.code.model.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
