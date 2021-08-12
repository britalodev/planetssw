package br.com.italo.planetssw.planets.feign.service;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.exception.PlanetException;
import br.com.italo.planetssw.mapper.PlanetMapper;
import br.com.italo.planetssw.mapper.PlanetMapperImpl;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.italo.planetssw.planets.bean.PlanetResult;
import br.com.italo.planetssw.planets.feign.client.PlanetClient;
import br.com.italo.planetssw.repository.PlanetRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class PlanetServiceTest {

    private static final String INVALID = "invalid";
    private static final String VALID = "valid";
    private static final String VALID_ONE = "validOne";
    private static final String VALID_MULTIPLE = "validMultiple";
    private static final String VALID_MULTIPLE3 = "validMultiple3";

    @Mock
    PlanetClient planetClient;

    @Mock
    PlanetMapper planetMapper;

    @Mock
    PlanetRepository planetRepository;

    @InjectMocks
    PlanetService planetService;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.italo.planetssw.fixtures");
    }


    @Test
    public void addPlanetOkTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);
        PlanetResult planetResult = Fixture.from(PlanetResult.class).gimme(VALID_ONE);
        Planet planet = Fixture.from(Planet.class).gimme(VALID);

        Mockito.when(planetClient.getPlanetName(Mockito.any())).thenReturn(planetResult);
        Mockito.when(planetMapper.planetResultToPlanet(Mockito.any())).thenReturn(planet);
        Mockito.when(planetRepository.insert(planetBean)).thenReturn(planetBean);


        PlanetBean bean = planetService.addPlanet(planetBean);

        assertThat(bean.getFilms(), is(3));
        assertThat(bean.getName(), is(planetBean.getName()));


    }

    @Test
    public void addPlanetPlasnetBeanNullExceptionTest() {

        Assert.assertThrows(PlanetException.class, () -> planetService.addPlanet(null));

    }

    @Test
    public void addPlanetExceptionNameNullTest() {

        Assert.assertThrows(PlanetException.class, () -> planetService.addPlanet(Fixture.from(PlanetBean.class).gimme(INVALID)));

    }

    @Test
    public void getPlanetsFromDBTest() {

        List<PlanetBean> planetBean = Fixture.from(PlanetBean.class).gimme(3, VALID);

        Mockito.when(planetRepository.findAll()).thenReturn(planetBean);


        List<PlanetBean> pLanetsFromDB = planetService.getPLanetsFromDB();

        assertThat(pLanetsFromDB.size(), is(3));

    }

    @Test
    public void deletePlanetTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetRepository.existsById(Mockito.any())).thenReturn(true);

        String delete = planetService.deletePlanet(planetBean);

        assertThat(delete, is("removed"));

    }

    @Test
    public void deletePlanetIdNotFoundTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetRepository.existsById(Mockito.any())).thenReturn(false);

        String delete = planetService.deletePlanet(planetBean);

        assertThat(delete, is("ID Not Found"));

    }

    @Test
    public void getByNameTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetRepository.findByName(Mockito.any())).thenReturn(planetBean);

        PlanetBean byName = planetService.getByName(planetBean);

        assertThat(byName.getName(), is(planetBean.getName()));

    }


    @Test
    public void getPlanetsTest() {

        PlanetResult planetResult = Fixture.from(PlanetResult.class).gimme(VALID_MULTIPLE);
        PlanetResult planetResultWithOne = Fixture.from(PlanetResult.class).gimme(VALID_ONE);
        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetClient.getPlanets()).thenReturn(planetResult);
        Mockito.when(planetClient.getNextPagePlanets(Mockito.any())).thenReturn(planetResultWithOne);
        Mockito.when(planetMapper.planetToBean(Mockito.any())).thenReturn(planetBean);

        List<PlanetBean> planets = planetService.getPlanets();

        assertThat(planets.size(), is(4));

    }

    @Test
    public void getPlanetsOnePageTest() {

        PlanetResult planetResult = Fixture.from(PlanetResult.class).gimme(VALID_ONE);
        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetClient.getPlanets()).thenReturn(planetResult);
        Mockito.when(planetMapper.planetToBean(Mockito.any())).thenReturn(planetBean);

        List<PlanetBean> planets = planetService.getPlanets();

        assertThat(planets.size(), is(1));

    }

    @Test
    public void getPlanetsMultiplePagesTest() {


        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);
        PlanetResult planetResultWithOne = Fixture.from(PlanetResult.class).gimme(VALID_ONE);
        PlanetResult planetResult = Fixture.from(PlanetResult.class).gimme(VALID_MULTIPLE);
        PlanetResult planetResultWithNext3 = Fixture.from(PlanetResult.class).gimme(VALID_MULTIPLE3);

        Mockito.when(planetClient.getPlanets()).thenReturn(planetResult);
        Mockito.when(planetClient.getNextPagePlanets("2")).thenReturn(planetResultWithNext3);
        Mockito.when(planetClient.getNextPagePlanets("3")).thenReturn(planetResultWithOne);

        Mockito.when(planetMapper.planetToBean(Mockito.any())).thenReturn(planetBean);

        List<PlanetBean> planets = planetService.getPlanets();

        assertThat(planets.size(), is(7));

    }

    private String getNextPageNumber(PlanetResult planets) {
        String next = null;
        if(Objects.nonNull(planets.getNext())) {
            next = String.valueOf(planets.getNext().charAt(planets.getNext().length() - 1));
        }
        return next;
    }

}
