package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.entity.Share;
import top.gx.vo.ShareVO;

import java.util.List;
/**
 * @author Lenovo
 */
@Mapper
public interface ShareConvert {
    ShareConvert INSTANCE = Mappers.getMapper(ShareConvert.class);

    ShareVO convert(Share entity);

    List<ShareVO> convertList(List<Share> list);
}
