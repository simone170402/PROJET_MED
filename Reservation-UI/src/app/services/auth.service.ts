import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap, of } from 'rxjs';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private userRoleSubject = new BehaviorSubject<string>('');
  
  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();
  userRole$ = this.userRoleSubject.asObservable();
  
  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    if (isPlatformBrowser(this.platformId)) {
      this.checkAuthStatus();
    }
  }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }

  login(username: string, password: string, isBackOffice: boolean = false): Observable<any> {
    if (!isPlatformBrowser(this.platformId)) {
      return of(null);
    }

    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(username + ':' + password)
    });

    const endpoint = isBackOffice ? `${this.apiUrl}/admin/login` : `${this.apiUrl}/login`;

    return this.http.post(endpoint, {}, { headers }).pipe(
      tap((response: any) => {
        this.setCredentials(username, password);
        this.isAuthenticatedSubject.next(true);
        this.userRoleSubject.next(response.role);
      })
    );
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('credentials');
    }
    this.isAuthenticatedSubject.next(false);
    this.userRoleSubject.next('');
    this.router.navigate(['/login']);
  }

  isAdmin(): boolean {
    return this.userRoleSubject.value === 'ROLE_ADMIN';
  }

  private setCredentials(username: string, password: string) {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('credentials', btoa(username + ':' + password));
    }
  }

  getAuthorizationHeaders(): HttpHeaders {
    if (!isPlatformBrowser(this.platformId)) {
      return new HttpHeaders();
    }

    const credentials = localStorage.getItem('credentials');
    if (credentials) {
      return new HttpHeaders({
        'Authorization': 'Basic ' + credentials
      });
    }
    return new HttpHeaders();
  }

  checkAuthStatus() {
    const credentials = localStorage.getItem('credentials');
    if (credentials) {
      this.http.get(`${this.apiUrl}/check`).subscribe({
        next: (response: any) => {
          this.isAuthenticatedSubject.next(response.authenticated);
          this.userRoleSubject.next(response.role);
        },
        error: () => {
          this.logout();
        }
      });
    }
  }
}
