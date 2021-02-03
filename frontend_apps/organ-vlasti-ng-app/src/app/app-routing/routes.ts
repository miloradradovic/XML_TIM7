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
    path: 'main-page-organ_vlasti',
    component: OrganVlastiMainPageComponent
  },
  {
    path: 'zahtevi',
    component: ZahteviComponent
  },
  {
    path: 'main-page-gradjanin',
    component: GradjaninMainPageComponent
  },
  {
    path: 'kreiraj-izvestaj',
    component: KreiranjeIzvestajaComponent
  }
];
