import {Routes} from '@angular/router';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {SignInGuard} from '../guards/sign-in-guard.service';
import {SignUpComponent} from '../components/sign-up/sign-up.component';
import { ZahtevFormComponent } from '../components/forms/zahtev-form/zahtev-form.component';
import { ObavestenjeFormComponent } from '../components/forms/obavestenje-form/obavestenje-form.component';

export const routes: Routes = [
  {
    path: 'sign-in',
    component: SignInComponent
  },
  {
    path: 'sign-up',
    component: SignUpComponent
  },
  {
    path: 'zahtev',
    component: ZahtevFormComponent
  },
  {
    path: 'obavestenje',
    component: ObavestenjeFormComponent
  }
];
