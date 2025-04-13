package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MallProductMapper;
import com.ruoyi.system.domain.MallProduct;
import com.ruoyi.system.service.IMallProductService;

/**
 * 商品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@Service
public class MallProductServiceImpl implements IMallProductService 
{
    @Autowired
    private MallProductMapper mallProductMapper;

    /**
     * 查询商品管理
     * 
     * @param id 商品管理主键
     * @return 商品管理
     */
    @Override
    public MallProduct selectMallProductById(Long id)
    {
        return mallProductMapper.selectMallProductById(id);
    }

    /**
     * 查询商品管理列表
     * 
     * @param mallProduct 商品管理
     * @return 商品管理
     */
    @Override
    public List<MallProduct> selectMallProductList(MallProduct mallProduct)
    {
        mallProduct.setMerchantUserId(SecurityUtils.getUserId());
        return mallProductMapper.selectMallProductList(mallProduct);
    }

    /**
     * 新增商品管理
     * 
     * @param mallProduct 商品管理
     * @return 结果
     */
    @Override
    public int insertMallProduct(MallProduct mallProduct)
    {
        mallProduct.setMerchantUserId(SecurityUtils.getUserId());
        return mallProductMapper.insertMallProduct(mallProduct);
    }

    /**
     * 修改商品管理
     * 
     * @param mallProduct 商品管理
     * @return 结果
     */
    @Override
    public int updateMallProduct(MallProduct mallProduct)
    {
        return mallProductMapper.updateMallProduct(mallProduct);
    }

    /**
     * 批量删除商品管理
     * 
     * @param ids 需要删除的商品管理主键
     * @return 结果
     */
    @Override
    public int deleteMallProductByIds(Long[] ids)
    {
        return mallProductMapper.deleteMallProductByIds(ids);
    }

    /**
     * 删除商品管理信息
     * 
     * @param id 商品管理主键
     * @return 结果
     */
    @Override
    public int deleteMallProductById(Long id)
    {
        return mallProductMapper.deleteMallProductById(id);
    }
}
