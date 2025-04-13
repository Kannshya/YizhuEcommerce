package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品管理对象 mall_product
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public class MallProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long id;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantUserId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品主图 */
    @Excel(name = "商品主图")
    private String mainImage;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String description;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long stockQuantity;

    /** 商品类别 */
    @Excel(name = "商品类别")
    private String category;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantUserId(Long merchantUserId) 
    {
        this.merchantUserId = merchantUserId;
    }

    public Long getMerchantUserId() 
    {
        return merchantUserId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMainImage(String mainImage) 
    {
        this.mainImage = mainImage;
    }

    public String getMainImage() 
    {
        return mainImage;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setStockQuantity(Long stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public Long getStockQuantity() 
    {
        return stockQuantity;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantUserId", getMerchantUserId())
            .append("name", getName())
            .append("mainImage", getMainImage())
            .append("description", getDescription())
            .append("price", getPrice())
            .append("stockQuantity", getStockQuantity())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
