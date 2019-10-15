package com.stackroute.musixapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.musixapp.domain.Musix;
import com.stackroute.musixapp.service.MusixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MusixControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Musix musix;
    @MockBean
    private MusixService musixService;
    @InjectMocks
    private MusixController musixController;

    private List<Musix> list = null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(musixController).build();
        musix = new Musix();
        musix.setId(1);
        musix.setName("birds");
        musix.setRating(5);
        musix.setComments("wow!");
        list = new ArrayList();

        list.add(musix);
    }

    @Test
    public void saveMusic() throws Exception {
        when(musixService.saveNewMusix(any())).thenReturn(musix);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(musix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }




}
