package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.base.BaseErrorInterfaceInfo;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import com.easyrong.wireless.finance.entity.NeeqEntity;
import com.easyrong.wireless.finance.entity.NeeqRspEntity;
import com.easyrong.wireless.finance.entity.StockEntity;
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
public class NeeqService {
    @Autowired
    private OkHttpUtil okHttpUtil;

    @Autowired
    private StockEntity stockEntity;

    private String url = "http://touchstone.api.juhe.cn/ajax/data/realtime";
    private String appKey = "d84c6d7ca4264f62bb417cbfd19a78ca";

    public Object getNeeqList() {
        List <Object> rspEntityList = new ArrayList<>();
        List <String> list = stockEntity.getNeeq();
        try {
            for (String item : list) {
                NeeqRspEntity neeqRspEntity = (NeeqRspEntity) getNeeqEntity(item);
                if (!item.equals((neeqRspEntity.getCode()))) {
                    return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
                } else {
                    rspEntityList.add(neeqRspEntity);
                }
                sleep(100);
            }
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }

        return rspEntityList;
    }

    public Object getNeeqEntity(String stock) {
        Map<String, String> param = new HashMap<>();
        param.put("pageIndex", "1");
        param.put("id", stock);
        param.put("key", appKey);

        try {
            NeeqEntity neeqEntity = new Gson().fromJson(okHttpUtil.run(url, param), NeeqEntity.class);

            NeeqRspEntity neeqRspEntity = new NeeqRspEntity();

            if (0 == neeqEntity.getError_code()) {
                NeeqEntity.NeeqBody result = neeqEntity.getResult();
                NeeqEntity.NeeqIndex neeqIndex = result.getResult().get(0);
                neeqRspEntity.setAllValue(neeqIndex.getRegistCapi());
                neeqRspEntity.setClosePrice(neeqIndex.getZRSP());
                neeqRspEntity.setCode(neeqIndex.get_id());
                neeqRspEntity.setName(neeqIndex.getShortName());
            }

            return neeqRspEntity;
        } catch (Exception e) {
            return new BaseRspEntity(BaseErrorInterfaceInfo.SERVER_ERROR);
        }
    }
}
