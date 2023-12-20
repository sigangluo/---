package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.*;
import cn.edu.scau.cmi.luosigang.everyshare.entity.vo.GoodsVo;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.ICategoryService;
import cn.edu.scau.cmi.luosigang.everyshare.service.IGoodsService;
import cn.edu.scau.cmi.luosigang.everyshare.service.IPictureService;
import cn.edu.scau.cmi.luosigang.everyshare.service.IUserService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPictureService pictureService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/get/count")
    public ResponseEntity<?> getCount(Integer categoryId,String keyword) {
        LambdaQueryChainWrapper<Goods> query = goodsService.lambdaQuery();
        if (categoryId != null && categoryId != 0 )
            query.eq(Goods::getCategoryId,categoryId);
        if (StringUtils.hasLength(keyword))
            query.like(Goods::getName,"%"+keyword+"%");
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,query.count()));
    }

    @GetMapping(value = "/get")
    //TODO 可以提供更多排序选择 orderBy
    public ResponseEntity<?> get(Integer categoryId,String keyword,int page,int size) {
        LambdaQueryChainWrapper<Goods> query = goodsService.lambdaQuery();
        if (categoryId != null && categoryId != 0)
            query.eq(Goods::getCategoryId,categoryId);
        if (StringUtils.hasLength(keyword))
            query.like(Goods::getName,"%"+keyword+"%");
        List<Goods> goods = query.orderBy(true, false, Goods::getCreateTime).page(new Page<>(page, size)).getRecords();
        List<GoodsVo> goodsVos = null;
        if (goods!=null){
            goodsVos = new ArrayList<>();
            for (Goods good : goods) {
                //TODO 可以改为用原生的Mybatis多表联查
                GoodsVo goodsVo = new GoodsVo(good);
                goodsVo.setCategory(categoryService.lambdaQuery().eq(Category::getId,good.getCategoryId()).one().getName());
                goodsVo.setUsername(userService.lambdaQuery().eq(User::getId,good.getUserId()).one().getUsername());
                goodsVo.setMasterPicture(pictureService.lambdaQuery().eq(Picture::getGoodsId,good.getId()).eq(Picture::getIsMaster,1).one().getPath());
                goodsVos.add(goodsVo);
            }
        }
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,goodsVos));
    }

    @GetMapping(value = "/getById")
    //TODO 可以提供更多排序选择 orderBy
    public ResponseEntity<?> getById(int id) {
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,goodsService.lambdaQuery().eq(Goods::getId,id).one()));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(Goods goods, MultipartFile master,MultipartFile[] others){
        goods.setUserId(userService.getLoginUser().getId());
        goodsService.save(goods);
        pictureService.save(master,goods.getId(),true);
        if (others != null){
            for (MultipartFile file : others) {
                pictureService.save(master,goods.getId(),false);
            }
        }

        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    //TODO 删改
}
