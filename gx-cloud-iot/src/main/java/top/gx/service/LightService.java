package top.gx.service;

import top.gx.framework.common.utils.Result;

/**
 * @author Lenovo
 */
public interface LightService {
    //发送指令
    void sendCommand(String deviceId, String command,Integer value);

    //设置光线阈值
    Result<String> setLightThreshold(String deviceId,Integer value);

    //设置灯颜色
    Result<String> setColor(String deviceId,Integer value);

    //设置电源
    Result<String> setSwitch(String deviceId,Integer value);

    //设置声控模式
    Result<String> setSound(String deviceId,Integer value);

    //设置光控模式
    Result<String> setLight(String deviceId,Integer value);

    //设置自动模式
    Result<String> setAuto(String deviceId,Integer value);
}
