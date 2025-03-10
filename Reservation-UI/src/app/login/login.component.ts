import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  

  constructor(private authService: AuthService, public router: Router) { }

  login() {
    this.authService.login(this.email, this.password).subscribe(
        response => {
            localStorage.setItem('token', response.token);
            // Récupérer le rôle et le stocker si nécessaire
            const userRole = response.user.utilisateurRoles[0]?.role.name || "PATIENT"; // Assurez-vous que cela soit sécurisé
            localStorage.setItem('userRole', userRole); // Optionnel : stockez le rôle dans le localStorage
            this.router.navigate(['/accueil']);
        },
        error => {
            console.error('Erreur de connexion', error);
        }
    );
}
}