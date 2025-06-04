package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.gx.framework.common.utils.Result;
import top.gx.service.PlantWateringService;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/plant/watering")
@Tag(name = "植物浇水控制")
@AllArgsConstructor
public class PlantWateringController {
  private final PlantWateringService plantWateringService;

  @PostMapping("/threshold")
  @Operation(summary = "设置浇水阈值")
  public Result<String> setThreshold(@RequestParam String deviceId, @RequestParam float threshold) {
    return plantWateringService.setWateringThreshold(deviceId, threshold);
  }

  @PostMapping("/auto")
  @Operation(summary = "设置自动浇水")
  public Result<String> setAutoWatering(@RequestParam String deviceId, @RequestParam boolean enabled) {
    return plantWateringService.setAutoWatering(deviceId, enabled);
  }

  @PostMapping("/manual")
  @Operation(summary = "手动浇水控制")
  public Result<String> manualWatering(@RequestParam String deviceId, @RequestParam String command) {
    return plantWateringService.manualWatering(deviceId, command);
  }

  @GetMapping("/status")
  @Operation(summary = "获取浇水状态")
  public Result<Object> getStatus(@RequestParam String deviceId) {
    return plantWateringService.getWateringStatus(deviceId);
  }
}