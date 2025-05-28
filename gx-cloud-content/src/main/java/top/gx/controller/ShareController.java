package top.gx.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gx.convert.ShareConvert;
import top.gx.entity.Share;
import top.gx.framework.common.utils.Result;
import top.gx.service.ShareService;
import top.gx.vo.ShareVO;

import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/content")
@Tag(name = "内容模块")
@AllArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @GetMapping("shares")
    public Result<List<ShareVO>> getShareList() {
        List<Share> shares = shareService.list();
        List<ShareVO> list = ShareConvert.INSTANCE.convertList(shares);
        return Result.ok(list);
    }
}
