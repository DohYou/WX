package com.ylr.hyy.mvp.model;


import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

public class AddressModel implements IPickerViewData {
    private String name;
    private List<ChildrenDTOBeanX> childrenDTO;


    public String getName() {
        return name;
    }

    public List<ChildrenDTOBeanX> getChildrenDTO() {
        return childrenDTO;
    }

    public void setChildrenDTO(List<ChildrenDTOBeanX> childrenDTO) {
        this.childrenDTO = childrenDTO;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class ChildrenDTOBeanX {
        private String name;


        public String getName() {
            return name;
        }
    }
}
