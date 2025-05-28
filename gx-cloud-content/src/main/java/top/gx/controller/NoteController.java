package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.gx.dto.NoteDTO;
import top.gx.entity.Note;
import top.gx.feign.UserService;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.common.utils.Result;
import top.gx.query.NoteQuery;
import top.gx.service.NoteService;
import top.gx.vo.NoteDetailVO;
import top.gx.vo.NoteVO;
import top.gx.vo.UserVO;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/note")
@Tag(name = "笔记模块")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<NoteVO>> page(@ParameterObject @Valid NoteQuery query) {
        PageResult<NoteVO> page = noteService.page(query);
        return Result.ok(page);
    }

    @GetMapping("category")
    @Operation(summary = "分类笔记")
    public Result<PageResult<NoteVO>> category(@RequestParam("categoryId")Long categoryId,@ParameterObject @Valid NoteQuery query) {
        PageResult<NoteVO> page = noteService.getNotesByCategoryId(categoryId, query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "笔记详情")
    public Result<NoteDetailVO> getNote(@PathVariable Long id) {
        Note note = noteService.getById(id);
        Result<UserVO> res = userService.getUserById(note.getUserId());
        if (!ObjectUtils.isEmpty(res.getData())){
            UserVO userVO = res.getData();
            NoteDetailVO noteDetailVO = new NoteDetailVO();
            BeanUtils.copyProperties(note, noteDetailVO);
            noteDetailVO.setNickname(userVO.getNickname());
            noteDetailVO.setAvatar(userVO.getAvatar());
            return Result.ok(noteDetailVO);
        }else {
            NoteDetailVO noteDetailVO = new NoteDetailVO();
            BeanUtils.copyProperties(note, noteDetailVO);
            return Result.ok(noteDetailVO);
        }
    }

    @PostMapping("publish")
    @Operation(summary = "发布笔记")
    public Result<String> publishNote(@RequestBody NoteDTO noteDTO) {
        boolean flag = noteService.publishNote(noteDTO);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}
