package br.com.italo.planetssw.repository;

import br.com.italo.planetssw.bean.PlanetBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<PlanetBean, String> {

    PlanetBean findByName(String name);
}
