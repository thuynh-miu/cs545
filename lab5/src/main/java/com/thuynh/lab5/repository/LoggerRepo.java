package com.thuynh.lab5.repository;

import com.thuynh.lab5.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepo extends JpaRepository<Logger, Long> {

}
