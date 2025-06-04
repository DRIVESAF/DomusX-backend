package top.gx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.gx.convert.DeviceDataConvert;
import top.gx.dao.DeviceDataDao;
import top.gx.entity.DeviceData;
import top.gx.framework.common.constant.Constant;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import top.gx.query.DeviceDataQuery;
import top.gx.service.DeviceDataService;
import top.gx.vo.DeviceDataVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备数据服务实现
 */
@Service
@Slf4j
@AllArgsConstructor
public class DeviceDataServiceImpl extends BaseServiceImpl<DeviceDataDao, DeviceData> implements DeviceDataService {

  @Override
  public PageResult<DeviceDataVO> page(DeviceDataQuery query) {
    Map<String, Object> params = getParams(query);
    IPage<DeviceData> page = getPage(query);
    params.put(Constant.PAGE, page);
    List<DeviceData> list = baseMapper.getList(params);
    return new PageResult<>(DeviceDataConvert.INSTANCE.convertList(list), page.getTotal());
  }

  private Map<String, Object> getParams(DeviceDataQuery query) {
    Map<String, Object> params = new HashMap<>();
    params.put("deviceId", query.getDeviceId());
    params.put("dataKey", query.getDataKey());
    params.put("startTime", query.getStartTime());
    params.put("endTime", query.getEndTime());
    return params;
  }

  @Override
  public void saveDeviceData(Long deviceId, String dataKey, String dataValue, String unit) {
    DeviceData data = new DeviceData();
    data.setDeviceId(deviceId);
    data.setDataKey(dataKey);
    data.setDataValue(dataValue);
    data.setUnit(unit);
    baseMapper.insert(data);
  }
}