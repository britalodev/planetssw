package br.com.italo.planetssw.fixtures;


import br.com.italo.planetssw.bean.PlanetBean;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PlanetBeanFixture implements TemplateLoader {

    private static final String INVALID = "invalid";
    private static final String VALID = "valid";

    @Override
    public void load() {


        Fixture.of(PlanetBean.class).addTemplate(VALID, new Rule(){{
            add("id","");
            add("name","Tatooine");
            add("climate","");
            add("ground","");
            add("films",2);

        }});

        Fixture.of(PlanetBean.class).addTemplate(INVALID, new Rule(){{
            add("id","");
            add("name",null);
            add("climate","");
            add("ground","");
            add("films",0);

        }});

    }




}
