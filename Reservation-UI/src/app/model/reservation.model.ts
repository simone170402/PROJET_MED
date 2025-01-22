import { Patient } from './patient.model';
import { Centre } from './centre.model';
export class Reservation {
    id ?: number;
    reservationStatus?: string = 'Disponible';
    patient?: Patient;
    centre?: Centre;
    dateReservation?: Date;
    doctorId?: number;
    title?: string;
    dateStart?: string;
    dateEnd?: string;

}





