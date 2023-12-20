package cn.edu.scau.cmi.luosigang.everyshare.service.impl;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Order;
import cn.edu.scau.cmi.luosigang.everyshare.mapper.OrderMapper;
import cn.edu.scau.cmi.luosigang.everyshare.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表 服务实现类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
