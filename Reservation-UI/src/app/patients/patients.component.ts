import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports:[FormsModule, CommonModule],
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients: any[] = [];
  selectedPatient: any = null;
  newPatient: any = { name: '', surname: '', phoneNumber: '', email: '', dateOfBirth: '', adresse: '' };
  searchQuery: string = '';
  message: string = '';

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.getAllPatients();
  }

  // Récupérer tous les patients
  getAllPatients() {
    this.http.get<any[]>('http://localhost:8080/api/patients', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => this.patients = data,
      error => console.error('Erreur lors du chargement des patients', error)
    );
  }

  //  Rechercher un patient par nom
  searchPatient() {
    if (this.searchQuery.trim() === '') return;
    
    this.http.get<any[]>(`http://localhost:8080/api/patients/search?name=${this.searchQuery}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => {
        this.patients = data;
        this.message = data.length ? '' : 'Aucun patient trouvé.';
      },
      error => console.error('Erreur lors de la recherche', error)
    );
  }

  //  Ajouter un patient
  addPatient() {
    this.http.post('http://localhost:8080/api/patients', this.newPatient, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      () => {
        this.message = 'Patient ajouté avec succès';
        this.getAllPatients();
        this.newPatient = { name: '', surname: '', phoneNumber: '', email: '', dateOfBirth: '', adresse: '' };
      },
      error => console.error('Erreur lors de l\'ajout', error)
    );
  }

  //  Sélectionner un patient pour mise à jour
  selectPatient(patient: any) {
    this.selectedPatient = { ...patient };
  }

  //  Mettre à jour un patient
  updatePatient() {
    this.http.put(`http://localhost:8080/api/patients/${this.selectedPatient.id}`, this.selectedPatient, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      () => {
        this.message = 'Patient mis à jour avec succès';
        this.getAllPatients();
        this.selectedPatient = null;
      },
      error => console.error('Erreur lors de la mise à jour', error)
    );
  }

  // Supprimer un patient
  deletePatient(id: number) {
    this.http.delete(`http://localhost:8080/api/patients/${id}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      () => {
        this.message = 'Patient supprimé';
        this.getAllPatients();
      },
      error => console.error('Erreur lors de la suppression', error)
    );
  }

  //  Valider la vaccination d'un patient
  validateVaccination(id: number) {
    this.http.put(`http://localhost:8080/api/patients/${id}/validate`, {}, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      () => {
        this.message = 'Vaccination validée avec succès';
        this.getAllPatients();
      },
      error => console.error('Erreur lors de la validation', error)
    );
  }
}
