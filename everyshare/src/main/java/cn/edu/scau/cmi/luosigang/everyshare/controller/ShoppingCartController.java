package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.Address;
import cn.edu.scau.cmi.luosigang.everyshare.entity.ShoppingCart;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.IShoppingCartService;
import cn.edu.scau.cmi.luosigang.everyshare.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IUserService userService;
    @PostMapping("/save")
    public ResponseEntity<?> save(ShoppingCart shoppingCart){
        shoppingCart.setUserId(userService.getLoginUser().getId());
        shoppingCartService.save(shoppingCart);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @GetMapping("/del")
    public ResponseEntity<?> del(int id){
        shoppingCartService.removeById(id);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @GetMapping("/del/batch")
    public ResponseEntity<?> delBatch(int[] ids){
        shoppingCartService.removeBatchByIds(Collections.singleton(ids));
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(ShoppingCart shoppingCart){
        shoppingCart.setUpdateTime(LocalDateTime.now());
        shoppingCartService.updateById(shoppingCart);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @GetMapping("/getAll/count")
    public ResponseEntity<?> getAllCount(){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,shoppingCartService.lambdaQuery().eq(ShoppingCart::getUserId, userService.getLoginUser().getUsername()).count()));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(int page,int size){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,shoppingCartService.lambdaQuery().eq(ShoppingCart::getUserId,userService.getLoginUser().getUsername()).orderBy(true,false,ShoppingCart::getCreateTime).page(new Page<>(page, size)).getRecords()));
    }
}
