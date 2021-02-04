import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {MatDividerModule} from '@angular/material/divider';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {RouterModule} from '@angular/router';
import {routes} from './app-routing/routes';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {HttpAuthInterceptor} from './interceptors/http-auth.interceptor';
import { NavigationComponent } from './navigation/navigation.component';
import { NavigationNonSignedInComponent } from './navigation/navigation-non-signed-in/navigation-non-signed-in.component';
import { NavigationGradjaninComponent } from './navigation/navigation-gradjanin/navigation-gradjanin.component';
import { NavigationOrganVlastiComponent } from './navigation/navigation-organ-vlasti/navigation-organ-vlasti.component';
import { ZahtevFormComponent } from './components/forms/zahtev-form/zahtev-form.component';
import { ObavestenjeFormComponent } from './components/forms/obavestenje-form/obavestenje-form.component';
import { TableComponent } from './components/table/table.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { GradjaninMainPageComponent } from './components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import { OrganVlastiMainPageComponent } from './components/organ-vlasti/organ-vlasti-main-page/organ-vlasti-main-page.component';
import { ZahteviComponent } from './components/organ-vlasti/zahtevi/zahtevi.component';
import { ObavestenjaComponent } from './components/organ-vlasti/obavestenja/obavestenja.component';
import { ProcitanaResenjaComponent } from './components/organ-vlasti/procitana-resenja/procitana-resenja.component';
import { NeprocitanaResenjaComponent } from './components/organ-vlasti/neprocitana-resenja/neprocitana-resenja.component';
import { IzvestajiComponent } from './components/organ-vlasti/izvestaji/izvestaji.component';
import { IzjasnjavanjaComponent } from './components/organ-vlasti/izjasnjavanja/izjasnjavanja.component';
import {MatTableModule} from '@angular/material/table';
import { KreiranjeIzvestajaComponent } from './components/organ-vlasti/kreiranje-izvestaja/kreiranje-izvestaja.component';
import { IzjasnjavanjeDialogComponent } from './components/organ-vlasti/izjasnjavanja/izjasnjavanje-dialog/izjasnjavanje-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';
import { DetaljniPrikazZahtevaComponent } from './components/organ-vlasti/detaljni-prikaz-zahteva/detaljni-prikaz-zahteva.component';
import { DetaljniPrikazObavestenjaComponent } from './components/organ-vlasti/detaljni-prikaz-obavestenja/detaljni-prikaz-obavestenja.component';
import { DetaljniPrikazIzvestajaComponent } from './components/organ-vlasti/detaljni-prikaz-izvestaja/detaljni-prikaz-izvestaja.component';
import { DetaljniPrikazResenjaComponent } from './components/organ-vlasti/detaljni-prikaz-resenja/detaljni-prikaz-resenja.component';
import { DetaljniPrikazZalbeComponent } from './components/organ-vlasti/detaljni-prikaz-zalbe/detaljni-prikaz-zalbe.component';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    NavigationComponent,
    NavigationNonSignedInComponent,
    NavigationGradjaninComponent,
    NavigationOrganVlastiComponent,
    ZahtevFormComponent,
    ObavestenjeFormComponent,
    TableComponent,
    GradjaninMainPageComponent,
    OrganVlastiMainPageComponent,
    ZahteviComponent,
    ObavestenjaComponent,
    ProcitanaResenjaComponent,
    NeprocitanaResenjaComponent,
    IzvestajiComponent,
    IzjasnjavanjaComponent,
    KreiranjeIzvestajaComponent,
    IzjasnjavanjeDialogComponent,
    DetaljniPrikazZahtevaComponent,
    DetaljniPrikazObavestenjaComponent,
    DetaljniPrikazIzvestajaComponent,
    DetaljniPrikazResenjaComponent,
    DetaljniPrikazZalbeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatDividerModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    FormsModule,
    MatDialogModule,
    MatDatepickerModule,
    MatSelectModule,
    MatNativeDateModule,
    MatMenuModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
