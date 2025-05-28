package top.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.gx.convert.NoteConvert;
import top.gx.dao.NoteDao;
import top.gx.dto.NoteDTO;
import top.gx.entity.Note;
import top.gx.framework.common.constant.Constant;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import top.gx.query.NoteQuery;
import top.gx.service.NoteService;
import top.gx.vo.NoteVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Lenovo
 */
@Service
@AllArgsConstructor
public class NoteServiceImpl  extends BaseServiceImpl<NoteDao, Note> implements NoteService {
    @Override
    public PageResult<NoteVO> page(NoteQuery query) {
        //查询参数
        Map<String, Object> params = getParams(query);
        //分⻚查询
        IPage<Note> page = getPage(query);
        params.put(Constant.PAGE, page);
        //数据列表
        List<Note> list = baseMapper.getList(params);
        return new PageResult<>(NoteConvert.INSTANCE.convertList(list), page.getTotal());
    }

    @Override
    public PageResult<NoteVO> getNotesByCategoryId(Long categoryId,NoteQuery query) {
//        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Note::getCategoryId,categoryId);
//        List<Note> list = baseMapper.selectList(queryWrapper);
//        Map<String, Object> params = getParams(query);
//        IPage<Note> page = getPage(query);
//        params.put(Constant.PAGE, page);
//        return new PageResult<>(NoteConvert.INSTANCE.convertList(list),page.getTotal());

        // 创建查询条件
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Note::getCategoryId, categoryId);

        // 获取分页对象
        IPage<Note> page = getPage(query);

        // 使用分页查询方法
        IPage<Note> resultPage = baseMapper.selectPage(page, queryWrapper);

        // 将分页查询结果转换为视图对象列表
        List<NoteVO> noteVOList = NoteConvert.INSTANCE.convertList(resultPage.getRecords());

        // 封装分页结果并返回
        return new PageResult<>(noteVOList, resultPage.getTotal());
    }

    @Override
    public boolean publishNote(NoteDTO noteDTO) {
        Note note = NoteConvert.INSTANCE.convert(noteDTO);
        return baseMapper.insertOrUpdate(note);
    }

    private Map<String, Object> getParams(NoteQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", query.getTitle());
        return params;
    }
}
