package com.livethegame.CreateBroadcast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livethegame.CreateBroadcast.common.BroadcastResponseMapper;
import com.livethegame.CreateBroadcast.controller.CreateBroadcastRestController;
import com.livethegame.CreateBroadcast.dto.BroadcastRequest;
import com.livethegame.CreateBroadcast.dto.BroadcastResponse;
import com.livethegame.CreateBroadcast.repository.PlatformRepository;
import com.livethegame.CreateBroadcast.repository.BroadcastRepository;
import com.livethegame.CreateBroadcast.services.BroadcastService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@WebMvcTest(CreateBroadcastRestController.class)
@AutoConfigureMockMvc
class CreateBroadcastApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BroadcastRepository broadcastRepository;

    @MockBean
    private PlatformRepository categoryRepository;

    @MockBean
    private BroadcastService broadcastService;
    @MockBean
    private BroadcastResponseMapper broadcastResponseMapper;

    private static final String PASSWORD = "admin";
    private static final String Broadcast = "admin";

    @Test
    public void testCreateBroadcast_Success() throws Exception {
        BroadcastRequest request = new BroadcastRequest();
        BroadcastResponse response = new BroadcastResponse();
        response.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/broadcasts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCreateBroadcast_Business_TournamentNotFound() throws Exception {

    }
    @Test
    public void testCreateBroadcast_Business_PlatformNotFound() throws Exception {

    }

    @Test
    public void testCreateBroadcast_Business_BroadcastTypeNotFound() throws Exception {

    }

    @Test
    public void testCreateBroadcast_Business_WithoutAuthorization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Broadcasts/create")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testCreateBroadcast_Business_testUnauthorizedAccess() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
        mockMvc.perform(MockMvcRequestBuilders.get("/Broadcasts/create")
                        .header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
