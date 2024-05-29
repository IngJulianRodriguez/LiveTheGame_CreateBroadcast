package com.livethegame.CreateBroadcast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class BroadcastResponse {

    @ApiModelProperty(name = "Id", required = true,example = "", value = "")
    private Long id;

    public BroadcastResponse(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
