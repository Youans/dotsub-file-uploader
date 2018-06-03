/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.constants.enums;

import com.dotsub.fileuploader.service.storage.StorageStrategyService;
import lombok.Getter;

/**
 * The enum Storage template enum.
 *
 * @author Youans Ezzat
 */
public enum StorageTemplateEnum {

    /**
     * Local storage template enum.
     */
    LOCAL(StorageStrategyService.class), /**
     * Amazon s 3 storage template enum.
     */
    AMAZON_S3(StorageStrategyService.class);

    @Getter private Class<? extends StorageStrategyService> storageStrategyServiceClass;

    StorageTemplateEnum(Class<? extends StorageStrategyService> storageStrategyServiceClass) {
        this.storageStrategyServiceClass = storageStrategyServiceClass;
    }

    /**
     * Gets strategy enum by strategy service.
     *
     * @param storageStrategyServiceClass the storage strategy service class
     * @return the strategy enum by strategy service
     */
    public StorageTemplateEnum getStrategyEnumByStrategyService(Class<? extends StorageStrategyService> storageStrategyServiceClass) {
        for (StorageTemplateEnum strategyEnum : values()) {
            if (strategyEnum.storageStrategyServiceClass == storageStrategyServiceClass) {
                return strategyEnum;
            }
        }
        return null;
    }

}
