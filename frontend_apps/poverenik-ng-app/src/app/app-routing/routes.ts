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
import {SignInGuard} from '../guards/sign-in.guard';
import {PoverenikGuard} from '../guards/poverenik.guard';
import {GradjaninGuard} from '../guards/gradjanin.guard';

export const routes: Routes = [
  {
    path: '',
    component: SignInComponent,
    canActivate: [SignInGuard]
  },
  {
    path: 'registracija',
    component: SignUpComponent,
    canActivate: [SignInGuard]
  },
  {
    path: 'zalba-cutanje',
    component: ZalbaCutanjeFormComponent,
    canActivate: [GradjaninGuard]
  },
  {
    path: 'zalba-odluka',
    component: ZalbaOdlukaFormComponent,
    canActivate: [GradjaninGuard]
  },
  {
    path: 'resenje',
    component: ResenjeFormComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'pocetna-stranica-gradjanin',
    component: GradjaninMainPageComponent,
    canActivate: [GradjaninGuard]
  },
  {
    path: 'pocetna-stranica-poverenik',
    component: PoverenikMainPageComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'zalbe',
    component: PoverenikSveZalbeComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'izjasnjavanja',
    component: PoverenikIzjasnjavanjaComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'resenja',
    component: PoverenikSvaResenjaComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'dodaj-poverenika',
    component: AddPoverenikFormComponent,
    canActivate: [PoverenikGuard]
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
    component: PoverenikNeprocitaniIzvestajiComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'procitani-izvestaji',
    component: PoverenikProcitaniIzvestajiComponent,
    canActivate: [PoverenikGuard]
  },
  {
    path: 'gradjanin/profil',
    component: EditProfileComponent,
    canActivate: [GradjaninGuard]
  },
  {
    path: 'poverenik/profil',
    component: EditProfileComponent,
    canActivate: [PoverenikGuard]
  }

];
