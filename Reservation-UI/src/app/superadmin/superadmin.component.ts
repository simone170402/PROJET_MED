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
import { MatFabButton } from '@angular/material/button';
import {ChangeDetectionStrategy, signal} from '@angular/core';


@Component({
  selector: 'app-superadmin',
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
    MatCardModule,
    MatFabButton
  ],
  templateUrl: './superadmin.component.html',
  styleUrls: ['./superadmin.component.css']
})
export class SuperAdminComponent implements OnInit {
  centres: any[] = [];
  administrateurs: any[] = [];
  newCentre = { name: '', city: '', address: '', phoneNumber: '' };
  updatedCentre = { id: null, name: '', city: '', address: '', phoneNumber: '', medecins: [], reservations: [] };
  updatedAdmin = { id: null, name: '', email: '', centreId: null };
  newAdmin = { name: '', email: '', password: '', centreId: null };
  errorMessage: string = '';
  
  constructor(private http: HttpClient, private authService: AuthService, private snackBar: MatSnackBar) {}
  showMessage(message: string) {
    this.snackBar.open(message, 'Fermer', { duration: 3000 });
  }

  ngOnInit(): void {
    this.getAllCentres();
    this.getAllAdministrateurs();
  }

  //  Récupérer tous les centres depuis le backend
  getAllCentres() {
    this.http.get<any[]>('http://localhost:8080/api/centres', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => {
        console.log("Centres récupérés :", data);
        this.centres = data;
      },
      error => {
        console.error("Erreur chargement centres :", error);
        this.errorMessage = 'Erreur chargement centres';
      }
    );
  }

  //  Ajouter un centre
  addCentre() {
    if (!this.newCentre.name || !this.newCentre.city || !this.newCentre.address || !this.newCentre.phoneNumber) {
      console.error("Erreur : Remplissez tous les champs du formulaire !");
      return;
    }
  
    this.authService.addCentre(this.newCentre).subscribe(
      (response) => {
        console.log(" Centre ajouté :", response);
        this.newCentre = { name: '', city: '', address: '', phoneNumber: '' };
        this.getAllCentres();  // Rafraîchir la liste des centres
        this.showMessage("Centre ajouté avec succès !");
      },
      (error) => {
        console.error("Erreur lors de l'ajout :", error);
      this.showMessage("Échec de l'ajout du centre !");
      }
    );
  }
  

  //  Pré-remplir les champs pour modification
  selectCentreForUpdate(centre: any) {
    this.updatedCentre = { 
      ...centre, 
      medecins: centre.medecins ? centre.medecins.map((m: any) => ({ id: m.id })) : [], 
      reservations: centre.reservations ? centre.reservations.map((r: any) => ({ id: r.id })) : []
    };
  } 
  
  //  Modifier un centre
  updateCentre() {
    if (!this.updatedCentre.id) {
      this.showMessage("ID du centre invalide !");
      return;
    }
  
    const centreData = {
      id: this.updatedCentre.id,
      name: this.updatedCentre.name,
      city: this.updatedCentre.city,
      address: this.updatedCentre.address,
      phoneNumber: this.updatedCentre.phoneNumber,
      medecins: this.updatedCentre.medecins ? this.updatedCentre.medecins.map((m: any) => ({ id: m.id })) : [], 
      reservations: this.updatedCentre.reservations ? this.updatedCentre.reservations.map((r: any) => ({ id: r.id })) : []  
    };
  
    console.log(" Données envoyées :", JSON.stringify(centreData));
  
    this.authService.updateCentre(this.updatedCentre.id, centreData).subscribe(
      (response) => {
        console.log(" Centre mis à jour avec succès :", response);
        this.getAllCentres();  // Rafraîchir la liste
        this.showMessage("Centre mis à jour avec succès !");
      },
      (error) => {
        console.error(" Erreur lors de la mise à jour :", error);
        this.showMessage("Erreur lors de la mise à jour du centre !");
      }
    );
  }
  
  getMedecinsByCentre(centreId: number) {
    this.http.get<any[]>(`http://localhost:8080/api/medecins/centre/${centreId}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => {
        console.log(" Médecins récupérés :", data);
      },
      error => {
        console.error(" Erreur lors de la récupération des médecins :", error);
      }
    );
  }
  

  //  Supprimer un centre
  deleteCentre(id: number) {
    if (!confirm("Êtes-vous sûr de vouloir supprimer ce centre ?")) {
      return;
    }
  
    this.authService.deleteCentre(id).subscribe(
      () => {
        console.log(" Centre supprimé !");
        this.getAllCentres();  // Rafraîchir après suppression
      },
      (error) => {
        console.error(" Erreur lors de la suppression :", error);
      }
    );
  }
  

  //  Récupérer tous les administrateurs (filtrés avec role.id === 1)
  getAllAdministrateurs() {
    this.authService.getAllAdmins().subscribe(
      data => {
        console.log(" Admins récupérés :", data);
        this.administrateurs = data;
      },
      error => {
        console.error(" Erreur chargement administrateurs :", error);
        this.errorMessage = 'Erreur chargement administrateurs';
      }
    );
  }
  
  addAdmin() {
    if (!this.newAdmin.name || !this.newAdmin.email || !this.newAdmin.password || !this.newAdmin.centreId) {
      console.error(" Remplissez tous les champs !");
      return;
    }
  
    this.authService.addAdmin(this.newAdmin).subscribe(
      response => {
        console.log(" Administrateur ajouté :", response);
        this.getAllAdministrateurs();
        this.newAdmin = { name: '', email: '', password: '', centreId: null };
        this.showMessage("Administrateur ajouté avec succès !");
      },
      error => {
        console.error(" Erreur lors de l'ajout :", error);
        this.showMessage("Échec de l'ajout de l'administrateur !");
      }
    );
  }
  

  selectAdminForUpdate(admin: any) {
    this.updatedAdmin = { ...admin }; // Copie des valeurs pour éviter la modification en direct
  }
  
  // modifier un administrateur
  updateAdmin() {
    if (!this.updatedAdmin.id || !this.updatedAdmin.name || !this.updatedAdmin.email || !this.updatedAdmin.centreId) {
      this.showMessage(" Informations de l'administrateur invalides !");
      return;
    }
  
    this.authService.updateAdmin(this.updatedAdmin.id, this.updatedAdmin).subscribe(
      response => {
        console.log(" Administrateur mis à jour :", response);
        this.getAllAdministrateurs(); // Rafraîchir la liste
        this.updatedAdmin = { id: null, name: '', email: '', centreId: null }; // Réinitialisation du formulaire
        this.showMessage("Administrateur mis à jour avec succès !");
      },
      error => {
        console.error(" Erreur lors de la mise à jour :", error);
        this.showMessage("Erreur lors de la mise à jour !");
      }
    );
  }
  
  

  //  Supprimer un administrateur
  deleteAdmin(id: number) {
    if (!confirm("Êtes-vous sûr de vouloir supprimer cet administrateur ?")) {
      return;
    }
  
    this.authService.deleteAdmin(id).subscribe(
      () => {
        console.log("Administrateur supprimé !");
        this.getAllAdministrateurs();
        this.showMessage("Administrateur supprimé avec succès !");
      },
      error => {
        console.error(" Erreur lors de la suppression :", error);
        this.showMessage("Échec de la suppression !");
      }
    );
  }
}  
