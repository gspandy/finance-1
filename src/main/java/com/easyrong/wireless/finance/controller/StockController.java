package com.easyrong.wireless.finance.controller;

import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.service.NeeqService;
import com.easyrong.wireless.finance.service.AsharesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StockController {
    @Autowired
    private AsharesService asharesService;

    @Autowired
    private NeeqService neeqService;

    @ApiOperation(value = "张家港A股19家上市企业", notes = "张家港A股19家上市企业股票信息列表")
    @RequestMapping(value = "/ashares", method = RequestMethod.GET)
    public Object getAshares(Model model) {
        model.addAttribute("ashares", asharesService.getAsharesList());
        return "ashares";
    }

    @ApiOperation(value = "张家港新三板挂牌企业", notes = "张家港新三板挂牌企业股票信息列表")
    @RequestMapping(value = "/neeq", method = RequestMethod.GET)
    public Object getNeeq(Model model) {
        model.addAttribute("neeq", neeqService.getNeeqList());
        return "neeq";
    }
}
