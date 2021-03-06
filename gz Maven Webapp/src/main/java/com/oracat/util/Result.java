package com.oracat.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Data
public class Result {
    private Integer code=0;
    private String msg;


    public static  final Result ADD_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.ADD_SUCCESS);
    public static  final Result ADD_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.ADD_ERROR);

    public static  final Result UPDATE_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.UPDATE_SUCCESS);
    public static  final Result UPDATE_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.UPDATE_ERROR);

    public static  final Result DELETE_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.DELETE_SUCCESS);
    public static  final Result DELETE_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.DELETE_ERROR);

    public static  final Result RESET_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.RESET_SUCCESS);
    public static  final Result RESET_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.RESET_ERROR);

    public static  final Result DISPATCH_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.DISPATCH_SUCCESS);
    public static  final Result DISPATCH_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.DISPATCH_ERROR);


    public static final Result STATUS_TRUE=new Result(SysConstants.CODE_SUCCESS);
    public static final Result STATUS_FALSE=new Result(SysConstants.CODE_ERROR);



    public static  final Result PAUSE_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.PAUSE_SUCCESS);
    public static  final Result PAUSE_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.PAUSE_ERROR);


    public static  final Result RESUME_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.RESUME_SUCCESS);
    public static  final Result RESUME_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.RESUME_ERROR);


    public static  final Result RUN_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.RUN_SUCCESS);
    public static  final Result RUN_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.RUN_ERROR);

    public static  final Result STOP_SUCCESS=new Result(SysConstants.CODE_SUCCESS,SysConstants.STOP_SUCCESS);
    public static  final Result STOP_ERROR=new Result(SysConstants.CODE_ERROR,SysConstants.STOP_ERROR);


    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code) {
        this.code = code;
    }
}
