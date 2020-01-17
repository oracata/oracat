package com.oracat.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class DataGridView {

    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;


    public DataGridView() {
    }


    public DataGridView(Object data) {
        this.data = data;
    }


    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
