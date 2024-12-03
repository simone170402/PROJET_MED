export class Medecin {
    name: string;
    surname: string;
    phoneNumber: string;
    email: string;
    centre: Centre;
    patients: Patient[];

    constructor(
        name: string,
        surname: string,
        phoneNumber: string,
        email: string,
        centre: Centre,
        patients: Patient[]
    ) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.centre = centre;
        this.patients = patients;
    }
}

export class Centre {
    // Define properties and methods for Centre class
}

export class Patient {
    // Define properties and methods for Patient class
}