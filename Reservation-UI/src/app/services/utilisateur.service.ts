import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from '../model/utilisateur.model';

@Injectable({
    providedIn: 'root'
})
export class UtilisateurService {
    private apiUrl = '/api/utilisateurs';

    constructor(private http: HttpClient) {}

    getAllUtilisateurs(): Observable<Utilisateur[]> {
        return this.http.get<Utilisateur[]>(this.apiUrl);
    }

    getUtilisateurById(id: number): Observable<Utilisateur> {
        return this.http.get<Utilisateur>(`${this.apiUrl}/${id}`);
    }

    createUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
        return this.http.post<Utilisateur>(this.apiUrl, utilisateur);
    }

    updateUtilisateur(id: number, utilisateur: Utilisateur): Observable<Utilisateur> {
        return this.http.put<Utilisateur>(`${this.apiUrl}/${id}`, utilisateur);
    }

    deleteUtilisateur(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}