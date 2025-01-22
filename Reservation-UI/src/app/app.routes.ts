import { Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import path from 'path';
import { ReservationComponent } from './reservation/reservation.component';
import { CentresComponent } from './centres/centres.component';
import { MedecinsComponent } from './medecins/medecins.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AgendaComponent } from './agenda/agenda.component';

export const routes: Routes = [
    { path: 'reservation', component: ReservationComponent },
    { path: 'accueil', component: AccueilComponent },
    { path: '', redirectTo: '/accueil', pathMatch: 'full' },
    { path: 'centres', component: CentresComponent },
    { path: 'medecins', component: MedecinsComponent },
    { path: 'medecins/:centreId', component: MedecinsComponent },
    { path: 'reservation/:medecinId', component: ReservationComponent },
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'agenda', component: AgendaComponent }
];
