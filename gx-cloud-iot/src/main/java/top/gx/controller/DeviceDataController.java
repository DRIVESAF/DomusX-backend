package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.common.utils.Result;
import top.gx.query.DeviceDataQuery;
import top.gx.service.DeviceDataService;
import top.gx.vo.DeviceDataVO;

/**
 * 设备数据接口
 */
@RestController
@RequestMapping("device/data")
@Tag(name = "设备数据管理")
@AllArgsConstructor
public class DeviceDataController {
  private final DeviceDataService deviceDataService;

  @GetMapping("page")
  @Operation(summary = "分页查询")
  public Result<PageResult<DeviceDataVO>> page(
      @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
      @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer limit,
      @Parameter(description = "排序字段") @RequestParam(required = false) String order,
      @Parameter(description = "是否升序") @RequestParam(defaultValue = "true") Boolean asc,
      @Parameter(description = "设备ID") @RequestParam(required = false) Long deviceId,
      @Parameter(description = "数据类型") @RequestParam(required = false) String dataKey,
      @Parameter(description = "开始时间") @RequestParam(required = false) String startTime,
      @Parameter(description = "结束时间") @RequestParam(required = false) String endTime) {

    DeviceDataQuery query = new DeviceDataQuery();
    query.setPage(page);
    query.setLimit(limit);
    query.setOrder(order);
    query.setAsc(asc);
    query.setDeviceId(deviceId);
    query.setDataKey(dataKey);
    query.setStartTime(startTime);
    query.setEndTime(endTime);

    PageResult<DeviceDataVO> result = deviceDataService.page(query);
    return Result.ok(result);
  }
}