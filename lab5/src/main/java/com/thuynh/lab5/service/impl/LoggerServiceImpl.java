package com.thuynh.lab5.service.impl;

import com.thuynh.lab5.entity.Logger;
import com.thuynh.lab5.repository.LoggerRepo;
import com.thuynh.lab5.service.LoggerService;
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
