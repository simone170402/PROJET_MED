import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CentreService {
  private baseUrl = '/api/centres';

  constructor(private http: HttpClient) {}

  getAllCentres(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getCentresByCity(city: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search`, { params: { city } });
  }

  getMedecinsByCentreId(centreId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/centres/${centreId}/medecins`);
  }
  
  
}
