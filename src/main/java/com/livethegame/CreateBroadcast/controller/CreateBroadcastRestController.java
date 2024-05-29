package com.livethegame.CreateBroadcast.controller;

import com.livethegame.CreateBroadcast.dto.BroadcastRequest;
import com.livethegame.CreateBroadcast.dto.BroadcastResponse;
import com.livethegame.CreateBroadcast.exception.*;
import com.livethegame.CreateBroadcast.services.BroadcastService;
import com.livethegame.CreateBroadcast.services.MonitoringService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Api Create Broadcasts.java")
@RestController
@RequestMapping("/broadcasts")
public class CreateBroadcastRestController {

    @Autowired
    BroadcastService broadcastService;
    @Autowired
    MonitoringService monitoringService;

    @PostMapping("/create")
    public ResponseEntity<?> createBroadcast(@RequestBody BroadcastRequest input) {
        try {
            BroadcastResponse BroadcastResponse = broadcastService.createBroadcast(input);
            monitoringService.registerSuccessLog(String.valueOf(BroadcastResponse.getId()),"/create "+input.toString()+" "+BroadcastResponse);
            return ResponseEntity.ok(BroadcastResponse);
        } catch (TournamentNotFoundException
                 | BroadcastTypeNotFoundException
                 | ParamsNotFoundException
                 | PlatformNotFoundException e ) {
            monitoringService.registerControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MaximumNumberOfFreeBroadcastsReachedException e) {
            monitoringService.registerControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            monitoringService.registerNotControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    @GetMapping("/test-create")
    public ResponseEntity<?> testCreateBroadcast(){
        return ResponseEntity.ok().build();
    }
}
