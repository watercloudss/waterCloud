package com.watercloud.webmagic.service;

public interface DistributedLockFactoryService {
    DistributedLock getDistributedLock(String key);
}
