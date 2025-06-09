package top.gx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.gx.entity.TUserTenantEntity;
import top.gx.framework.mybatis.dao.BaseDao;

import java.util.List;

/**
 * 用户-租户关联表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface TUserTenantDao extends BaseDao<TUserTenantEntity> {
    // 用户租户关联
    int insertUserTenant(@Param("userId") Long userId, @Param("tenantId") String tenantId);

}
