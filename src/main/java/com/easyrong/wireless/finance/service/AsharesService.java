package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.base.BaseErrorInterfaceInfo;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.entity.AsharesEntity;
import com.easyrong.wireless.finance.entity.AsharesRspEntity;
import com.easyrong.wireless.finance.entity.StockEntity;
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
public class AsharesService {
    @Autowired
    private OkHttpUtil okHttpUtil;

    @Autowired
    private StockEntity stockEntity;

    private String url = "http://stock.market.alicloudapi.com/batch-real-stockinfo";

    private String appCode = "75391fd9f8fa4b49a2c812c169afd17c";

    public Object getAsharesList() {
        Map<String, String> querys = new HashMap<>();
        querys.put("needIndex", "0");
        querys.put("stocks", getAsharessKey(stockEntity.getAshares()));

        try {
            List<AsharesRspEntity> asharesRspEntityList = new ArrayList<>();

            AsharesEntity asharesEntity = new Gson().fromJson(okHttpUtil.run(url, querys, appCode), AsharesEntity.class);

            AsharesEntity.Asharesbody showapi_res_body = asharesEntity.getShowapi_res_body();
            for (AsharesEntity.AsharesDetails item : showapi_res_body.getList()) {
                AsharesRspEntity asharesRspEntity = new AsharesRspEntity();
                asharesRspEntity.setAllValue(item.getAll_value());
                asharesRspEntity.setClosePrice(item.getClosePrice());
                asharesRspEntity.setName(item.getName());
                asharesRspEntity.setCode(item.getCode());
                asharesRspEntityList.add(asharesRspEntity);
            }

            return asharesRspEntityList;
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }
    }

    public String getAsharessKey(List<String> stockInfo) {
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
