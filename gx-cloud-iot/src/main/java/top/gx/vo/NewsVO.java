package top.gx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "资讯VO")
public class NewsVO implements Serializable {
    @Schema(description = "资讯ID")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    @Schema(description = "浏览量")
    private Integer viewCount;
    @Schema(description = "封面图片URL")
    private String coverImage;
    @Schema(description = "资讯来源")
    private String source;
}