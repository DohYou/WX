package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class AiNewsModel extends Base {

    /**
     * data : {"customer":{"current":1,"orders":[],"pages":0,"records":[],"searchCount":true,"size":10,"total":0},"isshow":0,"keyword":["在玩游戏","在玩游好","关键字","关键字1","关键字2","关键字3","关键字4"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customer : {"current":1,"orders":[],"pages":0,"records":[],"searchCount":true,"size":10,"total":0}
         * isshow : 0
         * keyword : ["在玩游戏","在玩游好","关键字","关键字1","关键字2","关键字3","关键字4"]
         */

        private CustomerBean customer;
        private int isshow;
        private List<String> keyword;

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public int getIsshow() {
            return isshow;
        }

        public void setIsshow(int isshow) {
            this.isshow = isshow;
        }

        public List<String> getKeyword() {
            return keyword;
        }

        public void setKeyword(List<String> keyword) {
            this.keyword = keyword;
        }

        public static class CustomerBean {
            /**
             * current : 1
             * orders : []
             * pages : 0
             * records : []
             * searchCount : true
             * size : 10
             * total : 0
             */

            private int current;
            private int pages;
            private boolean searchCount;
            private int size;
            private int total;
            private List<?> orders;
            private List<?> records;

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public boolean isSearchCount() {
                return searchCount;
            }

            public void setSearchCount(boolean searchCount) {
                this.searchCount = searchCount;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<?> getOrders() {
                return orders;
            }

            public void setOrders(List<?> orders) {
                this.orders = orders;
            }

            public List<?> getRecords() {
                return records;
            }

            public void setRecords(List<?> records) {
                this.records = records;
            }
        }
    }
}
