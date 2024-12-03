export class Centre {
    id: number;
    name: string;
    city: string;
    address: string;
    phoneNumber: string;

    constructor(id: number, name: string, city: string, address: string, phoneNumber: string) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}