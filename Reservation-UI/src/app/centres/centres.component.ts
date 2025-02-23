import { Component, OnInit } from '@angular/core';
import { BookingService } from '../services/Booking.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CentreService } from '../services/centre.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-centres',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './centres.component.html',
  styleUrl: './centres.component.css'
})

export class CentresComponent implements OnInit {
  centres: any[] = [];
  city: string = '';
  filteredCentres: any[] = []; // Liste filtrée des centres

  constructor(private bookingService: BookingService, private router: Router,private centreService: CentreService) {}

  ngOnInit(): void {
    this.getAllCentres();
    this.bookingService.getCentres().subscribe(data => {
      this.centres = data;
      this.filteredCentres = data;
    });
  }
  
  // Méthode appelée lorsque l'utilisateur choisit un centre
  chooseCentre(centreId: number): void {
    this.router.navigate(['/medecins'], { queryParams: { centreId } });
  }

  getAllCentres(): void {
    this.centreService.getAllCentres().subscribe((data) => {
      this.centres = data;
    });
  }

  searchCentresByCity(): void {
    if (this.city.trim() === '') {
      this.getAllCentres(); // Charge tous les centres
    } else {
      const searchCity = this.capitalizeFirstLetter(this.city.trim());
      this.centreService.getAllCentres().subscribe(
        (data) => {
          // Filtrer les centres en comparant avec la ville formatée
          this.filteredCentres = data.filter((centre: any) =>
            centre.city.startsWith(searchCity)
          );
        },
        (error) => {
          console.error('Erreur lors de la recherche des centres par ville', error);
          // Vous pouvez afficher un message d'erreur à l'utilisateur ici
        }
      );
    }
  }
  
  // Méthode pour mettre en majuscule la première lettre d'une chaîne
  capitalizeFirstLetter(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  }
  
  
  
}