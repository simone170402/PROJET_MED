import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterModule],
  template: `
    <div class="not-found">
      <h1>404</h1>
      <h2>Page non trouvée</h2>
      <p>La page que vous recherchez n'existe pas.</p>
      <a routerLink="/" class="home-link">Retour à l'accueil</a>
    </div>
  `,
  styles: [`
    .not-found {
      text-align: center;
      padding: 4rem 1rem;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      background-color: #f8f9fa;
    }

    h1 {
      font-size: 6rem;
      color: #dc3545;
      margin: 0;
    }

    h2 {
      font-size: 2rem;
      color: #343a40;
      margin: 1rem 0;
    }

    p {
      color: #6c757d;
      margin-bottom: 2rem;
    }

    .home-link {
      display: inline-block;
      padding: 0.75rem 1.5rem;
      background-color: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      transition: background-color 0.2s;
    }

    .home-link:hover {
      background-color: #0056b3;
    }
  `]
})
export class NotFoundComponent {}
