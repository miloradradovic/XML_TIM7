import {Routes} from '@angular/router';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {SignInGuard} from '../guards/sign-in-guard.service';
import {SignUpComponent} from '../components/sign-up/sign-up.component';
import { ZahtevFormComponent } from '../components/forms/zahtev-form/zahtev-form.component';
import { ObavestenjeFormComponent } from '../components/forms/obavestenje-form/obavestenje-form.component';
import {IzjasnjavanjaComponent} from '../components/organ-vlasti/izjasnjavanja/izjasnjavanja.component';
import {NeprocitanaResenjaComponent} from '../components/organ-vlasti/neprocitana-resenja/neprocitana-resenja.component';
import {ProcitanaResenjaComponent} from '../components/organ-vlasti/procitana-resenja/procitana-resenja.component';
import {ObavestenjaComponent} from '../components/organ-vlasti/obavestenja/obavestenja.component';
import {OrganVlastiMainPageComponent} from '../components/organ-vlasti/organ-vlasti-main-page/organ-vlasti-main-page.component';
import {ZahteviComponent} from '../components/organ-vlasti/zahtevi/zahtevi.component';
import {GradjaninMainPageComponent} from '../components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import {KreiranjeIzvestajaComponent} from '../components/organ-vlasti/kreiranje-izvestaja/kreiranje-izvestaja.component';
import {IzvestajiComponent} from '../components/organ-vlasti/izvestaji/izvestaji.component';
import {DetaljniPrikazZahtevaComponent} from '../components/organ-vlasti/detaljni-prikaz-zahteva/detaljni-prikaz-zahteva.component';
import {DetaljniPrikazIzvestajaComponent} from '../components/organ-vlasti/detaljni-prikaz-izvestaja/detaljni-prikaz-izvestaja.component';
import {DetaljniPrikazObavestenjaComponent} from '../components/organ-vlasti/detaljni-prikaz-obavestenja/detaljni-prikaz-obavestenja.component';
import {DetaljniPrikazResenjaComponent} from '../components/organ-vlasti/detaljni-prikaz-resenja/detaljni-prikaz-resenja.component';
import {DetaljniPrikazZalbeComponent} from '../components/organ-vlasti/detaljni-prikaz-zalbe/detaljni-prikaz-zalbe.component';
import {AddOrganVlastiFormComponent} from '../components/forms/add-organ-vlasti-form/add-organ-vlasti-form.component';
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
    path: 'zahtev',
    component: ZahtevFormComponent
  },
  {
    path: 'obavestenje',
    component: ObavestenjeFormComponent
  },
  {
    path: 'izjasnjavanja',
    component: IzjasnjavanjaComponent
  },
  {
    path: 'neprocitana-resenja',
    component: NeprocitanaResenjaComponent
  },
  {
    path: 'procitana-resenja',
    component: ProcitanaResenjaComponent
  },
  {
    path: 'obavestenja',
    component: ObavestenjaComponent
  },
  {
    path: 'pocetna-stranica-organ-vlasti',
    component: OrganVlastiMainPageComponent
  },
  {
    path: 'zahtevi',
    component: ZahteviComponent
  },
  {
    path: 'pocetna-stranica-gradjanin',
    component: GradjaninMainPageComponent
  },
  {
    path: 'kreiraj-izvestaj',
    component: KreiranjeIzvestajaComponent
  },
  {
    path: 'izvestaji',
    component: IzvestajiComponent
  },
  {
    path: 'detaljni-prikaz-zahteva',
    component: DetaljniPrikazZahtevaComponent
  },
  {
    path: 'detaljni-prikaz-izvestaja',
    component: DetaljniPrikazIzvestajaComponent
  },
  {
    path: 'detaljni-prikaz-obavestenja',
    component: DetaljniPrikazObavestenjaComponent
  },
  {
    path: 'detaljni-prikaz-resenja',
    component: DetaljniPrikazResenjaComponent
  },
  {
    path: 'detaljni-prikaz-zalbe',
    component: DetaljniPrikazZalbeComponent
  },
  {
    path: 'dodaj-sluzbenika',
    component: AddOrganVlastiFormComponent
  },
  {
    path: 'profil',
    component: EditProfileComponent
  }
];
