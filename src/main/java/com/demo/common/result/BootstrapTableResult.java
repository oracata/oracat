package com.demo.common.result;

import java.util.List;

/**
 * @description bootstrapTable����Ľ����
 * @author admin
 * @date 2017-11-25 18:59
 */

public class BootstrapTableResult {
    /**
     * �ܼ�¼��
     */
    private Integer total;
    /**
     * �������list����
     */
    private List rows;

    public BootstrapTableResult() {
    }

    public BootstrapTableResult(Integer total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "BootstrapTableResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
