package br.com.italo.planetssw.mapper;


import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.italo.planetssw.planets.bean.PlanetResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

    @Mapping(target = "films", expression = "java(planet.getFilms().size())")
    @Mapping(target = "name", source = "name")
    PlanetBean planetToBean(Planet planet);

    @Mapping(target = "name", expression = "java(planetResult.getPlanets().get(0).getName())")
    @Mapping(target = "films", expression = "java(planetResult.getPlanets().get(0).getFilms())")
    Planet planetResultToPlanet(PlanetResult planetResult);


}
