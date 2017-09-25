package com.easyrong.wireless.finance.entity;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class NtStockEntity {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public NtStockBody getResult() {
        return result;
    }

    public void setResult(NtStockBody result) {
        this.result = result;
    }

    private int error_code;
    private String reason;
    private NtStockBody result = new NtStockBody();

    public class NtStockBody {
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

        public List<StockIndex> getResult() {
            return result;
        }

        public void setResult(List<StockIndex> result) {
            this.result = result;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        private List<StockIndex> result = new ArrayList<>();
        private int currentPage;
        private int totalPages;
        private int pageSize;
        private int totalRows;
        private int start;
    }

    public class StockIndex {
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public double getZd() {
            return zd;
        }

        public void setZd(double zd) {
            this.zd = zd;
        }

        public double getZdf() {
            return zdf;
        }

        public void setZdf(double zdf) {
            this.zdf = zdf;
        }

        public String getZRSP() {
            return ZRSP;
        }

        public void setZRSP(String ZRSP) {
            this.ZRSP = ZRSP;
        }

        public double getZJCJ() {
            return ZJCJ;
        }

        public void setZJCJ(double ZJCJ) {
            this.ZJCJ = ZJCJ;
        }

        public int getCJJE() {
            return CJJE;
        }

        public void setCJJE(int CJJE) {
            this.CJJE = CJJE;
        }

        public float getCJSL() {
            return CJSL;
        }

        public void setCJSL(float CJSL) {
            this.CJSL = CJSL;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getRegistCapi() {
            return registCapi;
        }

        public void setRegistCapi(double registCapi) {
            this.registCapi = registCapi;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getOperName() {
            return operName;
        }

        public void setOperName(String operName) {
            this.operName = operName;
        }

        public String getUpDate() {
            return upDate;
        }

        public void setUpDate(String upDate) {
            this.upDate = upDate;
        }

        public long getTotalStock() {
            return totalStock;
        }

        public void setTotalStock(long totalStock) {
            this.totalStock = totalStock;
        }

        public String getCategoryStr() {
            return categoryStr;
        }

        public void setCategoryStr(String categoryStr) {
            this.categoryStr = categoryStr;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public double getZGCJ() {
            return ZGCJ;
        }

        public void setZGCJ(double ZGCJ) {
            this.ZGCJ = ZGCJ;
        }

        public double getZDCJ() {
            return ZDCJ;
        }

        public void setZDCJ(double ZDCJ) {
            this.ZDCJ = ZDCJ;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        private String _id;
        private String shortName;
        private double zd;
        private double zdf;
        private String ZRSP;
        private double ZJCJ;
        private int CJJE;
        private float CJSL;
        private int type;
        private String province;
        private String address;
        private double registCapi;
        private String company;
        private String operName;
        private String upDate;
        private long totalStock;
        private String categoryStr;
        private String updateDate;
        private String updateTime;
        private double ZGCJ;
        private double ZDCJ;
        private String companyName;
    }
}
