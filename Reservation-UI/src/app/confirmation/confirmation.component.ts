import { Component } from '@angular/core';

@Component({
  selector: 'app-confirmation',
  standalone: true,
  imports: [],
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
  template: `
    <div class="confirmation-container">
      <h2>Réservation Confirmée</h2>
      <p>Votre réservation a été effectuée avec succès!</p>
    </div>
  `
})
export class ConfirmationComponent {}
