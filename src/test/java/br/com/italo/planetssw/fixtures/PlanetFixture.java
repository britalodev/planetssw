package br.com.italo.planetssw.fixtures;


import br.com.italo.planetssw.bean.PlanetBean;
import br.com.italo.planetssw.planets.bean.Planet;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Arrays;

public class PlanetFixture implements TemplateLoader {

    private static final String INVALID = "invalid";
    private static final String VALID = "valid";

    @Override
    public void load() {


        Fixture.of(Planet.class).addTemplate(VALID, new Rule(){{
            add("name","Tatooine");
            add("films", Arrays.asList("","",""));

        }});

        Fixture.of(Planet.class).addTemplate(INVALID, new Rule(){{
            add("name","Tatooine");
            add("films", Arrays.asList("","",""));

        }});

    }




}
