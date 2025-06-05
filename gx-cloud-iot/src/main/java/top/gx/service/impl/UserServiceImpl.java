package top.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.gx.entity.Device;
import top.gx.entity.UserDevice;
import top.gx.mapper.UserDeviceMapper;
import top.gx.service.DeviceService;
import top.gx.service.UserService;

import java.time.LocalDateTime;

/**
 * @author Lenovo
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDeviceMapper,UserDevice> implements UserService{
    private final DeviceService deviceService;
    private final UserDeviceMapper userDeviceMapper;


    @Override
    public void bindDevice(Long deviceId,Long userId) {
//        QueryWrapper<Device> query = new QueryWrapper<>();
//        query.eq("device_id", deviceId);
//        Device device = deviceService.getOne(query);
        Device device = deviceService.getById(deviceId);
        if (device == null) {
            throw new RuntimeException("设备不存在");
        }

        // 查询表中是否存在该设备的记录以确认用户是不是主用户
        QueryWrapper<UserDevice> userDeviceQuery = new QueryWrapper<>();
        userDeviceQuery.eq("device_id", deviceId);
        UserDevice existingUserDevice = userDeviceMapper.selectOne(userDeviceQuery);

        // 创建用户设备关联记录
        UserDevice userDevice = new UserDevice();
        userDevice.setUserId(userId);
        userDevice.setDeviceId(deviceId);
        userDevice.setRegionId(0);
        userDevice.setIsOwner(existingUserDevice == null ? 1 : 0);
        userDevice.setDeleteFlag(0);
        userDevice.setCreateTime(LocalDateTime.now());

        // 插入记录
//        int result = userDeviceMapper.insert(userDevice);
        if (!save(userDevice)) {
            throw new RuntimeException("设备绑定失败");
        }
//        if (result <= 0) {
//            throw new RuntimeException("绑定设备失败");
//        }
    }
}
