package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.gx.framework.common.utils.Result;
import top.gx.service.LightService;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/light/control")
@Tag(name = "智能灯控制")
@AllArgsConstructor
public class LightController {
    private final LightService lightService;

    @PostMapping("/setThreshold")
    @Operation(summary = "设置光线阈值")
    public Result<String> setThreshold(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setLightThreshold(deviceId, value);
    }

    @PostMapping("/setPower")
    @Operation(summary = "电源开关")
    public Result<String> setPower(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setSwitch(deviceId, value);
    }

    @PostMapping("/setSoundSensor")
    @Operation(summary = "设置声控模式")
    public Result<String> setSoundSensor(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setSound(deviceId, value);
    }

    @PostMapping("/setLightSensor")
    @Operation(summary = "设置光控模式")
    public Result<String> setLightSensor(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setLight(deviceId, value);
    }

    @PostMapping("/setAutoMode")
    @Operation(summary = "设置自动模式")
    public Result<String> setAutoMode(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setAuto(deviceId, value);
    }

    @PostMapping("/setColor")
    @Operation(summary = "设置灯光颜色")
    public Result<String> setColor(@RequestParam String deviceId, @RequestParam Integer value) {
        return lightService.setColor(deviceId, value);
    }
}
