import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {MatDividerModule} from '@angular/material/divider';
import {ReactiveFormsModule} from '@angular/forms';
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
import { TableComponent } from './components/table/table.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { GradjaninMainPageComponent } from './components/gradjanin/gradjanin-main-page/gradjanin-main-page.component';
import { SluzbenikMainPageComponent } from './components/organ-vlasti/sluzbenik-main-page/sluzbenik-main-page.component';
import { OrganVlastiMainPageComponent } from './components/organ-vlasti/organ-vlasti-main-page/organ-vlasti-main-page.component';
import { ZahteviComponent } from './components/organ-vlasti/zahtevi/zahtevi.component';
import { ObavestenjaComponent } from './components/organ-vlasti/obavestenja/obavestenja.component';
import { ProcitanaResenjaComponent } from './components/organ-vlasti/procitana-resenja/procitana-resenja.component';
import { NeprocitanaResenjaComponent } from './components/organ-vlasti/neprocitana-resenja/neprocitana-resenja.component';
import { IzvestajiComponent } from './components/organ-vlasti/izvestaji/izvestaji.component';
import { IzjasnjavanjaComponent } from './components/organ-vlasti/izjasnjavanja/izjasnjavanja.component';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    NavigationComponent,
    NavigationNonSignedInComponent,
    NavigationGradjaninComponent,
    NavigationOrganVlastiComponent,
    TableComponent,
    GradjaninMainPageComponent,
    SluzbenikMainPageComponent,
    OrganVlastiMainPageComponent,
    ZahteviComponent,
    ObavestenjaComponent,
    ProcitanaResenjaComponent,
    NeprocitanaResenjaComponent,
    IzvestajiComponent,
    IzjasnjavanjaComponent
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
    MatTableModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
