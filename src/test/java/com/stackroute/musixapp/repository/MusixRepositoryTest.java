package com.stackroute.musixapp.repository;

import com.stackroute.musixapp.domain.Musix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MusixRepositoryTest {

    @Autowired
    MusixRepository musixRepository;
    Musix musix;

    @Before
    public void setUp()
    {
        musix = new Musix();
        musix.setId(1);
        musix.setName("birds");
        musix.setRating(5);
        musix.setComments("wow!");
    }

    @After
    public void tearDown(){

        musixRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        musixRepository.save(musix);
        Musix fetchUser = musixRepository.findById(musix.getId()).get();
        Assert.assertEquals(1,fetchUser.getId());

    }


}
