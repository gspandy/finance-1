package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.base.BaseErrorInterfaceInfo;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.entity.StockEntity;
import com.easyrong.wireless.finance.entity.StockRspEntity;
import com.easyrong.wireless.finance.utils.OkHttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://market.aliyun.com/products/57000002/cmapi010845.html#sku=yuncode484500000
 */
@Service
public class StockService {
    @Autowired
    private OkHttpUtil okHttpUtil;

    private String url = "http://stock.market.alicloudapi.com/batch-real-stockinfo";

    private String appCode = "75391fd9f8fa4b49a2c812c169afd17c";

    private List<String> stockInfo() {
        List<String> stocks = new ArrayList<>();

        stocks.add("sh600794");
        stocks.add("sh601599");

        stocks.add("sz002071");
        stocks.add("sz002075");
        stocks.add("sz002091");
        stocks.add("sz002172");
        stocks.add("sz002221");
        stocks.add("sz002245");
        stocks.add("sz002255");
        stocks.add("sz002274");
        stocks.add("sz002435");
        stocks.add("sz002450");
        stocks.add("sz002519");
        stocks.add("sz002564");
        stocks.add("sz002839");
        stocks.add("sz300228");
        stocks.add("sz300509");
        stocks.add("sz300651");

        return stocks;
    }

    public Object getStockList() {
        Map<String, String> querys = new HashMap<>();
        querys.put("needIndex", "0");
        querys.put("stocks", getStocksKey(stockInfo()));

        try {
            List<StockRspEntity> stockRspEntityList = new ArrayList<>();

            StockEntity stockEntity = new Gson().fromJson(okHttpUtil.run(url, querys, appCode), StockEntity.class);

            StockEntity.Stockbody showapi_res_body = stockEntity.getShowapi_res_body();
            for (StockEntity.StockDetails item : showapi_res_body.getList()) {
                StockRspEntity stockRspEntity = new StockRspEntity();
                stockRspEntity.setAllValue(item.getAll_value());
                stockRspEntity.setClosePrice(item.getClosePrice());
                stockRspEntity.setName(item.getName());
                stockRspEntity.setCode(item.getCode());
                stockRspEntityList.add(stockRspEntity);
            }

            return stockRspEntityList;
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }
    }

    public String getStocksKey(List<String> stockInfo) {
        StringBuilder sb = new StringBuilder();
        if (stockInfo != null && stockInfo.size() > 0) {
            sb.append(stockInfo.get(0));

            for (int i = 1; i < stockInfo.size(); i++) {
                sb.append("," + stockInfo.get(i));
            }
        }

        return sb.toString();
    }
}
