import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {routes} from './app-routing/routes';
import {MatButtonModule} from '@angular/material/button';
import {HttpAuthInterceptor} from './interceptors/http.auth.interceptor';
import { ZalbaCutanjeFormComponent } from './components/forms/zalba-cutanje-form/zalba-cutanje-form.component';
import { NavigationComponent } from './navigation/navigation.component';
import { NavigationGradjaninComponent } from './navigation/navigation-gradjanin/navigation-gradjanin.component';
import { NavigationPoverenikComponent } from './navigation/navigation-poverenik/navigation-poverenik.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { GradjaninMainPageComponent } from './components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import { PoverenikMainPageComponent } from './components/poverenik/poverenik-main-page/poverenik-main-page.component';
import { NavigationNonSignedInComponent } from './navigation/navigation-non-signed-in/navigation-non-signed-in.component';
import { TableComponent } from './components/table/table.component';
import {MatColumnDef, MatTable, MatTableModule} from '@angular/material/table';
import { PoverenikSveZalbeComponent } from './components/poverenik/poverenik-sve-zalbe/poverenik-sve-zalbe.component';
import { PoverenikSvaResenjaComponent } from './components/poverenik/poverenik-sva-resenja/poverenik-sva-resenja.component';
import { PoverenikIzjasnjavanjaComponent } from './components/poverenik/poverenik-izjasnjavanja/poverenik-izjasnjavanja.component';
import { AddPoverenikFormComponent } from './components/forms/add-poverenik-form/add-poverenik-form.component';
import { ResenjeFormComponent } from './components/forms/resenje-form/resenje-form.component';
import { MatRadioModule } from '@angular/material/radio';
import { ZalbaOdlukaFormComponent } from './components/forms/zalba-odluka-form/zalba-odluka-form.component';
import { PoverenikDetaljniPrikazZalbeComponent } from './components/poverenik/poverenik-detaljni-prikaz-zalbe/poverenik-detaljni-prikaz-zalbe.component';
import { PoverenikProcitaniIzvestajiComponent } from './components/poverenik/poverenik-procitani-izvestaji/poverenik-procitani-izvestaji.component';
import { PoverenikNeprocitaniIzvestajiComponent } from './components/poverenik/poverenik-neprocitani-izvestaji/poverenik-neprocitani-izvestaji.component';
import { GradjaninPonistavanjeDialogComponent } from './components/gradjanin/gradjanin-main-page/gradjanin-ponistavanje-dialog/gradjanin-ponistavanje-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    ZalbaCutanjeFormComponent,
    NavigationComponent,
    NavigationGradjaninComponent,
    NavigationPoverenikComponent,
    GradjaninMainPageComponent,
    PoverenikMainPageComponent,
    NavigationNonSignedInComponent,
    TableComponent,
    PoverenikSveZalbeComponent,
    PoverenikSvaResenjaComponent,
    PoverenikIzjasnjavanjaComponent,
    AddPoverenikFormComponent,
    ResenjeFormComponent,
    ZalbaOdlukaFormComponent,
    PoverenikDetaljniPrikazZalbeComponent,
    PoverenikProcitaniIzvestajiComponent,
    PoverenikNeprocitaniIzvestajiComponent,
    GradjaninPonistavanjeDialogComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatDividerModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatTableModule,
    MatInputModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatRadioModule,
    MatDialogModule,
    FormsModule,
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
