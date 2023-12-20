package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.*;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单主表 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IOrderDetailService orderDetailService ;

    @PostMapping("/create")
    public ResponseEntity<?> create(int addressId,int[] goodsIds, int[] goodsCounts) {
        Order order = new Order();
        order.setUserId(userService.getLoginUser().getId());
        order.setAddressId(addressId);
        double priceTotal = 0;
        double postalFeeTotal = 0;
        for (int i = 0; i < goodsIds.length; i++) {
            Goods goods = goodsService.lambdaQuery().eq(Goods::getId, goodsIds[i]).one();
            priceTotal += (goods.getPrice().doubleValue() * goodsCounts[i]);
            postalFeeTotal += (goods.getPostalFee().doubleValue() * goodsCounts[i]);
        }
        order.setPriceTotal(BigDecimal.valueOf(priceTotal));
        order.setReductionTotal(BigDecimal.valueOf(priceTotal));
        order.setPostalFeeTotal(BigDecimal.valueOf(postalFeeTotal));
        order.setStatus("未支付");
        orderService.save(order);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }

    @GetMapping("/pay")
    public ResponseEntity<?> pay(int id) {
        Order order = orderService.lambdaQuery().eq(Order::getId, id).one();
        order.setStatus("已支付，等待发货");
        order.setPayTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderService.updateById(order);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }

    @GetMapping("/delivery")
    public ResponseEntity<?> delivery(int id,String expressCompany,String expressNumber) {
        Order order = orderService.lambdaQuery().eq(Order::getId, id).one();
        order.setExpressCompany(expressCompany);
        order.setExpressNumber(expressNumber);
        order.setStatus("已发货");
        order.setDeliveryTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderService.updateById(order);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }

    @GetMapping("/receive")
    public ResponseEntity<?> receive(int id) {
        Order order = orderService.lambdaQuery().eq(Order::getId, id).one();
        order.setStatus("已签收");
        order.setUpdateTime(LocalDateTime.now());
        orderService.updateById(order);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }

   @GetMapping( "/getOrderDetailById")
    public ResponseEntity<?> getDetailById(String id) {
       Order order = orderService.lambdaQuery().eq(Order::getId, id).one();
       Map<String,Object> map = new HashMap<>();
       map.put("address",addressService.lambdaQuery().eq(Address::getId,order.getAddressId()).one());
       map.put("detail",orderDetailService.lambdaQuery().eq(OrderDetail::getOrderId,order.getId()).one());
       map.put("order",order);
       return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,map));
   }

    @GetMapping("/getMyOrders")
    public ResponseEntity<?> getMyOrders(int page,int size) {
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,orderService.lambdaQuery().eq(Order::getUserId,userService.getLoginUser().getId()).orderBy(true,false, Order::getCreateTime).page(new Page<>(page, size)).getRecords()));
    }

    @GetMapping("/del")
    public ResponseEntity<?> del(int id){
        orderService.removeById(id);
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS));
    }
}
