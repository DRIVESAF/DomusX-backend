package top.gx.dao;

import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.Device;
import top.gx.framework.mybatis.dao.BaseDao;
import java.util.List;
import java.util.Map;
/**
 * @author Lenovo
 */
@Mapper
public interface DeviceDao extends BaseDao<Device>{
    List<Device> getList(Map<String, Object> params);
}
