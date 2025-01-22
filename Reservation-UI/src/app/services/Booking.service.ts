import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getCentres(): Observable<any> {
    return this.http.get(`${this.apiUrl}/centres`);
  }

  getMedecins(centreId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/centres/${centreId}/medecins`);
  }

  getAgenda(medecinId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/reservations/medecin/${medecinId}`);
  }

  createReservation(reservationData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/reservations`, reservationData);
  }
}
