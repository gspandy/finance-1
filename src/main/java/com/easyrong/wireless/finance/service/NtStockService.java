package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.base.BaseErrorInterfaceInfo;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.entity.NtStockEntity;
import com.easyrong.wireless.finance.entity.NtStockRspEntity;
import com.easyrong.wireless.finance.utils.OkHttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 参考  https://www.juhe.cn/docs/api/id/185
 */
@Service
public class NtStockService {
    @Autowired
    private OkHttpUtil okHttpUtil;

    private String url = "http://touchstone.api.juhe.cn/ajax/data/realtime";
    private String appKey = "d84c6d7ca4264f62bb417cbfd19a78ca";

    private List<String> stockInfo() {
        List<String> stocks = new ArrayList<>();

        stocks.add("831096");
        stocks.add("831473");
        stocks.add("831521");
        stocks.add("831826");
        stocks.add("831794");
        stocks.add("832369");
        stocks.add("834830");
        stocks.add("834958");
        stocks.add("836440");
        stocks.add("836923");

        stocks.add("831561");
        stocks.add("831555");
        stocks.add("831897");
        stocks.add("833120");
        stocks.add("833544");
        stocks.add("833950");
        stocks.add("834575");
        stocks.add("836373");
        stocks.add("836710");
        stocks.add("837312");

        stocks.add("837083");
        stocks.add("837120");
        stocks.add("838280");
        stocks.add("832802");
        stocks.add("833879");
        stocks.add("834305");
        stocks.add("834286");
        stocks.add("837289");
        stocks.add("830957");
        stocks.add("831424");

        stocks.add("831486");
        stocks.add("832481");
        stocks.add("838177");
        stocks.add("830895");
        stocks.add("831846");
        stocks.add("833634");
        stocks.add("838367");
        stocks.add("430404");
        stocks.add("831279");
        stocks.add("835829");

        stocks.add("837328");
        stocks.add("837660");
        stocks.add("831954");
        stocks.add("833705");
        stocks.add("831624");
        stocks.add("834423");
        stocks.add("834595");
        stocks.add("839588");
        stocks.add("839926");
        stocks.add("839604");

        stocks.add("839652");
        stocks.add("839879");
        stocks.add("839982");
        stocks.add("870098");
        stocks.add("870138");
        stocks.add("870391");
        stocks.add("870365");
        stocks.add("870521");
        stocks.add("870630");
        stocks.add("870607");

        stocks.add("870766");
        stocks.add("871121");
        stocks.add("871782");

        return stocks;
    }

    public Object getNtStockList() {
        List <Object> rspEntityList = new ArrayList<>();
        List <String> list = stockInfo();
        try {
            for (String item : list) {
                NtStockRspEntity ntStockRspEntity = (NtStockRspEntity)getNtStockentity(item);
                if (!item.equals((ntStockRspEntity.getCode()))) {
                    return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
                } else {
                    rspEntityList.add(ntStockRspEntity);
                }
                sleep(100);
            }
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }

        return rspEntityList;
    }

    public Object getNtStockentity(String stock) {
        Map<String, String> param = new HashMap<>();
        param.put("pageIndex", "1");
        param.put("id", stock);
        param.put("key", appKey);

        try {
            NtStockEntity ntStockEntity = new Gson().fromJson(okHttpUtil.run(url, param), NtStockEntity.class);

            NtStockRspEntity ntStockRspEntity = new NtStockRspEntity();

            if (0 == ntStockEntity.getError_code()) {
                NtStockEntity.NtStockBody result = ntStockEntity.getResult();
                NtStockEntity.StockIndex stockIndex = result.getResult().get(0);
                ntStockRspEntity.setAllValue(stockIndex.getRegistCapi());
                ntStockRspEntity.setClosePrice(stockIndex.getZRSP());
                ntStockRspEntity.setCode(stockIndex.get_id());
                ntStockRspEntity.setName(stockIndex.getShortName());
            }

            return ntStockRspEntity;
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }
    }
}
