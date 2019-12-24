package com.example.aspect.TestAspect.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationHandleErrorImpl implements ApplicationHandleError{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationHandleErrorImpl.class);

    @Override
    public void handleError(Throwable ex) {
        logger.error(ex.getMessage());

    }
}
