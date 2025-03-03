import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCardModule
  ],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  centres: any[] = [];
  medecins: any[] = [];
  reservations: any[] = [];
  patients: any[] = [];
  selectedCentreId: number | null = null; // ID du centre sélectionné
  
  newMedecin = { name: '', email: '', speciality: '', centreId: null };
  updatedMedecin = { id: null, name: '', email: '', speciality: '', centreId: null };

  newReservation = { dateReservation: '', medecinId: null, patientId: null, centreId: null };
  updatedReservation = { id: null, dateReservation: '', medecinId: null, patientId: null, centreId: null };

  constructor(private http: HttpClient, private authService: AuthService, private snackBar: MatSnackBar) {}

  showMessage(message: string) {
    this.snackBar.open(message, 'Fermer', {
      duration: 3000,
      panelClass: ['custom-snack-bar'],
    });
  }

  ngOnInit(): void {
    this.getAllCentres();
    this.getAllPatients();
  }

  onCentreSelected(centreId: number) {
    if (centreId == null) {
      console.error("Centre ID est nul. Impossible de charger les médecins et les réservations.");
      return;
    }
  
    this.selectedCentreId = centreId; // Stocker l'ID du centre sélectionné
    this.getMedecinsByCentre(centreId);
    this.getReservationsByCentre(centreId);
  }

  /** Récupérer tous les centres */
  getAllCentres() {
    this.http.get<any[]>('http://localhost:8080/api/centres', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(data => {
      this.centres = data;
      if (this.centres.length > 0) {
        this.selectedCentreId = this.centres[0].id; // Sélectionner le premier centre par défaut
        if (this.selectedCentreId !== null) {
          this.onCentreSelected(this.selectedCentreId); // Charger médecins et réservations pour le premier centre
        }
      }
    }, error => console.error("Erreur chargement centres", error));
  }

  /** Récupérer les médecins par centre */
  getMedecinsByCentre(centreId: number) {
    if (centreId == null) {
      console.error("Centre ID est null dans getMedecinsByCentre");
      return;
    }
  
    this.authService.getMedecinsByCentre(centreId).subscribe(
      data => {
        this.medecins = data;
        console.log("Médecins récupérés pour le centre :", this.medecins);
      },
      error => console.error("Erreur chargement médecins par centre", error)
    );
  }


  getAllPatients() {
    this.http.get<any[]>('http://localhost:8080/api/patients', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => {
        this.patients = data;
        console.log("Patients récupérés :", this.patients);
      },
      error => console.error("Erreur chargement patients", error)
    );
  }
  /** Ajouter un médecin */
  addMedecin() {
    if (!this.newMedecin.name || !this.newMedecin.email || !this.newMedecin.speciality || !this.newMedecin.centreId) {
      this.showMessage("Tous les champs sont obligatoires !");
      return;
    }
  
    this.authService.addMedecin(this.newMedecin).subscribe(
      (response) => {
        console.log("Médecin ajouté :", response);
        this.getMedecinsByCentre(this.selectedCentreId!); // Rafraîchir la liste
        this.newMedecin = { name: '', email: '', speciality: '', centreId: null };
        this.showMessage("Médecin ajouté avec succès !");
      },
      (error) => {
        console.error("Erreur lors de l'ajout :", error);
        this.showMessage("Échec de l'ajout du médecin !");
      }
    );
  }
  

  /** Pré-remplir les champs pour modifier un médecin */
  selectMedecinForUpdate(medecin: any) {
    this.updatedMedecin = { ...medecin };
  }

  /** Modifier un médecin */
  updateMedecin() {
    if (!this.updatedMedecin.id) {
      this.showMessage("Sélectionnez un médecin !");
      return;
    }
  
    this.authService.updateMedecin(this.updatedMedecin.id, this.updatedMedecin).subscribe(
      (response) => {
        console.log("Médecin mis à jour :", response);
        this.getMedecinsByCentre(this.selectedCentreId!); // Rafraîchir la liste
        this.updatedMedecin = { id: null, name: '', email: '', speciality: '', centreId: null };
        this.showMessage("Médecin mis à jour avec succès !");
      },
      (error) => {
        console.error("Erreur lors de la mise à jour :", error);
        this.showMessage("Erreur mise à jour médecin !");
      }
    );
  }
  

  /** Réinitialiser le formulaire de médecin */
clearSelectedMedecin() {
  this.updatedMedecin = { id: null, name: '', email: '', speciality: '', centreId: null };
}



  /** Supprimer un médecin */
  deleteMedecin(id: number) {
    if (!confirm("Voulez-vous vraiment supprimer ce médecin ?")) return;
  
    this.authService.deleteMedecin(id).subscribe(
      () => {
        console.log("Médecin supprimé !");
        this.getMedecinsByCentre(this.selectedCentreId!); // Rafraîchir la liste
        this.showMessage("Médecin supprimé avec succès !");
      },
      (error) => {
        console.error("Erreur suppression médecin :", error);
        this.showMessage("Erreur suppression médecin !");
      }
    );
  }

  /** Récupérer les réservations par centre */
  getReservationsByCentre(centreId: number) {
    if (centreId == null) {
      console.error("Centre ID est null dans getReservationsByCentre");
      return;
    }
  
    this.http.get<any[]>(`http://localhost:8080/api/reservations/centre/${centreId}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => {
        this.reservations = data;
        console.log("Réservations récupérées pour le centre :", this.reservations);
      },
      error => console.error("Erreur chargement réservations par centre", error)
    );
  }

  /** Ajouter une réservation */
  addReservation() {
    if (!this.newReservation.dateReservation || !this.newReservation.medecinId || !this.newReservation.patientId || !this.selectedCentreId) {
      this.showMessage("Tous les champs sont obligatoires !");
      return;
    }
  
    this.authService.addReservation({ ...this.newReservation, centreId: this.selectedCentreId }).subscribe(
      (response) => {
        console.log("Réservation ajoutée :", response);
        this.getReservationsByCentre(this.selectedCentreId!); // Rafraîchir la liste
        this.newReservation = { dateReservation: '', medecinId: null, patientId: null, centreId: null };
        this.showMessage("Réservation ajoutée avec succès !");
      },
      (error) => {
        console.error("Erreur lors de l'ajout :", error);
        this.showMessage("Échec de l'ajout de la réservation !");
      }
    );
  }

  /** Pré-remplir les champs pour modifier une réservation */
  selectReservationForUpdate(reservation: any) {
    this.updatedReservation = { ...reservation };
  }

  /** Modifier une réservation */
  updateReservation() {
    if (!this.updatedReservation.id) {
      this.showMessage("Sélectionnez une réservation !");
      return;
    }
  
    this.authService.updateReservation(this.updatedReservation.id, this.updatedReservation).subscribe(
      (response) => {
        console.log("Réservation mise à jour :", response);
        this.getReservationsByCentre(this.selectedCentreId!); // Rafraîchir la liste
        this.updatedReservation = { id: null, dateReservation: '', medecinId: null, patientId: null, centreId: null };
        this.showMessage("Réservation mise à jour avec succès !");
      },
      (error) => {
        console.error("Erreur lors de la mise à jour :", error);
        this.showMessage("Erreur mise à jour réservation !");
      }
    );
  }

  /** Réinitialiser le formulaire de réservation */
  clearSelectedReservation() {
    this.updatedReservation = { id: null, dateReservation: '', medecinId: null, patientId: null, centreId: null };
  }

  /** Supprimer une réservation */
  deleteReservation(id: number) {
    if (!confirm("Voulez-vous vraiment supprimer cette réservation ?")) return;

    this.http.delete(`http://localhost:8080/api/reservations/${id}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => {
      this.getReservationsByCentre(this.selectedCentreId!);
      this.showMessage("Réservation supprimée !");
    }, error => this.showMessage("Erreur suppression réservation !"));
  }
}