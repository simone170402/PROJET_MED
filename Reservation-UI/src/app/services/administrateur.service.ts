import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Administrateur } from '../model/administrateur.model';

@Injectable({
    providedIn: 'root'
})
export class AdministrateurService {
    private apiUrl = 'http://localhost:8080/api/administrateurs';

    constructor(private http: HttpClient) {}

    getAllAdministrateurs(): Observable<Administrateur[]> {
        return this.http.get<Administrateur[]>(this.apiUrl);
    }

    getAdministrateurById(id: number): Observable<Administrateur> {
        return this.http.get<Administrateur>(`${this.apiUrl}/${id}`);
    }

    createAdministrateur(administrateur: Administrateur): Observable<Administrateur> {
        return this.http.post<Administrateur>(this.apiUrl, administrateur);
    }

    updateAdministrateur(id: number, administrateur: Administrateur): Observable<Administrateur> {
        return this.http.put<Administrateur>(`${this.apiUrl}/${id}`, administrateur);
    }

    deleteAdministrateur(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}