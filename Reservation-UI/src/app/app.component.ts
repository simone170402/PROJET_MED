import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { AccueilComponent } from './accueil/accueil.component';
import { FooterComponent } from './footer/footer.component';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    RouterOutlet
    
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

  title = 'Reservation-UI';
  
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Vérifier si l'utilisateur est connecté
    if (this.authService.isLoggedIn()) {
      // Ne rien faire, il reste sur la page actuelle
      console.log("Utilisateur connecté, reste sur la page :", this.router.url);
    } else {
      // Redirection vers login si l'utilisateur n'est pas connecté
      this.router.navigate(['/login']);
    }
  }
}
  

