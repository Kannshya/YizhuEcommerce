package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MallAddressMapper;
import com.ruoyi.system.domain.MallAddress;
import com.ruoyi.system.service.IMallAddressService;

/**
 * 收货地址Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@Service
public class MallAddressServiceImpl implements IMallAddressService 
{
    @Autowired
    private MallAddressMapper mallAddressMapper;

    /**
     * 查询收货地址
     * 
     * @param id 收货地址主键
     * @return 收货地址
     */
    @Override
    public MallAddress selectMallAddressById(Long id)
    {
        return mallAddressMapper.selectMallAddressById(id);
    }

    /**
     * 查询收货地址列表
     * 
     * @param mallAddress 收货地址
     * @return 收货地址
     */
    @Override
    public List<MallAddress> selectMallAddressList(MallAddress mallAddress)
    {
        mallAddress.setUserId(SecurityUtils.getUserId());
        return mallAddressMapper.selectMallAddressList(mallAddress);
    }

    /**
     * 新增收货地址
     * 
     * @param mallAddress 收货地址
     * @return 结果
     */
    @Override
    public int insertMallAddress(MallAddress mallAddress)
    {
        if (mallAddress.getUserId() == null)
        {
            mallAddress.setUserId(SecurityUtils.getUserId());
        }
        return mallAddressMapper.insertMallAddress(mallAddress);
    }

    /**
     * 修改收货地址
     * 
     * @param mallAddress 收货地址
     * @return 结果
     */
    @Override
    public int updateMallAddress(MallAddress mallAddress)
    {
        return mallAddressMapper.updateMallAddress(mallAddress);
    }

    /**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的收货地址主键
     * @return 结果
     */
    @Override
    public int deleteMallAddressByIds(Long[] ids)
    {
        return mallAddressMapper.deleteMallAddressByIds(ids);
    }

    /**
     * 删除收货地址信息
     * 
     * @param id 收货地址主键
     * @return 结果
     */
    @Override
    public int deleteMallAddressById(Long id)
    {
        return mallAddressMapper.deleteMallAddressById(id);
    }
}
