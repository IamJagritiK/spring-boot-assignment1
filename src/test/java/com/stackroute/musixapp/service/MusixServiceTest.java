package com.stackroute.musixapp.service;

import com.stackroute.musixapp.domain.Musix;
import com.stackroute.musixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.musixapp.repository.MusixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MusixServiceTest {

    Musix  musix;

    //Create a mock for UserRepository
    @Mock
    MusixRepository musixRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MusixServiceImpl musixService;
    List<Musix> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        musix = new Musix();
        musix.setId(1);
        musix.setName("birds");
        musix.setRating(5);
        musix.setComments("wow!");
        list = new ArrayList<>();
        list.add(musix);


    }

    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {

        when(musixRepository.save((Musix) any())).thenReturn(musix);
        Musix savedTrack = musixService.saveNewMusix(musix);
        Assert.assertEquals(musix,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(musixRepository,times(1)).save(musix);

    }

}
