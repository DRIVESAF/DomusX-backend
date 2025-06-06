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
import top.gx.vo.DeviceVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        if (!save(userDevice)) {
            throw new RuntimeException("设备绑定失败");
        }
    }

    @Override
    public List<DeviceVO> getDevicesByUser(Long userId) {
        // 查询用户所有未删除的设备关联记录
        QueryWrapper<UserDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("delete_flag", 0);
        List<UserDevice> userDevices = userDeviceMapper.selectList(queryWrapper);

        // 根据设备ID查询设备信息
        List<DeviceVO> deviceVOS = new ArrayList<>();
        for (UserDevice userDevice : userDevices) {
            Device device = deviceService.getById(userDevice.getDeviceId());
            if (device != null) {
                DeviceVO deviceVO = new DeviceVO();
                deviceVO.setId(device.getId());
                deviceVO.setDeviceId(device.getDeviceId());
                deviceVO.setName(device.getName());
                deviceVO.setType(device.getType());
                deviceVO.setStatus(device.getStatus());
                deviceVO.setCreateTime(device.getCreateTime());
                deviceVOS.add(deviceVO);
            }
        }
        return deviceVOS;
    }

    @Override
    public void unBindDevice(Long deviceId, Long userId) {
        // 查询用户与设备的关联记录
        QueryWrapper<UserDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("device_id", deviceId)
                .eq("delete_flag", 0);
        UserDevice userDevice = userDeviceMapper.selectOne(queryWrapper);

        if (userDevice == null) {
            throw new RuntimeException("设备未绑定或已解绑");
        }

        // 更新delete_flag为1
        userDevice.setDeleteFlag(1);
        userDeviceMapper.updateById(userDevice);
    }
}
