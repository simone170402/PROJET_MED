import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';

export interface Medecin {
  id?: number;
  name: string;
  surname: string;
  phoneNumber: string;
  speciality?: string;
  email?: string;
  disponibilite?: string;
}

@Injectable({
  providedIn: 'root'
})
export class MedecinService {
  private apiUrl = `${environment.apiUrl}/api/medecins`;

  constructor(private http: HttpClient) {}

  getMedecinsByCentreId(centreId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/centre/${centreId}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError('Something bad happened; please try again later.');
  }

  getMedecins(centreId?: number): Observable<Medecin[]> {
    const url = centreId ? `${this.apiUrl}?centreId=${centreId}` : this.apiUrl;
    return this.http.get<Medecin[]>(url);
  }

  getMedecinById(id: number): Observable<Medecin> {
    return this.http.get<Medecin>(`${this.apiUrl}/${id}`);
  }

  createMedecin(medecin: Medecin): Observable<Medecin> {
    return this.http.post<Medecin>(this.apiUrl, medecin);
  }

  updateMedecin(id: number, medecin: Medecin): Observable<Medecin> {
    return this.http.put<Medecin>(`${this.apiUrl}/${id}`, medecin);
  }

  deleteMedecin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getDisponibilites(medecinId: number, date: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/${medecinId}/disponibilites?date=${date}`);
  }

  updateDisponibilites(medecinId: number, disponibilites: string[]): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${medecinId}/disponibilites`, disponibilites);
  }
}