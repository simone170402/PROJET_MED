import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="admin-dashboard">
      <header class="admin-header">
        <h1>Administration</h1>
        <button class="logout-btn" (click)="logout()">Déconnexion</button>
      </header>

      <nav class="admin-nav">
        <a routerLink="/admin/doctors" class="nav-item">
          <i class="fas fa-user-md"></i>
          Médecins
        </a>
        <a routerLink="/admin/patients" class="nav-item">
          <i class="fas fa-users"></i>
          Patients
        </a>
        <a routerLink="/admin/appointments" class="nav-item">
          <i class="fas fa-calendar-alt"></i>
          Rendez-vous
        </a>
      </nav>

      <div class="dashboard-content">
        <div class="welcome-message">
          <h2>Bienvenue dans l'interface d'administration</h2>
          <p>Sélectionnez une option dans le menu pour commencer.</p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .admin-dashboard {
      min-height: 100vh;
      background-color: #f8f9fa;
    }

    .admin-header {
      background-color: #343a40;
      color: white;
      padding: 1rem 2rem;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .logout-btn {
      background-color: #dc3545;
      color: white;
      border: none;
      padding: 0.5rem 1rem;
      border-radius: 4px;
      cursor: pointer;
    }

    .admin-nav {
      background-color: white;
      padding: 1rem;
      display: flex;
      gap: 1rem;
      border-bottom: 1px solid #dee2e6;
    }

    .nav-item {
      text-decoration: none;
      color: #495057;
      padding: 0.5rem 1rem;
      border-radius: 4px;
      display: flex;
      align-items: center;
      gap: 0.5rem;
    }

    .nav-item:hover {
      background-color: #e9ecef;
    }

    .dashboard-content {
      padding: 2rem;
    }

    .welcome-message {
      background-color: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      text-align: center;
    }

    h2 {
      color: #343a40;
      margin-bottom: 1rem;
    }

    p {
      color: #6c757d;
    }
  `]
})
export class DashboardComponent {
  constructor(private authService: AuthService) {}

  logout() {
    this.authService.logout();
  }
}
