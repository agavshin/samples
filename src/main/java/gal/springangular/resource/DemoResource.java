package gal.springangular.resource;

import gal.springangular.domain.Demo;
import gal.springangular.domain.Sub;
import gal.springangular.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin("http://localhost:4200")
public class DemoResource {

    private final DemoService demoService;

    @Autowired
    public DemoResource(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public ResponseEntity<List<Demo>> getDemos() {
        return ResponseEntity.ok(demoService.getDemos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demo> getDemo(@PathVariable("id") String id) {
        return ResponseEntity.ok(demoService.getDemoById(id));
    }

    @PostMapping
    public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) {
        return ResponseEntity.ok(demoService.addDemo(demo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        demoService.deleteDemoById(id);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteDemo(@RequestBody Demo demo) {
        demoService.deleteDemo(demo);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/{guid}")
    public ResponseEntity<Demo> addSub(@PathVariable("guid") String demoGuid,
                                       @RequestParam(value = "parent_id", required = false) String parentId,
                                       @RequestBody Sub sub) {
        return ResponseEntity.ok(demoService.addSub(demoGuid, parentId, sub));
    }
}
