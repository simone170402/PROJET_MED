package org.example.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Entities.Medecin;
import org.example.Exceptions.MedecinNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MedecinService {
    private Map<Long, Medecin> medecinMap = new HashMap<>();
    private Long currentId = 1L;

    public Medecin findOneById(Long id) {
        if (!medecinMap.containsKey(id)) {
            throw new MedecinNotFoundException("Medecin with id " + id + " not found.");
        }
        return medecinMap.get(id);
    }

    public List<Medecin> findAll() {
        return new ArrayList<>(medecinMap.values());
    }

    public Medecin save(Medecin medecin) {
        medecin.setId(currentId);
        medecinMap.put(currentId, medecin);
        currentId++;
        return medecin;
    }

    public Medecin update(Long id, Medecin medecin) {
        if (!medecinMap.containsKey(id)) {
            throw new MedecinNotFoundException("Medecin with id " + id + " not found.");
        }
        medecin.setId(id);
        medecinMap.put(id, medecin);
        return medecin;
    }

    public void delete(Long id) {
        if (!medecinMap.containsKey(id)) {
            throw new MedecinNotFoundException("Medecin with id " + id + " not found.");
        }
        medecinMap.remove(id);
    }
}


