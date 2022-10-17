package com.thuthao.btl1_react.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuthao.btl1_react.entities.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

}
