import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Patient } from '../model/patient.model';

@Injectable({
    providedIn: 'root'
})
export class PatientService {
    private apiUrl = '/api/patients';

    constructor(private http: HttpClient) {}

    getAllPatients(): Observable<Patient[]> {
        return this.http.get<Patient[]>(`${this.apiUrl}`)
            .pipe(catchError(this.handleError));
    }

    getPatientById(id: number): Observable<Patient> {
        return this.http.get<Patient>(`${this.apiUrl}/${id}`)
            .pipe(catchError(this.handleError));
    }

    createPatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>(`${this.apiUrl}`, patient)
            .pipe(catchError(this.handleError));
    }

    updatePatient(id: number, patient: Patient): Observable<Patient> {
        return this.http.put<Patient>(`${this.apiUrl}/${id}`, patient)
            .pipe(catchError(this.handleError));
    }

    deletePatient(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`)
            .pipe(catchError(this.handleError));
    }

    private handleError(error: any) {
        console.error('An error occurred', error);
        return throwError(error.message || 'Server error');
    }
}