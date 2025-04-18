package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TestTemp;
import com.ruoyi.system.service.ITestTempService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试Controller
 * 
 * @author yizhu
 * @date 2025-04-11
 */
@RestController
@RequestMapping("/system/testTemp")
public class TestTempController extends BaseController
{
    @Autowired
    private ITestTempService testTempService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestTemp testTemp)
    {
        startPage();
        List<TestTemp> list = testTempService.selectTestTempList(testTemp);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestTemp testTemp)
    {
        List<TestTemp> list = testTempService.selectTestTempList(testTemp);
        ExcelUtil<TestTemp> util = new ExcelUtil<TestTemp>(TestTemp.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testTempService.selectTestTempById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestTemp testTemp)
    {
        return toAjax(testTempService.insertTestTemp(testTemp));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestTemp testTemp)
    {
        return toAjax(testTempService.updateTestTemp(testTemp));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('system:testTemp:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testTempService.deleteTestTempByIds(ids));
    }
}
