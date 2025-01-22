import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CalendarOptions } from '@fullcalendar/core';
import { FullCalendarModule } from '@fullcalendar/angular'; // Import du module FullCalendar
import { CommonModule } from '@angular/common';
import dayGridPlugin from '@fullcalendar/daygrid'; // Plugins nécessaires
import timeGridPlugin from '@fullcalendar/timegrid';
import { MedecinService } from '../services/medecin.service';
import { ReservationService } from '../services/reservation.service';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [FullCalendarModule, CommonModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookingComponent implements OnInit {
  events: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Chargez les créneaux d'un médecin
    this.http.get<any[]>('http://localhost:8080/api/reservations/medecin/1').subscribe((data) => {
      this.events = data.map((reservation) => ({
        title: reservation.reservationStatus === 'Réservé' ? 'Réservé' : 'Disponible',
        start: reservation.datestart,
        end: reservation.dateend,
        backgroundColor: reservation.reservationStatus === 'Réservé' ? 'red' : 'green',
      }));
    });
  }

  reserveCreneau(creneauId: number) {
    this.http.post(`http://localhost:8080/api/reservations/reserve/${creneauId}`, {}).subscribe(() => {
      alert('Créneau réservé avec succès !');
      // Rechargez l'agenda après réservation
      this.ngOnInit();
    });
  }
}
