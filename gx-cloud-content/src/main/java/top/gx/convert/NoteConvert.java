package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.dto.NoteDTO;
import top.gx.entity.Note;
import top.gx.vo.NoteVO;

import java.util.List;
/**
 * @author Lenovo
 */
@Mapper
public interface NoteConvert {
    NoteConvert INSTANCE = Mappers.getMapper(NoteConvert.class);
    NoteVO convert(Note entity);
    List<NoteVO> convertList(List<Note> list);
    Note convert(NoteDTO noteDTO);
}
