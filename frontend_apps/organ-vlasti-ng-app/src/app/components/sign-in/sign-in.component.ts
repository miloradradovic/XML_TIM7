import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SignInService} from '../../services/sign-in-service/sign-in.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../model/sign-in.model';
import {Router} from '@angular/router';
import {StorageService} from '../../services/stogare-service/storage.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  form: FormGroup;
  error = '';
  constructor(
    private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private signInService: SignInService,
    public router: Router,
    private storageService: StorageService
  ) {
    this.form = this.fb.group({
      email : [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const auth: any = {};
    auth.email = this.form.value.email;
    auth.password = this.form.value.password;
    const signInUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      user: { _attributes:
          { xmlns: 'http://user',
            'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance',
            'xsi:schemaLocation': 'http://user ../xsd/user.xsd' },
        email: { _text: '' }, password: { _text: '' } } };
    signInUser.user.email = auth.email;
    signInUser.user.password = auth.password;

    // @ts-ignore
    const convert = require('xml-js');
    const signInUserXML = convert.js2xml(signInUser, {compact: true, ignoreComment: true, spaces: 4});

    this.signInService.signIn(signInUserXML).subscribe(
      result => {
        const userToken = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const jwt: JwtHelperService = new JwtHelperService();
        const info = jwt.decodeToken(userToken.token.jwt._text);
        const email = info.email;
        const role = info.role;
        const user = new SignInModel(email, userToken.token.jwt._text, role);
        console.log(user);
        this.storageService.setStorageItem('user', JSON.stringify(user));
        this.snackBar.open('Успешна пријава!', 'Ок', { duration: 2000 });
        if (role === 'ROLE_USER'){
          this.router.navigate(['/pocetna-stranica-gradjanin']);
        }else{
          this.router.navigate(['/pocetna-stranica-organ-vlasti']);
        }
      },
      error => {
        this.snackBar.open('Лоши креденцијали!', 'Ok', { duration: 2000 });
      }
    );
  }
}
