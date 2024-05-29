package com.livethegame.CreateBroadcast.services;

import com.livethegame.CreateBroadcast.Utils.Mapper;
import com.livethegame.CreateBroadcast.common.BroadcastResponseMapper;
import com.livethegame.CreateBroadcast.dto.BroadcastRequest;
import com.livethegame.CreateBroadcast.dto.BroadcastResponse;
import com.livethegame.CreateBroadcast.entities.*;
import com.livethegame.CreateBroadcast.exception.BroadcastTypeNotFoundException;
import com.livethegame.CreateBroadcast.exception.MaximumNumberOfFreeBroadcastsReachedException;
import com.livethegame.CreateBroadcast.exception.ParamsNotFoundException;
import com.livethegame.CreateBroadcast.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BroadcastService {

    @Autowired
    BroadcastRepository broadcastRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    ParamsRepository paramsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TournamentUserRepository tournamentUserRepository;
    @Autowired
    BroadcastTypeRepository broadcastTypeRepository;
    @Autowired
    PlatformRepository platformRepository;
    @Autowired
    BroadcastResponseMapper broadcastResponseMapper;


    public BroadcastResponse createBroadcast(BroadcastRequest broadcastRequest){
        Optional<Tournaments> optionalTournament = tournamentRepository.findById(broadcastRequest.getTournament_id());
        if(optionalTournament.isEmpty()) {
            throw new BroadcastTypeNotFoundException("Torneo no encontrado con ID: " + broadcastRequest.getTournament_id());
        }
        Optional<BroadcastTypes> optionalBroadcastType = broadcastTypeRepository.findById(broadcastRequest.getBroadcast_type_id());
        if(optionalBroadcastType.isEmpty()) {
            throw new BroadcastTypeNotFoundException("Tipo de transmisión no encontrado con ID: " + broadcastRequest.getBroadcast_type_id());
        }
        Optional<Platforms> optionalPlatform = platformRepository.findById(broadcastRequest.getPlatform_id());
        if(optionalPlatform.isEmpty()) {
            throw new BroadcastTypeNotFoundException("Tipo de plataforma no encontrada con ID: " + broadcastRequest.getPlatform_id());
        }

        Optional<Params> optionalParamsBroadcastTypesFreeId = paramsRepository.findByName("broadcast.types.free.id");
        if (optionalParamsBroadcastTypesFreeId.isEmpty()) {
            throw new ParamsNotFoundException("Parametro broadcast.types.free.id no encontrado");
        }

        if(optionalParamsBroadcastTypesFreeId.get().getValueAsLong()
                == broadcastRequest.getBroadcast_type_id()){
            Optional<TournamentUsers> optionalTournamentUser = tournamentUserRepository.findByTournamentIdAndRoleId(broadcastRequest.getTournament_id(), 1L);
            if (optionalTournamentUser.isEmpty()) {
                throw new ParamsNotFoundException("Usuario no encontrado con ID: "+optionalTournamentUser.get().getUser().getId());
            }
            Optional<Params> optionalParamsBroadcastFreeMax = paramsRepository.findByName("broadcast.free.max");
            if (optionalParamsBroadcastFreeMax.isEmpty()) {
                throw new ParamsNotFoundException("Parametro broadcast.free.max no encontrado");
            }
            Users user = optionalTournamentUser.get().getUser();
            if(user.getFree_views_tournaments_created()== optionalParamsBroadcastFreeMax.get().getValueAsInt()){
                throw new MaximumNumberOfFreeBroadcastsReachedException("Numero maximo de transmisiones gratis alcanzado");
            }else{
                user.incrementFree_views_tournaments_created();
                userRepository.save(user);
            }
        }

        Broadcasts broadcastRequestToBroadcast = Mapper.BroadcastRequestToBroadcast(broadcastRequest,optionalTournament.get(),optionalBroadcastType.get(),optionalPlatform.get());
        Broadcasts save = broadcastRepository.save(broadcastRequestToBroadcast);
        BroadcastResponse broadcastToBroadcastResponse = broadcastResponseMapper.BroadcastToBroadcastResponse(save);
        return broadcastToBroadcastResponse;
    }

}
