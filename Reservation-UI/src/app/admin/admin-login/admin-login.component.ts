import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  template: `
    <div class="admin-login-container">
      <div class="admin-login-box">
        <h2>Administration</h2>
        <form [formGroup]="loginForm" (ngSubmit)="onSubmit()">
          <div class="form-group">
            <label for="username">Nom d'utilisateur</label>
            <input
              type="text"
              id="username"
              formControlName="username"
              class="form-control"
              placeholder="Entrez votre nom d'utilisateur"
            >
          </div>

          <div class="form-group">
            <label for="password">Mot de passe</label>
            <input
              type="password"
              id="password"
              formControlName="password"
              class="form-control"
              placeholder="Entrez votre mot de passe"
            >
          </div>

          <div class="error-message" *ngIf="error">
            {{ error }}
          </div>

          <button
            type="submit"
            class="btn btn-primary"
            [disabled]="!loginForm.valid"
          >
            Connexion
          </button>
        </form>
      </div>
    </div>
  `,
  styles: [`
    .admin-login-container {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      background-color: #f5f5f5;
    }

    .admin-login-box {
      background: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 2rem;
    }

    .form-group {
      margin-bottom: 1rem;
    }

    label {
      display: block;
      margin-bottom: 0.5rem;
      color: #666;
    }

    .form-control {
      width: 100%;
      padding: 0.75rem;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 1rem;
    }

    .error-message {
      color: #dc3545;
      margin: 1rem 0;
      text-align: center;
    }

    .btn {
      width: 100%;
      padding: 0.75rem;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 1rem;
      cursor: pointer;
      transition: background-color 0.2s;
    }

    .btn:disabled {
      background-color: #ccc;
      cursor: not-allowed;
    }

    .btn:hover:not(:disabled) {
      background-color: #0056b3;
    }
  `]
})
export class AdminLoginComponent {
  loginForm: FormGroup;
  error: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(
        this.loginForm.value.username,
        this.loginForm.value.password,
        true // isBackOffice = true
      ).subscribe({
        next: () => {
          if (this.authService.isAdmin()) {
            this.router.navigate(['/admin/dashboard']);
          } else {
            this.error = "Accès non autorisé";
          }
        },
        error: (err) => {
          this.error = err.error?.message || 'Échec de la connexion';
        }
      });
    }
  }
}
