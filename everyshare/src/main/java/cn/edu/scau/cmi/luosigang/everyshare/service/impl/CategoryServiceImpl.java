package cn.edu.scau.cmi.luosigang.everyshare.service.impl;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Category;
import cn.edu.scau.cmi.luosigang.everyshare.mapper.CategoryMapper;
import cn.edu.scau.cmi.luosigang.everyshare.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 种类 服务实现类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
