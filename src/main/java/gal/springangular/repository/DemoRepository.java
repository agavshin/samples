package gal.springangular.repository;

import gal.springangular.domain.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<Demo, String> {

    Demo findByDemoGuid(String demoGuid);
}
