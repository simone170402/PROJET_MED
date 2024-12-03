export class Utilisateur {
    id: number;
    name: string;
    surname: string;
    phoneNumber: string;
    email: string;
    password: string;

    constructor(id: number, name: string, surname: string, phoneNumber: string, email: string, password: string) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}