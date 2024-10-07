package com.demo.recyclebinproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.recyclebinproject.model.RecycleBin;

public interface RecycleBinRepository extends JpaRepository<RecycleBin, Long>{

}
