import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AgendaService } from '../services/agenda.service';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { CalendarOptions } from '@fullcalendar/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-agenda',
  standalone: true,
  imports: [
    FullCalendarModule
  ],
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AgendaComponent implements OnInit {
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay',
    },
    height: 'auto',
    events: [], // Dynamically populated
    eventClassNames: this.getEventClassNames.bind(this)
  };

  constructor(
    private agendaService: AgendaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const refresh = params['refresh'];
      if (refresh) {
        this.loadReservations(); // Reload events if refresh is requested
      } else {
        this.loadReservations(); // Initial load of reservations
      }
    });
  }

  loadReservations(): void {
    this.agendaService.getAgenda(1).subscribe(
      (data) => {
        const events = data.map((reservation: any, index: number) => {
          const className = `reservation-color-${(index % 8) + 1}`; // Ensure class starts from 1
          console.log(`Reservation ID: ${reservation.id}, Class Name: ${className}`);
  
          return {
            id: reservation.id.toString(),
            title: reservation.title,
            start: reservation.datestart,
            end: reservation.dateend,
            classNames: [className], // Assign a valid class
          };
        });
  
        // Update the calendar's events
        this.calendarOptions.events = events;
      },
      (error) => {
        console.error('Error loading reservations:', error);
      }
    );
  }  

  getEventClassNames(arg: any): string[] {
    return arg.event.classNames;
  }
}