import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Centre } from '../model/centre.model';


@Injectable({
    providedIn: 'root'
  })
  export class CentreService {
    private apiUrl = '/api/superadmin/centres';
  
    constructor(private http: HttpClient) {}
  
    getAllCentres(): Observable<Centre[]> {
      return this.http.get<Centre[]>(this.apiUrl);
    }
  
    createCentre(centre: Centre): Observable<Centre> {
      return this.http.post<Centre>(this.apiUrl, centre);
    }
  
    updateCentre(id: number, centre: Centre): Observable<Centre> {
      return this.http.put<Centre>(`${this.apiUrl}/${id}`, centre);
    }
  
    deleteCentre(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
  }
  