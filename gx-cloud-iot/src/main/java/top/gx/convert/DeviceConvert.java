package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.dto.DeviceDTO;
import top.gx.entity.Device;
import top.gx.vo.DeviceVO;

import java.util.List;

/**
 * @author Lenovo
 */
@Mapper
public interface DeviceConvert {
    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);
    Device convert(DeviceDTO deviceDto);
    DeviceVO convert(Device device);
    List<DeviceVO> convertList(List<Device> list);
}
