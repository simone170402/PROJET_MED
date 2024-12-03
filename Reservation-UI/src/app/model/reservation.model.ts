export class Reservation {
    id: number;
    reservationStatus: string;
    patient: Patient;
    centre: Centre;
    dateReservation: Date;
    doctorId: number;
    title: string;
    dateStart: string;
    dateEnd: string;

    constructor(id: number, reservationStatus: string, patient: Patient, centre: Centre, dateReservation: Date, doctorId: number, title: string, dateStart: string, dateEnd: string) {
        this.id = id;
        this.reservationStatus = reservationStatus;
        this.patient = patient;
        this.centre = centre;
        this.dateReservation = dateReservation;
        this.doctorId = doctorId;
        this.title = title;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}

export class Patient {
    // Define properties and methods for Patient class
}

export class Centre {
    // Define properties and methods for Centre class
}