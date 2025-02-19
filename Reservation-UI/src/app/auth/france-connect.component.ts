import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FranceConnectService } from './france-connect.service';

@Component({
  selector: 'app-france-connect',
  templateUrl: './france-connect.component.html'
})
export class FranceConnectComponent implements OnInit {
  constructor(
    private franceConnectService: FranceConnectService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // Check if we're handling a callback
    this.route.queryParams.subscribe(params => {
      if (params['code']) {
        this.handleCallback(params['code']);
      }
    });
  }

  login() {
    this.franceConnectService.initiateLogin();
  }

  private handleCallback(code: string) {
    this.franceConnectService.handleCallback(code).subscribe({
      next: (userData) => {
        // Store user data and redirect to home
        localStorage.setItem('userProfile', JSON.stringify(userData));
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('FranceConnect authentication failed:', error);
        // Handle error appropriately
      }
    });
  }
}
