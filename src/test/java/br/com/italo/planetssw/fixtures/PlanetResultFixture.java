package br.com.italo.planetssw.fixtures;

import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.italo.planetssw.planets.bean.PlanetResult;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PlanetResultFixture implements TemplateLoader {

    private static final String INVALID = "invalid";
    private static final String VALID_ONE = "validOne";
    private static final String VALID_MULTIPLE = "validMultiple";
    private static final String VALID_MULTIPLE3 = "validMultiple3";
    private static final String VALID = "valid";

    private static final String URL_NEXT_2 = "https://swapi.dev/api/planets/?page=2";
    private static final String URL_NEXT_3 = "https://swapi.dev/api/planets/?page=3";


    @Override
    public void load() {

        Fixture.of(PlanetResult.class).addTemplate(VALID_ONE, new Rule(){{
            add("next", null);
            add("planets", has(1).of(Planet.class, VALID));
        }});

        Fixture.of(PlanetResult.class).addTemplate(VALID_MULTIPLE, new Rule(){{
            add("next",URL_NEXT_2);
            add("planets", has(3).of(Planet.class, VALID));
        }});

        Fixture.of(PlanetResult.class).addTemplate(VALID_MULTIPLE3, new Rule(){{
            add("next",URL_NEXT_3);
            add("planets", has(3).of(Planet.class, VALID));
        }});

    }
}
