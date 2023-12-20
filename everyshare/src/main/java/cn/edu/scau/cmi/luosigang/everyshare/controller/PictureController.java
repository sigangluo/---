package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.Goods;
import cn.edu.scau.cmi.luosigang.everyshare.entity.Picture;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.IPictureService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * 图片 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/picture")
public class PictureController {
    @Autowired
    private IPictureService pictureService;
    @GetMapping(value = "/getByGoods")
    public ResponseEntity<?> getByGoods(String goodsId) {
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,pictureService.lambdaQuery().eq(Picture::getGoodsId,goodsId).eq(Picture::getIsMaster,0).list()));
    }
}
