package br.com.italo.planetssw.rest.controller;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.mapper.PlanetMapper;
import br.com.italo.planetssw.planets.feign.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "planet", produces = "application/json")
@Configuration
@CrossOrigin
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetBean> addPlanet(@RequestBody PlanetBean planetBean){
        PlanetBean planet = planetService.addPlanet(planetBean);
        return new ResponseEntity<PlanetBean>(planet, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanetBean>> getPlanets(){
        return new ResponseEntity<List<PlanetBean>>(planetService.getPlanets(), HttpStatus.OK);
    }


    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanetBean>> getPlanetsFromDB(){
        return new ResponseEntity<List<PlanetBean>>(planetService.getPLanetsFromDB(), HttpStatus.OK);
    }


    @GetMapping(value = "name",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetBean> getByName(@RequestBody PlanetBean planetBean){
        return new ResponseEntity<PlanetBean>(planetService.getByName(planetBean), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePlanet(@RequestBody PlanetBean planetBean){
        return new ResponseEntity<String>(planetService.deletePlanet(planetBean), HttpStatus.NO_CONTENT);
    }

}
