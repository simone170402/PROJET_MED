import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  medecins: any[] = [];
  reservations: any[] = [];
  newMedecin = { name: '', email: '', speciality: '' };
  centreId: number | null = null; // ID du centre de l'admin

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.getAdminCentre();
  }

  // 📌 Récupérer le centre de l'admin
  getAdminCentre() {
    this.http.get<any>('http://localhost:8080/api/auth/me', {
      headers: this.authService.getAuthHeaders()
    }).subscribe(user => {
      this.centreId = user.centre.id;
      this.getMedecins();
      this.getReservations();
    });
  }

  // 📌 Récupérer les médecins du centre
  getMedecins() {
    this.http.get<any[]>(`http://localhost:8080/api/centres/${this.centreId}/medecins`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => this.medecins = data,
      error => console.error('Erreur chargement médecins', error)
    );
  }

  // 📌 Ajouter un médecin
  addMedecin() {
    const medecinData = { ...this.newMedecin, centreId: this.centreId };
    this.http.post('http://localhost:8080/api/medecins', medecinData, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => {
      this.getMedecins();
      this.newMedecin = { name: '', email: '', speciality: '' };
    });
  }

  // 📌 Modifier un médecin
  updateMedecin(id: number, updatedMedecin: any) {
    this.http.put(`http://localhost:8080/api/medecins/${id}`, updatedMedecin, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => this.getMedecins());
  }

  // 📌 Récupérer les réservations du centre
  getReservations() {
    this.http.get<any[]>(`http://localhost:8080/api/reservations?centreId=${this.centreId}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(
      data => this.reservations = data,
      error => console.error('Erreur chargement réservations', error)
    );
  }

  // 📌 Supprimer une réservation
  deleteReservation(id: number) {
    this.http.delete(`http://localhost:8080/api/reservations/${id}`, {
      headers: this.authService.getAuthHeaders()
    }).subscribe(() => this.getReservations());
  }
}
