package top.gx.dto;

import lombok.Data;

/**
 * @author Lenovo
 */
@Data
public class DeviceDTO {
    private String deviceId;
    private String name;
    private Integer type;
    private Boolean status;
}
