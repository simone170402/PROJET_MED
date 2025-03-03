import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config'; // ✅ Assure-toi que ce fichier existe
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import * as fr from '@angular/common/locales/fr';

registerLocaleData(fr.default); // Assure-toi que cela est bien importé

bootstrapApplication(AppComponent, {
  ...appConfig,
  providers: [
    provideAnimations(),
    ...(appConfig.providers || [])
  ],
}).catch((err) => console.error(err));