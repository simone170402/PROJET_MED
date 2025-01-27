import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  template: `
    <div class="patients-container">
      <div class="patients-header">
        <h2>Gestion des Patients</h2>
        <div class="search-box">
          <input 
            type="text" 
            [(ngModel)]="searchTerm" 
            (input)="onSearch()"
            placeholder="Rechercher un patient..."
            class="search-input"
          >
        </div>
      </div>

      <!-- Liste des patients -->
      <div class="patients-list">
        <table>
          <thead>
            <tr>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Dernière visite</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let patient of filteredPatients">
              <td>{{ patient.lastName }}</td>
              <td>{{ patient.firstName }}</td>
              <td>{{ patient.email }}</td>
              <td>{{ patient.phone }}</td>
              <td>{{ patient.lastVisit | date }}</td>
              <td class="actions">
                <button class="btn-view" (click)="viewPatient(patient)">Voir</button>
                <button class="btn-edit" (click)="editPatient(patient)">Modifier</button>
                <button class="btn-delete" (click)="deletePatient(patient.id)">Supprimer</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Pagination -->
      <div class="pagination">
        <button 
          [disabled]="currentPage === 1"
          (click)="changePage(currentPage - 1)"
          class="btn-page"
        >
          Précédent
        </button>
        <span class="page-info">Page {{ currentPage }} sur {{ totalPages }}</span>
        <button 
          [disabled]="currentPage === totalPages"
          (click)="changePage(currentPage + 1)"
          class="btn-page"
        >
          Suivant
        </button>
      </div>
    </div>
  `,
  styles: [`
    .patients-container {
      padding: 2rem;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .patients-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 2rem;
    }

    .search-box {
      width: 300px;
    }

    .search-input {
      width: 100%;
      padding: 0.5rem;
      border: 1px solid #ced4da;
      border-radius: 4px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 1rem;
    }

    th, td {
      padding: 0.75rem;
      text-align: left;
      border-bottom: 1px solid #dee2e6;
    }

    th {
      background-color: #f8f9fa;
      font-weight: 600;
    }

    .actions {
      display: flex;
      gap: 0.5rem;
    }

    .btn-view {
      background-color: #17a2b8;
      color: white;
      border: none;
      padding: 0.25rem 0.5rem;
      border-radius: 4px;
      cursor: pointer;
    }

    .btn-edit {
      background-color: #ffc107;
      color: #212529;
      border: none;
      padding: 0.25rem 0.5rem;
      border-radius: 4px;
      cursor: pointer;
    }

    .btn-delete {
      background-color: #dc3545;
      color: white;
      border: none;
      padding: 0.25rem 0.5rem;
      border-radius: 4px;
      cursor: pointer;
    }

    .pagination {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 1rem;
      margin-top: 2rem;
    }

    .btn-page {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 0.5rem 1rem;
      border-radius: 4px;
      cursor: pointer;
    }

    .btn-page:disabled {
      background-color: #6c757d;
      cursor: not-allowed;
    }

    .page-info {
      color: #6c757d;
    }
  `]
})
export class PatientsComponent implements OnInit {
  patients: any[] = [];
  filteredPatients: any[] = [];
  searchTerm: string = '';
  currentPage: number = 1;
  totalPages: number = 1;
  itemsPerPage: number = 10;

  ngOnInit() {
    // TODO: Charger la liste des patients depuis le backend
    this.patients = [
      {
        id: 1,
        firstName: 'Marie',
        lastName: 'Martin',
        email: 'marie.martin@example.com',
        phone: '0123456789',
        lastVisit: new Date('2024-01-15')
      }
    ];
    this.filteredPatients = this.patients;
    this.calculateTotalPages();
  }

  onSearch() {
    if (!this.searchTerm.trim()) {
      this.filteredPatients = this.patients;
    } else {
      const search = this.searchTerm.toLowerCase();
      this.filteredPatients = this.patients.filter(patient => 
        patient.firstName.toLowerCase().includes(search) ||
        patient.lastName.toLowerCase().includes(search) ||
        patient.email.toLowerCase().includes(search)
      );
    }
    this.currentPage = 1;
    this.calculateTotalPages();
  }

  calculateTotalPages() {
    this.totalPages = Math.ceil(this.filteredPatients.length / this.itemsPerPage);
  }

  changePage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }

  viewPatient(patient: any) {
    // TODO: Implémenter la vue détaillée
    console.log('Voir le patient:', patient);
  }

  editPatient(patient: any) {
    // TODO: Implémenter la modification
    console.log('Modifier le patient:', patient);
  }

  deletePatient(id: number) {
    // TODO: Implémenter la suppression
    console.log('Supprimer le patient:', id);
  }
}
