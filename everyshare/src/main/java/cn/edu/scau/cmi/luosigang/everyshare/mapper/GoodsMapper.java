package cn.edu.scau.cmi.luosigang.everyshare.mapper;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
