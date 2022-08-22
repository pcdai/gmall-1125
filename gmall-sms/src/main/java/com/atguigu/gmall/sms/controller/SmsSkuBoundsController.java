package com.atguigu.gmall.sms.controller;

import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.vo.ItemSaleVo;
import com.atguigu.gmall.sms.entity.SmsSkuBoundsEntity;
import com.atguigu.gmall.sms.entity.vo.SkuSaleVo;
import com.atguigu.gmall.sms.service.SmsSkuBoundsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品spu积分设置
 *
 * @author dpc
 * @email 1789333527@qq.com
 * @date 2020-05-18 11:27:59
 */
@Api(tags = "商品spu积分设置 管理")
@RestController
@RequestMapping("sms/smsskubounds")
public class SmsSkuBoundsController {

    @Autowired
    private SmsSkuBoundsService smsSkuBoundsService;


    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> querySmsSkuBoundsByPage(PageParamVo paramVo) {
        PageResultVo pageResultVo = smsSkuBoundsService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<SmsSkuBoundsEntity> querySmsSkuBoundsById(@PathVariable("id") Long id) {
        SmsSkuBoundsEntity smsSkuBounds = smsSkuBoundsService.getById(id);

        return ResponseVo.ok(smsSkuBounds);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody SmsSkuBoundsEntity smsSkuBounds) {
        smsSkuBoundsService.save(smsSkuBounds);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody SmsSkuBoundsEntity smsSkuBounds) {
        smsSkuBoundsService.updateById(smsSkuBounds);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids) {
        smsSkuBoundsService.removeByIds(ids);

        return ResponseVo.ok();
    }

    @PostMapping("sku/sales")
    public ResponseVo<Object> saveSale(@RequestBody SkuSaleVo skuSaleVo) {
        this.smsSkuBoundsService.saveSkuSales(skuSaleVo);
        return ResponseVo.ok("保存成功");
    }

    @GetMapping("sku/{skuId}")
    public ResponseVo<List<ItemSaleVo>> querySaleVoBySkuId(@PathVariable Long skuId) {
        List<ItemSaleVo> itemSaleVos = this.smsSkuBoundsService.querySaleVoBySkuId(skuId);
        return ResponseVo.ok(itemSaleVos);
    }

}
