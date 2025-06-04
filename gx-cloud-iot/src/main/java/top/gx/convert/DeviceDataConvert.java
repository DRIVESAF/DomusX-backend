package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.entity.DeviceData;
import top.gx.vo.DeviceDataVO;

import java.util.List;

/**
 * 设备数据转换器
 */
@Mapper
public interface DeviceDataConvert {
  DeviceDataConvert INSTANCE = Mappers.getMapper(DeviceDataConvert.class);

  DeviceDataVO convert(DeviceData entity);

  List<DeviceDataVO> convertList(List<DeviceData> list);
}