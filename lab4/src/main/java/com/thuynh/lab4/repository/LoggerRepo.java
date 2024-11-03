package com.thuynh.lab4.repository;

import com.thuynh.lab4.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepo extends JpaRepository<Logger, Long> {

}
