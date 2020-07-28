package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class MemberPriceModel extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * day : 30
         * fictitiousprice : 4.0
         * id : 1
         * realprice : 3.0
         * title : 月
         */

        private int day;
        private double fictitiousprice;
        private int id;
        private double realprice;
        private String title;
        private int discount;

        public int getDiscount() {
            return discount;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public double getFictitiousprice() {
            return fictitiousprice;
        }

        public void setFictitiousprice(double fictitiousprice) {
            this.fictitiousprice = fictitiousprice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRealprice() {
            return realprice;
        }

        public void setRealprice(double realprice) {
            this.realprice = realprice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
