package cn.edu.scau.cmi.luosigang.everyshare.service;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片 服务类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
public interface IPictureService extends IService<Picture> {

    Boolean save(MultipartFile file, Integer goodsId, Boolean isMaster);
}
