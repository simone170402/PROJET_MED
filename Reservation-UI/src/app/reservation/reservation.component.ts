import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../services/Booking.service';
import { ActivatedRoute } from '@angular/router';

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

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.medecinId = +this.route.snapshot.paramMap.get('medecinId')!;
    this.bookingService.getAgenda(this.medecinId).subscribe(data => {
      this.agenda = data;
    });
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
}





