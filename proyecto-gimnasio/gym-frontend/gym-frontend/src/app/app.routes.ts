import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SocioListComponent } from './socios/socio-list/socio-list.component';
import { SocioFormComponent } from './socios/socio-form/socio-form.component';
import { AppShellComponent } from './shared/app-shell/app-shell.component';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: AppShellComponent,
    canActivate: [authGuard],
    children: [
      { path: 'socios', component: SocioListComponent },
      { path: 'socios/nuevo', component: SocioFormComponent },
      { path: 'socios/editar/:id', component: SocioFormComponent }
    ]
  },
  { path: '**', redirectTo: 'login' }
];
