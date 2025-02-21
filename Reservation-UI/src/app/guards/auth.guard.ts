import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    const userRole = this.authService.getRole();

    console.log(" Rôle attendu :", expectedRoles);
    console.log(" Rôle utilisateur :", userRole);

    if (userRole && expectedRoles.includes(userRole)) {
      return true;
    }
    
    console.warn("Accès refusé ! Redirection vers /login");
    this.router.navigate(['/login']);
    return false;
  }
}
