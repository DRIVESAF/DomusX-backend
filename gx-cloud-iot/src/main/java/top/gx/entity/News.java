package top.gx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("t_news")
public class News {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Long adminId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
    private Integer viewCount;
    private Integer status;
    private String coverImage;
    private String source;
}