// patient.model.ts
export interface Patient {
    id?: number;
    name: string;
    surname: string;
    email: string;
    phoneNumber?: string;
    dateOfBirth?: Date;
    vaccinationStatus?: string;
    // FranceConnect specific fields
    franceConnectId?: string;
    birthDate?: string;
    birthPlace?: string;
    gender?: string;
    // centre?: Centre; // Si nécessaire
  }