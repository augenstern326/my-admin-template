package com.example.xiaohu.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    @ApiModelProperty(value = "状态码")
    private Integer code = 0;

    @ApiModelProperty(value = "返回消息")
    private String message = "no message";

    @ApiModelProperty(value = "返回数据对象")
    private T data;
}
