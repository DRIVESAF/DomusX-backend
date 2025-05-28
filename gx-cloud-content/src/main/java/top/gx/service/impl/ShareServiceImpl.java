package top.gx.service.impl;

import org.springframework.stereotype.Service;
import top.gx.dao.ShareDao;
import top.gx.entity.Share;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import top.gx.service.ShareService;

/**
 * @author Lenovo
 */
@Service
public class ShareServiceImpl extends BaseServiceImpl<ShareDao, Share> implements ShareService {
}
