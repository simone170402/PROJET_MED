import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../services/Booking.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';

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
  medecinId!: number;
  agenda: any[] = [];
  reservation: any = { nom: '', prenom: '', email: '', date: '' };
  isAdminMode: boolean = false;
  selectedDate: string = '';
  errorMessage: string = '';
  reservations: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.medecinId = +this.route.snapshot.paramMap.get('medecinId')!;
    this.bookingService.getAgenda(this.medecinId).subscribe(data => {
      this.agenda = data;
    });

    // Vérifier si on est en mode admin
    this.route.data.subscribe(data => {
      this.isAdminMode = data['admin'] === true;
    });
  }

  onDateChange() {
    this.errorMessage = '';
    if (this.selectedDate) {
      this.reservation.date = this.selectedDate;
    }
  }

  reserve(): void {
    const reservationData = {
      ...this.reservation,
      medecinId: this.medecinId
    };
    this.bookingService.createReservation(reservationData).subscribe(() => {
      this.router.navigate(['/confirmation']);
    });
  }

  // Méthodes spécifiques au mode admin
  approuverReservation(id: number) {
    if (!this.isAdminMode) return;
    // TODO: implémenter la méthode d'approbation de réservation
  }

  refuserReservation(id: number) {
    if (!this.isAdminMode) return;
    // TODO: implémenter la méthode de refus de réservation
  }

  annulerReservation(id: number) {
    // TODO: implémenter la méthode d'annulation de réservation
  }
}
