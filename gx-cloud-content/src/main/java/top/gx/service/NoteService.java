package top.gx.service;

import top.gx.dto.NoteDTO;
import top.gx.entity.Note;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.BaseService;
import top.gx.query.NoteQuery;
import top.gx.vo.NoteVO;

/**
 * @author Lenovo
 */
public interface NoteService extends BaseService<Note> {
    PageResult<NoteVO> page(NoteQuery query);
    PageResult<NoteVO> getNotesByCategoryId(Long categoryId,NoteQuery query);
    boolean publishNote(NoteDTO noteDTO);
}
