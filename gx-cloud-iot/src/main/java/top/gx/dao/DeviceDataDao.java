package top.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.DeviceData;
import java.util.List;
import java.util.Map;

/**
 * 设备数据DAO
 */
@Mapper
public interface DeviceDataDao extends BaseMapper<DeviceData> {
    List<DeviceData> getList(Map<String, Object> params);
}