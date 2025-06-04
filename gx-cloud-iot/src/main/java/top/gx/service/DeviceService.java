package top.gx.service;

import top.gx.entity.Device;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.BaseService;
import top.gx.query.DeviceQuery;
import top.gx.vo.DeviceVO;

/**
 * @author Lenovo
 */
public interface DeviceService extends BaseService<Device> {
    PageResult<DeviceVO> page(DeviceQuery query);
    /**
     *
     *发送命令
     * @param deviceId 设备id
     * @param command 命令
     */
    void sendCommand(String deviceId, String command);
}
