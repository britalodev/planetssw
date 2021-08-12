package br.com.italo.planetssw.planets.feign.service;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.exception.PlanetException;
import br.com.italo.planetssw.mapper.PlanetMapper;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.italo.planetssw.planets.bean.PlanetResult;
import br.com.italo.planetssw.planets.feign.client.PlanetClient;
import br.com.italo.planetssw.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    @Autowired
    PlanetClient planetClient;

    @Autowired
    PlanetMapper planetMapper;

    @Autowired
    PlanetRepository planetRepository;

    public PlanetBean addPlanet(PlanetBean planetBean) {
        if (Objects.nonNull(planetBean) && Objects.nonNull(planetBean.getName())) {


            Planet planet = getPlanetByName(planetBean.getName());

            planetBean.setFilms(planet.getFilms().size());
            planetBean.setName(planet.getName());

            PlanetBean insert = planetRepository.insert(planetBean);

            return insert;
        } else {
            throw new PlanetException("Não foi possível inserir o planeta do banco de dados!");
        }

    }

    private Planet getPlanetByName(String name) {
        PlanetResult planetResult = planetClient.getPlanetName(name);
        Planet planet = planetMapper.planetResultToPlanet(planetResult);;
        return planet;
    }


    public List<PlanetBean> getPlanets() {
        List<Planet> planet = new ArrayList<>();
        PlanetResult planets = planetClient.getPlanets();

        planet.addAll(planets.getPlanets());
        String next = getNextPageNumber(planets);
        if(Objects.nonNull(next)) {
            do {
                PlanetResult nextPagePlanets = planetClient.getNextPagePlanets(next);
                next = getNextPageNumber(nextPagePlanets);
                planet.addAll(nextPagePlanets.getPlanets());

            } while (checkNext(next));
        }
        return planet.stream().map(planetMapper::planetToBean).collect(Collectors.toList());
    }

    public List<PlanetBean> getPLanetsFromDB(){
        return planetRepository.findAll();
    }

    private boolean checkNext(String next) {
        return Objects.nonNull(next);
    }

    private String getNextPageNumber(PlanetResult planets) {
        String next = null;
        if(Objects.nonNull(planets.getNext())) {
            next = String.valueOf(planets.getNext().charAt(planets.getNext().length() - 1));
        }
        return next;
    }

    public String deletePlanet(PlanetBean planetBean) {
        if(planetRepository.existsById(planetBean.getId())) {
            planetRepository.deleteById(planetBean.getId());
            return "removed";
        } else {
            return "ID Not Found";
        }
    }

    public PlanetBean getByName(PlanetBean planetBean) {
        return planetRepository.findByName(planetBean.getName());
    }
}
