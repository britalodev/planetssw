package br.com.italo.planetssw.mapper;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.italo.planetssw.planets.bean.PlanetResult;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


public class PlanetMapperTest {


    private static final String INVALID = "invalid";
    private static final String VALID = "valid";
    private static final String VALID_ONE = "validOne";
    private static final String VALID_MULTIPLE = "validMultiple";
    private static final String VALID_MULTIPLE3 = "validMultiple3";

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.italo.planetssw.fixtures");
    }

    @Test
    public void planetToBeanOkTest(){

        Planet planet = Fixture.from(Planet.class).gimme(VALID);

        PlanetMapper planetMapper = new PlanetMapperImpl();

        PlanetBean planetBean = planetMapper.planetToBean(planet);

        assertThat(planetBean.getName(), is(planet.getName()));
        assertThat(planetBean.getFilms(), is(planet.getFilms().size()));

    }

    @Test
    public void planetToBeanNullTest(){

        PlanetMapper planetMapper = new PlanetMapperImpl();

        PlanetBean planetBean = planetMapper.planetToBean(null);

        assertThat(planetBean, nullValue());

    }

    @Test
    public void planetResultToPlanetOkTest(){

        PlanetResult planetResult = Fixture.from(PlanetResult.class).gimme(VALID_MULTIPLE);

        PlanetMapper planetMapper = new PlanetMapperImpl();

        Planet planet = planetMapper.planetResultToPlanet(planetResult);

        assertThat(planet.getName(), is(planetResult.getPlanets().get(0).getName()));
        assertThat(planet.getFilms().size(), is(planetResult.getPlanets().size()));

    }

    @Test
    public void planetResultToPlanetNullTest(){

        PlanetMapper planetMapper = new PlanetMapperImpl();

        Planet planet = planetMapper.planetResultToPlanet(null);

        assertThat(planet, nullValue());

    }

}
