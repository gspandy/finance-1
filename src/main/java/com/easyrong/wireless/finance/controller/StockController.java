package com.easyrong.wireless.finance.controller;

import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.service.NtStockService;
import com.easyrong.wireless.finance.service.StockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private NtStockService ntStockService;

    @ApiOperation(value = "张家港A股19家上市企业", notes = "张家港A股19家上市企业股票信息列表")
    @RequestMapping("/stock")
    public Object getStockInfo(Model model) {
        model.addAttribute("stock", stockService.getStockList());
        return "stock";
    }

    @ApiOperation(value = "张家港新三板挂牌企业", notes = "张家港新三板挂牌企业股票信息列表")
    @RequestMapping("/ntstock")
    public Object getNtStockInfo() {
        return new BaseRspEntity(ntStockService.getNtStockList());
    }
}
