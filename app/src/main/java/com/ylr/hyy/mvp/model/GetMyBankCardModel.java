package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class GetMyBankCardModel extends Base {


    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * idCard : 5139011986****4834
         * accountNo : 62166031000****6067
         * name : XXXX
         * mobile : 175****3317
         * bank : 中国银行
         * cardName : 联名卡
         * cardType : 借记卡
         */

        private int id;
        private String idCard;
        private String accountNo;
        private String name;
        private String mobile;
        private String bank;
        private String cardName;
        private String cardType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }
    }
}
