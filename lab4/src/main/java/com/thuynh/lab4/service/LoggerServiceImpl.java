package com.thuynh.lab4.service;

import com.thuynh.lab4.entity.Logger;
import com.thuynh.lab4.repository.LoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    LoggerRepo loggerRepo;

    public static final String DEFAULT_PRINCIPLE = "default_user";

    public void log(String opperation) {
        Logger logEntry = new Logger(DEFAULT_PRINCIPLE, opperation);
        loggerRepo.save(logEntry);
    }
}
