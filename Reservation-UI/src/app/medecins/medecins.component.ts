import { Component, OnInit } from '@angular/core';
import { MedecinService } from '../services/medecin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-medecins',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule

  ],
  templateUrl: './medecins.component.html',
  styleUrls: ['./medecins.component.css']
})
export class MedecinsComponent implements OnInit {
  medecins: any[] = [];
  centreId: number | null = null;
  errorMessage: string | null = null;
  isAdminMode: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private medecinService: MedecinService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Vérifier si on est en mode admin
    this.route.data.subscribe(data => {
      this.isAdminMode = data['admin'] === true;
    });

    // Récupérer l'ID du centre à partir des paramètres de la route
    this.route.queryParams.subscribe((params) => {
      this.centreId = +params['centreId'] || null;
      if (this.centreId !== null) {
        this.loadMedecinsByCentre();
      }
    });
  }

  chooseMedecin(medecinId: number) {
    this.router.navigate(['/reservation'], { queryParams: { medecinId: medecinId } });
  }

  // Charger les médecins par centre
  loadMedecinsByCentre(): void {
    if (this.centreId !== null) {
      this.medecinService.getMedecinsByCentreId(this.centreId).subscribe(
        (data) => {
          this.medecins = data;
          this.errorMessage = null;
        },
        (error) => {
          this.errorMessage = error;
          this.medecins = [];
        }
      );
    }
  }

  // Méthodes spécifiques au mode admin
  addMedecin() {
    if (!this.isAdminMode) return;
    // TODO: Implémenter l'ajout de médecin
  }

  editMedecin(id: number) {
    if (!this.isAdminMode) return;
    // TODO: Implémenter la modification de médecin
  }

  deleteMedecin(id: number) {
    if (!this.isAdminMode) return;
    // TODO: Implémenter la suppression de médecin
  }
}
