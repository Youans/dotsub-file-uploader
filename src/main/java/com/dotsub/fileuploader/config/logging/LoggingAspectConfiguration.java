/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.config.logging;

import com.dotsub.fileuploader.aop.logging.LoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author Youans Ezzat
 */
@Configuration @EnableAspectJAutoProxy public class LoggingAspectConfiguration {
    private final Environment env;

    public LoggingAspectConfiguration(Environment env) {
        this.env = env;
    }

    @Bean @ConditionalOnProperty(name="logging.enable.aop",havingValue = "true") public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
