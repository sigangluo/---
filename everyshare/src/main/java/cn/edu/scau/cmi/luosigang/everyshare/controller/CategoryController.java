package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.Address;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.ICategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 种类 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/getAll/count")
    public ResponseEntity<?> getAllCount(){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,categoryService.count()));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,categoryService.list()));
    }
}
