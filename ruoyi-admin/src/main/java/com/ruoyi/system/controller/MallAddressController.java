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
import com.ruoyi.system.domain.MallAddress;
import com.ruoyi.system.service.IMallAddressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货地址Controller
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@RestController
@RequestMapping("/system/MallAddress")
public class MallAddressController extends BaseController
{
    @Autowired
    private IMallAddressService mallAddressService;

    /**
     * 查询收货地址列表
     */
//    @PreAuthorize("@ss.hasPermi('system:MallAddress:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallAddress mallAddress)
    {
        startPage();
        List<MallAddress> list = mallAddressService.selectMallAddressList(mallAddress);
        return getDataTable(list);
    }

    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:MallAddress:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallAddress mallAddress)
    {
        List<MallAddress> list = mallAddressService.selectMallAddressList(mallAddress);
        ExcelUtil<MallAddress> util = new ExcelUtil<MallAddress>(MallAddress.class);
        util.exportExcel(response, list, "收货地址数据");
    }

    /**
     * 获取收货地址详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:MallAddress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mallAddressService.selectMallAddressById(id));
    }

    /**
     * 新增收货地址
     */
//    @PreAuthorize("@ss.hasPermi('system:MallAddress:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallAddress mallAddress)
    {
        return toAjax(mallAddressService.insertMallAddress(mallAddress));
    }

    /**
     * 修改收货地址
     */
//    @PreAuthorize("@ss.hasPermi('system:MallAddress:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallAddress mallAddress)
    {
        return toAjax(mallAddressService.updateMallAddress(mallAddress));
    }

    /**
     * 删除收货地址
     */
//    @PreAuthorize("@ss.hasPermi('system:MallAddress:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallAddressService.deleteMallAddressByIds(ids));
    }
}
