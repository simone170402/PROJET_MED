import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api';
  private baseUrl = 'http://localhost:8080/api/auth';
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

   //  Ajout d'un BehaviorSubject pour le rôle
   private roleSubject = new BehaviorSubject<string | null>(this.getRole());
   public role$ = this.roleSubject.asObservable();  // Observable du rôle
 
   constructor(private http: HttpClient) {
     this.currentUserSubject = new BehaviorSubject<any>(this.getUserFromStorage());
     this.currentUser = this.currentUserSubject.asObservable();
   }
 
   // Connexion utilisateur (Basic Auth)
   login(email: string, password: string): Observable<any> {
    const credentials = btoa(`${email}:${password}`);
    const headers = new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'Content-Type': 'application/json'
    });

    console.log("Envoi du header Authorization:", headers.get('Authorization'));

    return this.http.post(`${this.baseUrl}/login`, {}, { headers }).pipe(
        map((response: any) => {
            console.log(" Réponse complète de l'API :", response);
            
            if (response && response.token && response.user) {
                console.log('Token reçu:', response.token);
                
                //  Extraire correctement le rôle
                let roleStocke = "PATIENT"; // Valeur par défaut
                if (response.user.role && Array.isArray(response.user.role) && response.user.role.length > 0) {
                    roleStocke = response.user.role[0].name; // Récupère le nom du premier rôle
                }

                console.log(' Rôle extrait :', roleStocke);

                // Stocker l'utilisateur
                this.storeUser({
                    token: response.token,
                    role: roleStocke // Stocke "SUPER_ADMIN"
                });

                // Mettre à jour le BehaviorSubject
                this.roleSubject.next(roleStocke);
            } else {
                console.error(" Problème : `user` ou `role` absent de la réponse !");
            }

            return response;
        }),
        catchError(error => {
            console.error(" Erreur de connexion :", error);
            return throwError(() => new Error(error.message || "Erreur inconnue"));
        })
    );    
}


   // Stockage utilisateur
  private getUserFromStorage(): any {
     if (typeof window !== 'undefined') {
       const user = localStorage.getItem('user');
       console.log("Utilisateur récupéré depuis le localStorage :", user);
       return user ? JSON.parse(user) : null;
     }
     return null;
   }

   private storeUser(user: any): void {
    if (!user.token) {
      console.error(" Erreur : Aucun token trouvé !");
      return;
    }
  
    localStorage.setItem('user', JSON.stringify({
      token: user.token,
      role: user.role || "PATIENT"  // Ajoute un rôle par défaut si inexistant
    }));
  
    console.log(" Utilisateur stocké :", JSON.parse(localStorage.getItem('user')!));
    this.roleSubject.next(user.role);  // Mettre à jour le BehaviorSubject
  }
  

 
  getToken(): string | null {
     const user = this.getUserFromStorage();
     return user ? user.token : null;
   }
 
   getRole(): string {
     const user = this.getUserFromStorage();
     console.log('Rôle récupéré depuis localStorage:', user?.role);
   
     if (user && user.role) {
         return user.role;
     }
     return 'PATIENT';
   }
 
   hasRole(allowedRoles: string[]): boolean {
     return allowedRoles.includes(this.getRole());
   }
 
   isSuperAdmin(): boolean {
     return this.getRole() === 'SUPER_ADMIN';
   }
 
   isLoggedIn(): boolean {
     return !!this.getToken();
   }
 
   logout(): void {
     localStorage.removeItem('user');
     this.roleSubject.next(null); //  Met à jour le rôle
     this.currentUserSubject.next(null);
   }
  

        
  

  // Inscription utilisateur
  signup(name: string, email: string, password: string, roles: string[]): Observable<any> {
    const body = { name, email, password, roles };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    console.log("📡 Envoi des données d'inscription :", body);
  
    return this.http.post(`${this.baseUrl}/signup`, body, { headers }).pipe(
      map(response => {
        console.log("Inscription réussie :", response);
        return response;
      }),
      catchError(error => {
        console.error(" Erreur lors de l'inscription :", error);
        return throwError(() => new Error(error));
      })
    );
  }

  // GESTION DES CENTRES (CRUD)
  getAllCentres(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/centres`, { headers: this.getAuthHeaders() });
  }
  addCentre(centre: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/centres`, centre, { headers: this.getAuthHeaders() });
  }
  deleteCentre(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/centres/${id}`, { headers: this.getAuthHeaders() });
  }
  updateCentre(id: number, centre: any): Observable<any> {
    console.log("Requête PUT vers :", `${this.apiUrl}/centres/${id}`);
    console.log("Données envoyées :", centre);
    
    return this.http.put(`${this.apiUrl}/centres/${id}`, centre, { headers: this.getAuthHeaders() }).pipe(
      catchError(error => {
        console.error("Erreur API :", error);
        return throwError(() => new Error(error.message || "Erreur inconnue"));
      })
    );
  }
  
  

  // GESTION DES ADMINISTRATEURS (CRUD)
  getAllAdmins(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/administrateurs', {
      headers: this.getAuthHeaders()
    });
  }
  
  addAdmin(admin: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/administrateurs', admin, {
      headers: this.getAuthHeaders()
    });
  }

  updateAdmin(id: number, admin: any): Observable<any> {
    return this.http.put(`http://localhost:8080/api/administrateurs/${id}`, admin, {
      headers: this.getAuthHeaders()
    });
  }
  
  deleteAdmin(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/administrateurs/${id}`, { headers: this.getAuthHeaders() });
  }

  // GESTION DES MEDECINS (CRU)
  getMedecinsByCentre(centreId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/centres/${centreId}/medecins`, { headers: this.getAuthHeaders() });
  }
  addMedecin(medecin: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/medecins`, medecin, { headers: this.getAuthHeaders() });
  }
  updateMedecin(id: number, medecin: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/medecins/${id}`, medecin, { headers: this.getAuthHeaders() });
  }

  // GESTION DES RESERVATIONS (RD)
  getReservationsByCentre(centreId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/reservations?centreId=${centreId}`, { headers: this.getAuthHeaders() });
  }
  deleteReservation(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/reservations/${id}`, { headers: this.getAuthHeaders() });
  }

  //  GESTION DES HEADERS
  public getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    if (!token) {
      console.warn('Aucun token trouvé, requête sans authentication');
    }
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    console.log('Headers envoyés:', headers);
    return headers;
  }
}
