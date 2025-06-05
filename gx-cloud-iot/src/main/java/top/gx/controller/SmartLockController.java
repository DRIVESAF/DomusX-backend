package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.gx.framework.common.utils.Result;
import top.gx.service.SmartLockService;

/**
 * 智能门锁控制
 * @author WangL
 */
@RestController
@RequestMapping("api/lock")
@Tag(name = "智能门锁控制")
@AllArgsConstructor
public class SmartLockController {
    private final SmartLockService smartLockService;

    @PostMapping("/control")
    @Operation(summary = "手机控制开关门")
    public Result<String> controlLock(@RequestParam String deviceId, @RequestParam String command) {
        // command: open/close
        return smartLockService.controlLock(deviceId, command);
    }

    @PostMapping("/touch")
    @Operation(summary = "触控功能开关")
    public Result<String> controlTouch(@RequestParam String deviceId, @RequestParam String command) {
        // command: touch-on/touch-off
        return smartLockService.controlTouch(deviceId, command);
    }

    @PostMapping("/sensor")
    @Operation(summary = "人体红外感应功能开关")
    public Result<String> controlSensor(@RequestParam String deviceId, @RequestParam String command) {
        // command: sensor-on/sensor-off
        return smartLockService.controlSensor(deviceId, command);
    }
}