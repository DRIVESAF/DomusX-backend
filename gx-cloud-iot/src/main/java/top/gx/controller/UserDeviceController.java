package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.gx.convert.DeviceConvert;
import top.gx.dto.DeviceDTO;
import top.gx.framework.common.utils.Result;
import top.gx.service.UserService;
import top.gx.vo.DeviceVO;

import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/user")
@Tag(name = "用户模块")
@AllArgsConstructor
public class UserDeviceController {
    private final UserService userService;

    @PostMapping("/save")
    @Operation(summary = "用户绑定设备")
    public Result<String> bind(@RequestParam Long userId,@RequestParam Long deviceId) {
        userService.bindDevice(deviceId,userId);
        return Result.ok();
    }

    @PostMapping("/getAll")
    @Operation(summary = "查询用户绑定的所有设备")
    public Result<List<DeviceVO>> getAllByUserId(@RequestParam Long userId) {
        List<DeviceVO> devices = userService.getDevicesByUser(userId);
        return Result.ok(devices);
    }

    @PostMapping("/unBind")
    @Operation(summary = "用户解绑设备")
    public Result<List<DeviceVO>> userUnBindDevice(@RequestParam Long userId,@RequestParam Long deviceId) {
        userService.unBindDevice(deviceId,userId);
        return Result.ok();
    }
}
