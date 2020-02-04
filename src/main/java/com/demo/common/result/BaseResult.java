package com.demo.common.result;

/**
 * ͳһ���ؽ����
 * @author Administrator
 * @date 2017-11-25 ���� 20:07
 */
public class BaseResult {
    /**
     * ״̬�룺1�ɹ�������Ϊʧ��
     */
    private int code;

    /**
     * �ɹ�Ϊsuccess������Ϊʧ��ԭ��
     */
    private String message;

    /**
     * ���ݽ����
     */
    public Object data;

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
