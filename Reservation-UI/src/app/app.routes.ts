import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CentresComponent } from './centres/centres.component';
import { ReservationComponent } from './reservation/reservation.component';
import { MedecinsComponent } from './medecins/medecins.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AuthGuard } from './guards/auth.guard';
import { AdminGuard } from './guards/admin.guard';
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { AccueilComponent } from './accueil/accueil.component';
import { BookingComponent } from './booking/booking.component';

export const routes: Routes = [
  // Front-office routes
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'accueil', 
    component: AccueilComponent
  },
  {
    path: 'centres',
    component: CentresComponent
  },
  {
    path: 'reservation', 
    component: ReservationComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'medecins', 
    component: MedecinsComponent
  },
  {
    path: 'medecins/:centreId', 
    component: MedecinsComponent
  },
  {
    path: 'reservation/:medecinId', 
    component: ReservationComponent
  },
  {
    path: 'booking/:centreId',
    component: BookingComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'confirmation', 
    component: ConfirmationComponent
  },

  // Back-office routes
  {
    path: 'admin',
    children: [
      {
        path: 'login',
        component: AdminLoginComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'medecins',
        component: MedecinsComponent,
        canActivate: [AdminGuard],
        data: { admin: true }
      },
      {
        path: 'reservations',
        component: ReservationComponent,
        canActivate: [AdminGuard],
        data: { admin: true }
      },
      {
        path: 'doctors',
        loadComponent: () => import('./medecins/medecins.component').then(m => m.MedecinsComponent),
        canActivate: [AdminGuard]
      },
      {
        path: 'patients',
        loadComponent: () => import('./admin/patients/patients.component').then(m => m.PatientsComponent),
        canActivate: [AdminGuard]
      },
      {
        path: 'appointments',
        loadComponent: () => import('./reservation/reservation.component').then(m => m.ReservationComponent),
        canActivate: [AdminGuard]
      }
    ]
  },

  {
    path: '',
    redirectTo: '/centres',
    pathMatch: 'full'
  },
  {
    path: '**',
    loadComponent: () => import('@app/not-found/not-found.component').then(m => m.NotFoundComponent)
  }
];
