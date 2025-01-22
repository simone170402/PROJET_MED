import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) {}

  getEvents(from: string, to: string): Observable<any[]> {
    return this.http.get<any[]>(`/api/reservations?from=${from}&to=${to}`);
  }
}
