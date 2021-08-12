package br.com.italo.planetssw.planets.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class Planet {

    @JsonProperty("name")
    private String name;

    @JsonProperty("films")
    private List<String> films;

}
