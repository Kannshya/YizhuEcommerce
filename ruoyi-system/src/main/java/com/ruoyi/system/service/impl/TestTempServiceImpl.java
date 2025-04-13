package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestTempMapper;
import com.ruoyi.system.domain.TestTemp;
import com.ruoyi.system.service.ITestTempService;

/**
 * 测试Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-11
 */
@Service
public class TestTempServiceImpl implements ITestTempService 
{
    @Autowired
    private TestTempMapper testTempMapper;

    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    @Override
    public TestTemp selectTestTempById(Long id)
    {
        return testTempMapper.selectTestTempById(id);
    }

    /**
     * 查询测试列表
     * 
     * @param testTemp 测试
     * @return 测试
     */
    @Override
    public List<TestTemp> selectTestTempList(TestTemp testTemp)
    {
        return testTempMapper.selectTestTempList(testTemp);
    }

    /**
     * 新增测试
     * 
     * @param testTemp 测试
     * @return 结果
     */
    @Override
    public int insertTestTemp(TestTemp testTemp)
    {
        testTemp.setCreateTime(DateUtils.getNowDate());
        return testTempMapper.insertTestTemp(testTemp);
    }

    /**
     * 修改测试
     * 
     * @param testTemp 测试
     * @return 结果
     */
    @Override
    public int updateTestTemp(TestTemp testTemp)
    {
        return testTempMapper.updateTestTemp(testTemp);
    }

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键
     * @return 结果
     */
    @Override
    public int deleteTestTempByIds(Long[] ids)
    {
        return testTempMapper.deleteTestTempByIds(ids);
    }

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    @Override
    public int deleteTestTempById(Long id)
    {
        return testTempMapper.deleteTestTempById(id);
    }
}
