import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private baseUrl = 'http://localhost:8080/api'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}

  /**
   * Enregistrer un patient.
   */
  savePatient(patient: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/patients`, patient);
  }

  /**
   * Enregistrer une réservation.
   */
  saveReservation(reservation: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/reservations`, reservation);
  }

  /**
   * Réserver un créneau (Patient + Réservation combinés).
   */
  reserve(data: { patient: any; reservation: any }): Observable<any> {
    return this.http.post(`${this.baseUrl}/reservations/reserve`, data, {
      headers: { 'Content-Type': 'application/json' },
    }).pipe(
      catchError((error) => {
        console.error('Backend returned error:', error);
        return throwError(() => new Error('Failed to reserve. Please try again.'));
      })
    );
  }
  
  
}
