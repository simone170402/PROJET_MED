import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { StorageService } from './storage.service'; // Importez votre service

@Injectable({
  providedIn: 'root',
})
export class CentreService {
  private baseUrl = 'http://localhost:8080/api/centres';

  constructor(private http: HttpClient, private storageService: StorageService) {}

  private getAuthHeaders(): HttpHeaders {
    const token = this.storageService.getItem('token'); // Utilisez le service pour récupérer le token
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }

  getAllCentres(): Observable<any[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<any[]>(this.baseUrl, { headers }).pipe(
        catchError(error => {
            console.error('Erreur lors de la récupération des centres', error);
            return throwError(error);
        })
    );
  }

  getCentresByCity(city: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/search`, { headers, params: { city } });
  }

  getMedecinsByCentreId(centreId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`http://localhost:8080/api/centres/${centreId}/medecins`, { headers });
  }

  createCentre(centreData: any): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post(this.baseUrl, centreData, { headers });
  }

  updateCentre(id: number | string, centreData: any): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(`${this.baseUrl}/${id}`, centreData, { headers });
  }  

  deleteCentre(id: number | string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.delete(`${this.baseUrl}/${id}`, { headers });
  }  
}