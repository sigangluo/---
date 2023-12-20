package cn.edu.scau.cmi.luosigang.everyshare.entity.vo;

import cn.edu.scau.cmi.luosigang.everyshare.entity.Goods;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoodsVo {


    private Integer id;

    private String username;

    private String category;

    private String name;

    private String description;

    private BigDecimal price;

    private BigDecimal postalFee;

    private Integer count;

    private Integer saleCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String masterPicture;


    public GoodsVo(Goods goods){
        this.id = goods.getId();
        this.name = goods.getName();
        this.description = goods.getDescription();
        this.price = goods.getPrice();
        this.postalFee = goods.getPostalFee();
        this.count = goods.getCount();
        this.saleCount = goods.getSaleCount();
        this.createTime = goods.getCreateTime();
        this.updateTime = goods.getUpdateTime();
    }
}
