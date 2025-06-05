package top.gx.service;

import top.gx.framework.common.utils.Result;

/**
 * 智能门锁服务
 * @author WangL
 */
public interface SmartLockService {
    /**
     * 手机控制开关门
     */
    Result<String> controlLock(String deviceId, String command);

    /**
     * 触控功能开关
     */
    Result<String> controlTouch(String deviceId, String command);

    /**
     * 人体红外感应功能开关
     */
    Result<String> controlSensor(String deviceId, String command);
}