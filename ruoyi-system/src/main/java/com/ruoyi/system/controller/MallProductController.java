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
import com.ruoyi.system.domain.MallProduct;
import com.ruoyi.system.service.IMallProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品管理Controller
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@RestController
@RequestMapping("/system/MallProduct")
public class MallProductController extends BaseController
{
    @Autowired
    private IMallProductService mallProductService;

    /**
     * 查询商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallProduct mallProduct)
    {
        startPage();
        List<MallProduct> list = mallProductService.selectMallProductList(mallProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:export')")
    @Log(title = "商品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallProduct mallProduct)
    {
        List<MallProduct> list = mallProductService.selectMallProductList(mallProduct);
        ExcelUtil<MallProduct> util = new ExcelUtil<MallProduct>(MallProduct.class);
        util.exportExcel(response, list, "商品管理数据");
    }

    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mallProductService.selectMallProductById(id));
    }

    /**
     * 新增商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:add')")
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallProduct mallProduct)
    {
        return toAjax(mallProductService.insertMallProduct(mallProduct));
    }

    /**
     * 修改商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:edit')")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallProduct mallProduct)
    {
        return toAjax(mallProductService.updateMallProduct(mallProduct));
    }

    /**
     * 删除商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:MallProduct:remove')")
    @Log(title = "商品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallProductService.deleteMallProductByIds(ids));
    }
}
