import {Routes} from '@angular/router';
import { ZalbaCutanjeFormComponent } from '../components/forms/zalba-cutanje-form/zalba-cutanje-form.component';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {SignUpComponent} from '../components/sign-up/sign-up.component';
import {GradjaninMainPageComponent} from '../components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import {PoverenikMainPageComponent} from '../components/poverenik/poverenik-main-page/poverenik-main-page.component';
import {PoverenikSveZalbeComponent} from '../components/poverenik/poverenik-sve-zalbe/poverenik-sve-zalbe.component';
import {PoverenikIzjasnjavanjaComponent} from '../components/poverenik/poverenik-izjasnjavanja/poverenik-izjasnjavanja.component';
import {PoverenikSvaResenjaComponent} from '../components/poverenik/poverenik-sva-resenja/poverenik-sva-resenja.component';
import {AddPoverenikFormComponent} from '../components/forms/add-poverenik-form/add-poverenik-form.component';
import { ResenjeFormComponent } from '../components/forms/resenje-form/resenje-form.component';
import { ZalbaOdlukaFormComponent } from '../components/forms/zalba-odluka-form/zalba-odluka-form.component';
import {PoverenikDetaljniPrikazZalbeComponent} from '../components/poverenik/poverenik-detaljni-prikaz-zalbe/poverenik-detaljni-prikaz-zalbe.component';
import {PoverenikNeprocitaniIzvestajiComponent} from '../components/poverenik/poverenik-neprocitani-izvestaji/poverenik-neprocitani-izvestaji.component';
import {PoverenikProcitaniIzvestajiComponent} from '../components/poverenik/poverenik-procitani-izvestaji/poverenik-procitani-izvestaji.component';
import {PoverenikDetaljniPrikazIzvestajaComponent} from '../components/poverenik/poverenik-detaljni-prikaz-izvestaja/poverenik-detaljni-prikaz-izvestaja.component';
import {PoverenikDetaljniPrikazObavestenjaComponent} from '../components/poverenik/poverenik-detaljni-prikaz-obavestenja/poverenik-detaljni-prikaz-obavestenja.component';
import {PoverenikDetaljniPrikazResenjaComponent} from '../components/poverenik/poverenik-detaljni-prikaz-resenja/poverenik-detaljni-prikaz-resenja.component';
import {PoverenikDetaljniPrikazZahtevaComponent} from '../components/poverenik/poverenik-detaljni-prikaz-zahteva/poverenik-detaljni-prikaz-zahteva.component';
import { EditProfileComponent } from '../components/edit-profile/edit-profile.component';

export const routes: Routes = [
  {
    path: 'prijava',
    component: SignInComponent
  },
  {
    path: 'registracija',
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
    path: 'pocetna-stranica-gradjanin',
    component: GradjaninMainPageComponent
  },
  {
    path: 'pocetna-stranica-poverenik',
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
    path: 'dodaj-poverenika',
    component: AddPoverenikFormComponent
  },
  {
    path: 'detaljni-prikaz-zalbe',
    component: PoverenikDetaljniPrikazZalbeComponent
  },
  {
    path: 'detaljni-prikaz-izvestaja',
    component: PoverenikDetaljniPrikazIzvestajaComponent
  },
  {
    path: 'detaljni-prikaz-obavestenja',
    component: PoverenikDetaljniPrikazObavestenjaComponent
  },
  {
    path: 'detaljni-prikaz-resenja',
    component: PoverenikDetaljniPrikazResenjaComponent
  },
  {
    path: 'detaljni-prikaz-zahteva',
    component: PoverenikDetaljniPrikazZahtevaComponent
  },
  {
    path: 'neprocitani-izvestaji',
    component: PoverenikNeprocitaniIzvestajiComponent
  },
  {
    path: 'procitani-izvestaji',
    component: PoverenikProcitaniIzvestajiComponent
  },
  {
    path: 'profil',
    component: EditProfileComponent
  }
];
