package com.ylr.hyy.mvp.model;

import com.google.gson.annotations.SerializedName;
import com.ylr.hyy.base.Base;

public class MeAuditSuccessModel extends Base {

    /**
     * data : {"code":1,"idcard":"5109201923981927838","name":"张三丰"}
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
         * code : 1
         * idcard : 5109201923981927838
         * name : 张三丰
         */

        @SerializedName("code")
        private int codeX;
        private String idcard;
        private String name;

        public int getCodeX() {
            return codeX;
        }

        public void setCodeX(int codeX) {
            this.codeX = codeX;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
