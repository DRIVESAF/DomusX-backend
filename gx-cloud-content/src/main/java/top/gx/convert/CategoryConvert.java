package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.entity.Category;
import top.gx.vo.CategoryVO;
import java.util.List;

/**
 * @author Lenovo
 */
@Mapper
public interface CategoryConvert {
    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);
    CategoryVO convert(Category entity);
    List<CategoryVO> convertList(List<Category> list);
}
