import {Routes} from '@angular/router';
import { ZalbaCutanjeFormComponent } from '../components/forms/zalba-cutanje-form/zalba-cutanje-form.component';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {SignUpComponent} from '../components/sign-up/sign-up.component';

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
    path: 'zalba-cutanje',
    component: ZalbaCutanjeFormComponent
  }
];
