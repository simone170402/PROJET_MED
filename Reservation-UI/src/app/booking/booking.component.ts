import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CalendarOptions } from '@fullcalendar/core';
import { FullCalendarModule } from '@fullcalendar/angular'; // Import du module FullCalendar
import { CommonModule } from '@angular/common';
import dayGridPlugin from '@fullcalendar/daygrid'; // Plugins nécessaires
import timeGridPlugin from '@fullcalendar/timegrid';
import { MedecinService } from '../services/medecin.service';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [FullCalendarModule, CommonModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  doctor: any; // Informations sur le médecin
  reservations: any[] = []; // Réservations existantes
  location!: string;
  city!: string;
  calendarOptions!: CalendarOptions;

  constructor(
    private route: ActivatedRoute,
    private medecinService: MedecinService,
    private reservationService: ReservationService
  ) {}

  ngOnInit() {
   
  }

  // Charger les réservations pour un médecin
  loadReservations(doctorId: number) {
    this.reservationService.getReservations().subscribe(
      reservations => {
        // Filtrer les réservations par médecin
        this.reservations = reservations.filter(
          reservation => reservation.doctorId === doctorId
        );
        // Configurer les options du calendrier avec les réservations
        this.setCalendarOptions();
      },
      error => {
        console.error('Erreur lors du chargement des réservations:', error);
      }
    );
  }

  // Configurer les options du calendrier
  setCalendarOptions() {
    this.calendarOptions = {
      initialView: 'timeGridWeek', // Vue par défaut : semaine
      plugins: [dayGridPlugin, timeGridPlugin], // Plugins nécessaires
      events: this.reservations.map(reservation => ({
        title: reservation.title,
        start: reservation.dateStart,
        end: reservation.dateEnd,
        backgroundColor: reservation.backgroundColor || '#007bff'
      })),
      headerToolbar: {
        start: 'prev,next today', // Contrôles du calendrier
        center: 'title',
        end: 'dayGridMonth,timeGridWeek,timeGridDay' // Types de vues disponibles
      }
    };
  }
}
