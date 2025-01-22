import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AgendaService {
  private baseUrl = 'http://localhost:8080/api/reservations';

  constructor(private http: HttpClient) {}

  getAgenda(centreId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}?centreId=${centreId}`);
  }
}
