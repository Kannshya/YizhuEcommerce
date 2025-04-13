package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MallAddress;

/**
 * 收货地址Service接口
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public interface IMallAddressService 
{
    /**
     * 查询收货地址
     * 
     * @param id 收货地址主键
     * @return 收货地址
     */
    public MallAddress selectMallAddressById(Long id);

    /**
     * 查询收货地址列表
     * 
     * @param mallAddress 收货地址
     * @return 收货地址集合
     */
    public List<MallAddress> selectMallAddressList(MallAddress mallAddress);

    /**
     * 新增收货地址
     * 
     * @param mallAddress 收货地址
     * @return 结果
     */
    public int insertMallAddress(MallAddress mallAddress);

    /**
     * 修改收货地址
     * 
     * @param mallAddress 收货地址
     * @return 结果
     */
    public int updateMallAddress(MallAddress mallAddress);

    /**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的收货地址主键集合
     * @return 结果
     */
    public int deleteMallAddressByIds(Long[] ids);

    /**
     * 删除收货地址信息
     * 
     * @param id 收货地址主键
     * @return 结果
     */
    public int deleteMallAddressById(Long id);
}
