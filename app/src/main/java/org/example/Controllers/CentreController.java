package org.example.Controllers;

import java.util.List;

import org.example.Entities.Centre;
import org.example.Services.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centres")
public class CentreController {
    @Autowired
    private CentreService centreService;

    @GetMapping
    public List<Centre> getAllCentres() {
        return centreService.getAllCentres();
    }

    @GetMapping("/{id}")
    public Centre getCentreById(@PathVariable Long id) {
        return centreService.getCentreById(id);
    }

    @PostMapping
    public Centre createCentre(@RequestBody Centre centre) {
        return centreService.createOrUpdateCentre(centre);
    }

    @PutMapping("/{id}")
    public Centre updateCentre(@PathVariable Long id, @RequestBody Centre centre) {
        centre.setId(id);
        return centreService.createOrUpdateCentre(centre);
    }

    @DeleteMapping("/{id}")
    public void deleteCentre(@PathVariable Long id) {
        centreService.deleteCentre(id);
    }

}

