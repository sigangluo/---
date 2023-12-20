package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.Address;
import cn.edu.scau.cmi.luosigang.everyshare.entity.UserLogin;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.IAddressService;
import cn.edu.scau.cmi.luosigang.everyshare.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 地址 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IUserService userService;
    @GetMapping("/getAll/count")
    public ResponseEntity<?> getAllCount(){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,addressService.lambdaQuery().eq(Address::getUserId, userService.getLoginUser().getUsername()).count()));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(int page,int size){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,addressService.lambdaQuery().eq(Address::getUserId,userService.getLoginUser().getUsername()).orderBy(true,false,Address::getCreateTime).page(new Page<>(page, size)).getRecords()));
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(Address address){
        address.setUserId(userService.getLoginUser().getId());
        addressService.save(address);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @GetMapping("/del")
    public ResponseEntity<?> del(int id){
        addressService.removeById(id);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @GetMapping("/del/batch")
    public ResponseEntity<?> delBatch(int[] ids){
        addressService.removeBatchByIds(Collections.singletonList(ids));
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(Address address){
        address.setUpdateTime(LocalDateTime.now());
        addressService.updateById(address);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
}
