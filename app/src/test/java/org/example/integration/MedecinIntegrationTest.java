package org.example.integration;

import org.example.Entities.Medecin;
import org.example.Services.MedecinService;
import org.example.Repositories.MedecinRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedecinIntegrationTest {
    
    @Autowired
    MedecinService medecinService;
    
    @Autowired
    MedecinRepository medecinRepository;
    
    @Test
    public void testSaveAndRetrieveMedecin() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecin.setSurname("Gregory");
        
        // when
        Medecin savedMedecin = medecinService.save(medecin);
        
        // then
        Medecin retrievedMedecin = medecinService.findOneById(savedMedecin.getId());
        Assertions.assertThat(retrievedMedecin.getName()).isEqualTo("Dr. House");
        Assertions.assertThat(retrievedMedecin.getSurname()).isEqualTo("Gregory");
    }
}