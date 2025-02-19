import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FranceConnectService {
  private readonly clientId = environment.franceConnect.clientId;
  private readonly redirectUri = `${window.location.origin}/auth/callback`;
  private readonly scope = 'openid profile email';
  private readonly responseType = 'code';
  private readonly authEndpoint = 'https://fcp.integ01.dev-franceconnect.fr/api/v1/authorize';

  constructor(private http: HttpClient) {}

  initiateLogin(): void {
    const params = new URLSearchParams({
      response_type: this.responseType,
      client_id: this.clientId,
      redirect_uri: this.redirectUri,
      scope: this.scope,
      state: this.generateState(),
      nonce: this.generateNonce()
    });

    window.location.href = `${this.authEndpoint}?${params.toString()}`;
  }

  handleCallback(code: string): Observable<any> {
    return this.http.post('/api/auth/france-connect/callback', { code });
  }

  private generateState(): string {
    return Math.random().toString(36).substring(2, 15);
  }

  private generateNonce(): string {
    return Math.random().toString(36).substring(2, 15);
  }
}
