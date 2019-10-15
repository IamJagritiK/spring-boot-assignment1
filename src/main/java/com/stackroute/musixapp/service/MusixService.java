package com.stackroute.musixapp.service;

import com.stackroute.musixapp.domain.Musix;
import com.stackroute.musixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.musixapp.exceptions.TrackNotFoundException;


import java.util.List;

public interface MusixService {

    public Musix saveNewMusix(Musix musix) throws TrackAlreadyExistsException;

    public List<Musix> getMusix();

    public Musix getById(int id) throws TrackNotFoundException;

    public String deleteById(int id) throws TrackNotFoundException;

    public boolean updateById(Musix musix, int id) throws TrackNotFoundException;

    public boolean UpdateComments(int trackId, String trackComments);
    public boolean deleteTrack(int trackId);



}
