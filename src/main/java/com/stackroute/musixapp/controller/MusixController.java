package com.stackroute.musixapp.controller;

import com.stackroute.musixapp.domain.Musix;
import com.stackroute.musixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.musixapp.exceptions.TrackNotFoundException;
import com.stackroute.musixapp.service.MusixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MusixController {


    private MusixService musixService;


    @Autowired
    public MusixController(MusixService musixService) {

        this.musixService = musixService;
    }

    @PostMapping("muzix")
    public ResponseEntity<?> saveNewMusix(@RequestBody Musix musix)  {
        ResponseEntity responseEntity;
        try{
            musixService.saveNewMusix(musix);
            responseEntity= new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("muzix")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<List<Musix>>(musixService.getMusix(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/muzix/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {

        ResponseEntity responseEntity;
        try {
            Musix musix = musixService.getById(id);
            responseEntity = new ResponseEntity<Musix>(musix, HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrack(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(
                    musixService.deleteById(id),
                    HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @PutMapping("/update")   public ResponseEntity UpdateComments(@RequestBody Musix user)
    {       ResponseEntity responseEntity;
    responseEntity= new ResponseEntity<>(musixService.UpdateComments(user.getId(),user.getComments()), HttpStatus.OK);        return responseEntity;
    }




}
