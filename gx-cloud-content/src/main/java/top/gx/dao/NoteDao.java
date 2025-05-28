package top.gx.dao;

import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.Note;
import top.gx.framework.mybatis.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Mapper
public interface NoteDao extends BaseDao<Note> {
    List<Note> getList(Map<String, Object> params);
}
