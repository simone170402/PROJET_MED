import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, CommonModule, MatInputModule, MatButtonModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  roles: string[] = ['PATIENT'];

  constructor(private authService: AuthService, public router: Router) { }

  signup() {
    this.authService.signup(this.name, this.email, this.password, this.roles).subscribe(
      response => {
        console.log('Inscription réussie', response);
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Erreur d\'inscription', error);
      }
    );
  }
}