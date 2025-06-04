package top.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.DeviceThresholdRule;

/**
 * 设备阈值规则DAO
 */
@Mapper
public interface DeviceThresholdRuleDao extends BaseMapper<DeviceThresholdRule> {
}