package com.livethegame.CreateBroadcast.Utils;

import com.livethegame.CreateBroadcast.dto.BroadcastRequest;
import com.livethegame.CreateBroadcast.entities.*;

public  class Mapper {
    public static Broadcasts BroadcastRequestToBroadcast(BroadcastRequest source, Tournaments tournament, BroadcastTypes broadcastType, Platforms platform){
        Broadcasts broadcast = new Broadcasts();
        broadcast.setViewers_max(source.getViewers_max());
        broadcast.setTournament(tournament);
        broadcast.setBroadcastType(broadcastType);
        broadcast.setPlatform(platform);
        return broadcast;
    };
}
