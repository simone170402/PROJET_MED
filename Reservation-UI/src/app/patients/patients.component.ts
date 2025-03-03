import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule
  ],
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients: any[] = [];
  centres: any[] = [];
  searchName: string = '';
  selectedPatient: any = null;
  newPatient: any = {
    name: '', surname: '', phoneNumber: '', email: '', dateOfBirth: '',
    adresse: '', centreId: '', vaccinationStatus: 'Non Vacciné'
  };

  constructor(private http: HttpClient, private authService: AuthService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.getAllPatients();
  }

  getAllPatients() {
    this.http.get<any[]>('http://localhost:8080/api/patients', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => this.patients = data,
      error => console.error('Erreur lors du chargement des patients', error)
    );
  }

  addPatient() {
    this.http.post('http://localhost:8080/api/patients', this.newPatient, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => {
      this.showMessage('Patient ajouté avec succès');
      this.getAllPatients();
      this.resetForm();
    });
  }

  deletePatient(id: number) {
    if (confirm('Voulez-vous vraiment supprimer ce patient ?')) {
      this.http.delete(`http://localhost:8080/api/patients/${id}`, {
        headers: this.authService.getAuthHeaders()
      }).subscribe(() => {
        this.showMessage('Patient supprimé');
        this.getAllPatients();
      });
    }
  }

  selectPatientForUpdate(patient: any): void {
    this.selectedPatient = { ...patient }; // Pré-remplit le formulaire de modification
  }

  updatePatient() {
    this.http.put(`http://localhost:8080/api/patients/${this.selectedPatient.id}`, this.selectedPatient, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => {
      this.showMessage('Patient mis à jour');
      this.getAllPatients();
      this.selectedPatient = null; // Réinitialise la sélection
    });
  }

  changeVaccinationStatus(patient: any) {
    patient.vaccinationStatus = patient.vaccinationStatus === 'Vacciné' ? 'Non Vacciné' : 'Vacciné';
    this.updatePatient();
  }

  showMessage(message: string) {
    this.snackBar.open(message, 'Fermer', { duration: 3000 });
  }

  resetForm() {
    this.newPatient = { name: '', surname: '', phoneNumber: '', email: '', dateOfBirth: '', adresse: '', centreId: '', vaccinationStatus: 'Non Vacciné' };
    this.selectedPatient = null; // Réinitialise la sélection
  }
}