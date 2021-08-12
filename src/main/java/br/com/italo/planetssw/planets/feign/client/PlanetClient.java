package br.com.italo.planetssw.planets.feign.client;

import br.com.italo.planetssw.planets.bean.PlanetResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "planet", url = "${url.feign.swapi}")
public interface PlanetClient {

    @GetMapping(value = "/planets/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlanetResult getPlanetName(@RequestParam(value = "search") String planetName);

    @GetMapping(value = "/planets")
    public PlanetResult getPlanets();

    @GetMapping(value = "/planets/")
    public PlanetResult getNextPagePlanets(@RequestParam(value = "page") String page);
}
