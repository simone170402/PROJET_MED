import { Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { ReservationComponent } from './reservation/reservation.component';
import { CentresComponent } from './centres/centres.component';
import { MedecinsComponent } from './medecins/medecins.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AgendaComponent } from './agenda/agenda.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './guards/auth.guard';
import { SuperAdminComponent } from './superadmin/superadmin.component';
import { PatientsComponent } from './patients/patients.component';

export const routes: Routes = [
    // **Redirection par défaut vers la page de login**
    { path: '', redirectTo: '/login', pathMatch: 'full' },

    // **Routes accessibles à tous**
    { path: 'accueil', component: AccueilComponent },
    { path: 'centres', component: CentresComponent },
    { path: 'medecins', component: MedecinsComponent },
    { path: 'medecins/:centreId', component: MedecinsComponent },
    { path: 'reservation/:medecinId', component: ReservationComponent },
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'agenda', component: AgendaComponent },
    { path: 'signup', component: SignupComponent },
    { path: 'login', component: LoginComponent },
    { path: 'reservation', component: ReservationComponent },

    // **Super Admin - Gestion des centres et des admins**
    {
        path: 'admin/superadmin',
        component: SuperAdminComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SUPER_ADMIN'] }
    },

    // **Admin Centre - Gestion des médecins et réservations**
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AuthGuard],
        data: { roles: ['ADMIN_CENTRE'] }
    },

    // **Médecin - Gestion des patients**
    {
        path: 'patients',
        component: PatientsComponent,
        canActivate: [AuthGuard],
        data: { roles: ['MEDECIN'] }
    },

    // **Gestion des erreurs - Redirection vers login en cas de mauvaise route**
    { path: '**', redirectTo: '/login', pathMatch: 'full' }
];
