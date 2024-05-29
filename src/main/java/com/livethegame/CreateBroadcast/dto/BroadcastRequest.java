package com.livethegame.CreateBroadcast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel()
public class BroadcastRequest {

    @ApiModelProperty(name = "numero maximo de asistentes", required = false,example = "", value = "")
    private double viewers_max;
    @ApiModelProperty(name = "id del tipo de broadcast", required = true, example = "", value = "")
    private long broadcast_type_id;
    @ApiModelProperty(name = "id del torneo", required = true, example = "", value = "")
    private long tournament_id;
    @ApiModelProperty(name = "id de la plataforma", required = true, example = "", value = "")
    private long platform_id;

    public BroadcastRequest(){
    }

    public double getViewers_max() {
        return viewers_max;
    }

    public void setViewers_max(double viewers_max) {
        this.viewers_max = viewers_max;
    }

    public long getBroadcast_type_id() {
        return broadcast_type_id;
    }

    public void setBroadcast_type_id(long broadcast_type_id) {
        this.broadcast_type_id = broadcast_type_id;
    }

    public long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(long tournament_id) {
        this.tournament_id = tournament_id;
    }

    public long getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(long platform_id) {
        this.platform_id = platform_id;
    }
}
