import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
  ],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  role: string | null = null;
  private roleSubscription: Subscription | undefined;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    // 🔥 S'abonner aux changements du rôle
    this.roleSubscription = this.authService.role$.subscribe(role => {
      this.role = role;
      console.log('✅ Rôle mis à jour :', this.role);
    });

    // Charger le rôle initial
    this.role = this.authService.getRole();
    console.log('🚀 Rôle initial récupéré:', this.role);
  }

  logout(): void {
    this.authService.logout();
    this.role = null;
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    if (this.roleSubscription) {
      this.roleSubscription.unsubscribe();
    }
  }
}
