package cn.edu.scau.cmi.luosigang.everyshare.service.impl;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Picture;
import cn.edu.scau.cmi.luosigang.everyshare.mapper.PictureMapper;
import cn.edu.scau.cmi.luosigang.everyshare.service.IPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * 图片 服务实现类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements IPictureService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadRootPath;
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public Boolean save(MultipartFile file, Integer goodsId, Boolean isMaster){
        String orgName = file.getOriginalFilename();
        String extName = orgName.substring(orgName.lastIndexOf('.'));
        String destName = UUID.randomUUID().toString().toUpperCase() + extName;
        Picture picture = new Picture();
        picture.setPath(destName);
        picture.setGoodsId(goodsId);
        picture.setIsMaster(isMaster);
        try {
            File localFile = new File(uploadRootPath, destName);
            file.transferTo(localFile);
            pictureMapper.insert(picture);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
