package br.com.italo.planetssw.rest.controller;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.mapper.PlanetMapper;
import br.com.italo.planetssw.planets.feign.service.PlanetService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
public class PlanetControllerTest {


    private static final String INVALID = "invalid";
    private static final String VALID = "valid";
    private static final String VALID_ONE = "validOne";
    private static final String VALID_MULTIPLE = "validMultiple";

    @Mock
    PlanetService planetService;

    @Mock
    PlanetMapper planetMapper;

    @InjectMocks
    PlanetController planetController;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.italo.planetssw.fixtures");
    }


    @Test
    public void addPlanetTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetService.addPlanet(Mockito.any())).thenReturn(planetBean);

        ResponseEntity<PlanetBean> planetBeanResponseEntity = planetController.addPlanet(planetBean);

        MatcherAssert.assertThat(planetBeanResponseEntity.getBody().getName(), Matchers.is(planetBean.getName()));

    }

    @Test
    public void getPlanetsTest() {

        List<PlanetBean> planetBean = Fixture.from(PlanetBean.class).gimme(3, VALID);

        Mockito.when(planetService.getPlanets()).thenReturn(planetBean);

        ResponseEntity<List<PlanetBean>> planets = planetController.getPlanets();

        MatcherAssert.assertThat(planets.getBody().size(), Matchers.is(3));

    }

    @Test
    public void getPlanetsFromDBTest() {

        List<PlanetBean> planetBean = Fixture.from(PlanetBean.class).gimme(3, VALID);

        Mockito.when(planetService.getPLanetsFromDB()).thenReturn(planetBean);

        ResponseEntity<List<PlanetBean>> planetsFromDB = planetController.getPlanetsFromDB();

        MatcherAssert.assertThat(planetsFromDB.getBody().size(), Matchers.is(3));

    }

    @Test
    public void getByNameTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetService.getByName(Mockito.any())).thenReturn(planetBean);

        ResponseEntity<PlanetBean> planetBeanResponseEntity = planetController.getByName(planetBean);

        MatcherAssert.assertThat(planetBeanResponseEntity.getBody().getName(), Matchers.is(planetBean.getName()));

    }

    @Test
    public void deletePlanetTest() {

        PlanetBean planetBean = Fixture.from(PlanetBean.class).gimme(VALID);

        Mockito.when(planetService.deletePlanet(Mockito.any())).thenReturn("removed");

        ResponseEntity<String> stringResponseEntity = planetController.deletePlanet(planetBean);

        MatcherAssert.assertThat(stringResponseEntity.getBody(), Matchers.is("removed"));

    }
}
