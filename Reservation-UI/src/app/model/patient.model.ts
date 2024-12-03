export class Patient {
    id: number;
    name: string;
    surname: string;
    phoneNumber: string;
    email: string;
    dateOfBirth: Date;
    vaccinationStatus: string;
    centre: Centre;

    constructor(
        id: number,
        name: string,
        surname: string,
        phoneNumber: string,
        email: string,
        dateOfBirth: Date,
        vaccinationStatus: string,
        centre: Centre
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.vaccinationStatus = vaccinationStatus;
        this.centre = centre;
    }
}

export class Centre {
    // Define properties and constructor for Centre class
}