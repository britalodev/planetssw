package br.com.italo.planetssw.planets.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetResult {

    @JsonProperty("next")
    private String next;

    @JsonProperty("results")
    List<Planet> planets;

}
