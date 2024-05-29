package com.livethegame.CreateBroadcast.common;

import com.livethegame.CreateBroadcast.dto.BroadcastResponse;
import com.livethegame.CreateBroadcast.entities.Broadcasts;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BroadcastResponseMapper {
    BroadcastResponse BroadcastToBroadcastResponse(Broadcasts source);
}
