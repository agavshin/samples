package gal.springangular.service;

import gal.springangular.domain.Demo;
import gal.springangular.domain.Sub;
import gal.springangular.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public List<Demo> getDemos() {
        return demoRepository.findAll();
    }

    public Demo getDemoById(String id) {
        return demoRepository.findById(id).orElse(null);
    }

    public Demo addDemo(Demo demo) {
        return demoRepository.insert(demo);
    }

    public void deleteDemo(Demo demo) {
        demoRepository.delete(demo);
    }

    public void deleteDemoById(String id) {
        demoRepository.deleteById(id);
    }

    public Demo addSub(String demoGuid, String parentId, Sub sub) {
        Optional<Demo> demoOptional = Optional.of(demoRepository.findByDemoGuid(demoGuid));
        demoOptional.ifPresent(demo -> {
            if (parentId == null) {
                demo.getSubs().add(sub);
            } else {
                demo.flattenedSub()
                        .filter(s -> parentId.equals(s.getId()))
                        .findFirst()
                        .ifPresent(ss -> ss.getChildren().add(sub));
            }
            demoRepository.save(demo);
        });
        return demoOptional.orElse(null);
    }

}
