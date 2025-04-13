package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MallProduct;

/**
 * 商品管理Service接口
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public interface IMallProductService 
{
    /**
     * 查询商品管理
     * 
     * @param id 商品管理主键
     * @return 商品管理
     */
    public MallProduct selectMallProductById(Long id);

    /**
     * 查询商品管理列表
     * 
     * @param mallProduct 商品管理
     * @return 商品管理集合
     */
    public List<MallProduct> selectMallProductList(MallProduct mallProduct);

    /**
     * 新增商品管理
     * 
     * @param mallProduct 商品管理
     * @return 结果
     */
    public int insertMallProduct(MallProduct mallProduct);

    /**
     * 修改商品管理
     * 
     * @param mallProduct 商品管理
     * @return 结果
     */
    public int updateMallProduct(MallProduct mallProduct);

    /**
     * 批量删除商品管理
     * 
     * @param ids 需要删除的商品管理主键集合
     * @return 结果
     */
    public int deleteMallProductByIds(Long[] ids);

    /**
     * 删除商品管理信息
     * 
     * @param id 商品管理主键
     * @return 结果
     */
    public int deleteMallProductById(Long id);
}
