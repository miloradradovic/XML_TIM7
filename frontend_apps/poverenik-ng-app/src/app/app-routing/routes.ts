import {Routes} from '@angular/router';
import { ZalbaCutanjeFormComponent } from '../components/forms/zalba-cutanje-form/zalba-cutanje-form.component';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {SignUpComponent} from '../components/sign-up/sign-up.component';
import {GradjaninMainPageComponent} from '../components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import {PoverenikMainPageComponent} from '../components/poverenik/poverenik-main-page/poverenik-main-page.component';
import {PoverenikSveZalbeComponent} from '../components/poverenik/poverenik-sve-zalbe/poverenik-sve-zalbe.component';
import {PoverenikIzjasnjavanjaComponent} from '../components/poverenik/poverenik-izjasnjavanja/poverenik-izjasnjavanja.component';
import {PoverenikSvaResenjaComponent} from '../components/poverenik/poverenik-sva-resenja/poverenik-sva-resenja.component';
import {PoverenikSviIzvestajiComponent} from '../components/poverenik/poverenik-svi-izvestaji/poverenik-svi-izvestaji.component';
import {AddPoverenikFormComponent} from '../components/forms/add-poverenik-form/add-poverenik-form.component';
import { ResenjeFormComponent } from '../components/forms/resenje-form/resenje-form.component';
import { ZalbaOdlukaFormComponent } from '../components/forms/zalba-odluka-form/zalba-odluka-form.component';
import {PoverenikDetaljniPrikazZalbeComponent} from '../components/poverenik/poverenik-detaljni-prikaz-zalbe/poverenik-detaljni-prikaz-zalbe.component';

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
  },
  {
    path: 'zalba-odluka',
    component: ZalbaOdlukaFormComponent
  },
  {
    path: 'resenje',
    component: ResenjeFormComponent
  },
  {
    path: 'main-page-gradjanin',
    component: GradjaninMainPageComponent
  },
  {
    path: 'main-page-poverenik',
    component: PoverenikMainPageComponent
  },
  {
    path: 'zalbe',
    component: PoverenikSveZalbeComponent
  },
  {
    path: 'izjasnjavanja',
    component: PoverenikIzjasnjavanjaComponent
  },
  {
    path: 'resenja',
    component: PoverenikSvaResenjaComponent
  },
  {
    path: 'izvestaji',
    component: PoverenikSviIzvestajiComponent
  },
  {
    path: 'add-poverenik',
    component: AddPoverenikFormComponent
  },
  {
    path: 'detaljni-prikaz-zalbe',
    component: PoverenikDetaljniPrikazZalbeComponent
  }
];
