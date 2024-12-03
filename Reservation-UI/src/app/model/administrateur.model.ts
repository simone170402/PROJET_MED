export class Administrateur {
    role: string;
    centres: Centre[];

    constructor(role: string, centres: Centre[]) {
        this.role = role;
        this.centres = centres;
    }
}

export class Centre {
    // Define properties of Centre class here
}