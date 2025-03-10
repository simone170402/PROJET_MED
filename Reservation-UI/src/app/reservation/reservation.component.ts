import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ReservationService } from '../services/reservation.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  formSubmitted = false;

  patient: any = {
    name: '',
    surname: '',
    phoneNumber: '',
    email: '',
    dateOfBirth: '',
    adresse: '',
  };

  reservation: any = {
    dateReservation: '',
    datestart: '',
    dateend: '',
    title: '',
  };

  availableStartTimes: string[] = []; // Créneaux disponibles pour l'heure de début
  centreId: number | null = null;
  medecinId: number | null = null;

  constructor(
    private reservationService: ReservationService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Capture the centreId and medecinId from the URL query parameters
    this.route.queryParams.subscribe(params => {
      this.centreId = params['centreId'];
      this.medecinId = params['medecinId'];
      this.generateAvailableStartTimes();
    });
  }

  generateAvailableStartTimes(): void {
    const startHour = 8; // Début des créneaux
    const endHour = 18; // Fin des créneaux
    const intervalMinutes = 30; // Intervalle entre les créneaux

    for (let hour = startHour; hour < endHour; hour++) {
      this.availableStartTimes.push(this.formatTime(hour, 0));
      this.availableStartTimes.push(this.formatTime(hour, intervalMinutes));
    }
  }

  formatTime(hour: number, minute: number): string {
    // Formate l'heure en "HH:mm"
    const h = hour.toString().padStart(2, '0');
    const m = minute.toString().padStart(2, '0');
    return `${h}:${m}`;
  }

  updateEndTime(): void {
    if (!this.reservation.datestart) {
      return;
    }

    const [hour, minute] = this.reservation.datestart.split(':').map(Number); // Extrait l'heure et les minutes
    const date = new Date();
    date.setHours(hour);
    date.setMinutes(minute + 30); // Ajoute 30 minutes

    const endHour = date.getHours().toString().padStart(2, '0');
    const endMinute = date.getMinutes().toString().padStart(2, '0');

    this.reservation.dateend = `${endHour}:${endMinute}`; // Met à jour l'heure de fin
  }

  handleReservation(): void {
    this.formSubmitted = true;

    // Vérifiez si tous les champs requis sont remplis
    if (
      !this.patient.name ||
      !this.patient.surname ||
      !this.patient.phoneNumber ||
      !this.patient.email ||
      !this.patient.dateOfBirth ||
      !this.patient.adresse ||
      !this.reservation.dateReservation ||
      !this.reservation.datestart ||
      !this.reservation.dateend ||
      !this.reservation.title
    ) {
      alert('Veuillez remplir correctement tous les champs requis.');
      return;
    }

    // Préparer les données pour l'API
    // Préparer les données pour l'API
    const data = {
      patient: {
        name: this.patient.name,
        surname: this.patient.surname,
        phoneNumber: this.patient.phoneNumber,
        email: this.patient.email,
        dateOfBirth: this.patient.dateOfBirth,
        adresse: this.patient.adresse,
        centre: { id: this.centreId },
      },
      reservation: {
        dateReservation: this.reservation.dateReservation,
        datestart: `${this.reservation.dateReservation}T${this.reservation.datestart}`,
        dateend: `${this.reservation.dateReservation}T${this.reservation.dateend}`,
        title: this.reservation.title,
        centre: { id: this.centreId },
        medecin: { id: this.medecinId },
      },
    };

    // Appeler le service pour réserver
    this.reservationService.reserve(data).subscribe(
      (response) => {
        alert('Réservation réussie !');
        this.router.navigate(['/agenda'], { queryParams: { refresh: true } });
      },
      (error) => {
        console.error('Erreur lors de la réservation :', error);
        alert('Une erreur est survenue. Veuillez réessayer.');
      }
    );
  }
}
