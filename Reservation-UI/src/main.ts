import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { FranceConnectService } from './app/auth/france-connect.service';
import { appConfig } from './app/app.config'; // ✅ Assure-toi que ce fichier existe
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import * as fr from '@angular/common/locales/fr';

registerLocaleData(fr.default);

bootstrapApplication(AppComponent, {
  ...appConfig, // Garde la configuration existante
  providers: [
    provideAnimations(), // Ajoute les animations Angular Material
    provideRouter(routes), // Configure le routeur Angular
    ...(appConfig.providers || []), //  Garde les providers existants dans `appConfig`,
    provideHttpClient(),
    FranceConnectService
  ],
}).catch((err) => console.error(err));

