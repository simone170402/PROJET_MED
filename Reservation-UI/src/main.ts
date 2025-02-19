import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { FranceConnectService } from './app/auth/france-connect.service';
import { registerLocaleData } from '@angular/common';
import * as fr from '@angular/common/locales/fr';

registerLocaleData(fr.default);

const config = {
  ...appConfig,
  providers: [
    ...appConfig.providers || [],
    provideHttpClient(),
    FranceConnectService
  ]
};

bootstrapApplication(AppComponent, config)
  .catch(err => console.error(err));
