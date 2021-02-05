import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SignUpService} from '../../../services/sign-up-service/sign-up.service';
import {Router} from '@angular/router';
import {AddPoverenikService} from '../../../services/add-poverenik/add-poverenik.service';

@Component({
  selector: 'app-add-poverenik-form',
  templateUrl: './add-poverenik-form.component.html',
  styleUrls: ['./add-poverenik-form.component.css']
})
export class AddPoverenikFormComponent implements OnInit {

  form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private addPoverenikService: AddPoverenikService,
    public router: Router
  ) {
    this.form = this.fb.group({
      name : [null, Validators.required],
      surname: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.minLength(8)]],
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const user: any = {};
    user.name = this.form.value.name;
    user.surname = this.form.value.surname;
    user.email = this.form.value.email;
    user.password = this.form.value.password;
    const signUpUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      user: { _attributes:
          { xmlns: 'http://user',
            'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance',
            'xsi:schemaLocation': 'http://user ../xsd/user.xsd' },
        name: { _text: ''}, last_name: { _text: ''},
        email: { _text: '' }, password: { _text: '' }, role: { _text: ''} } };
    signUpUser.user.name = user.name;
    signUpUser.user.last_name = user.surname;
    signUpUser.user.email = user.email;
    signUpUser.user.password = user.password;
    signUpUser.user.role._text = 'ROLE_POVERENIK';

    // @ts-ignore
    const convert = require('xml-js');
    const signUpUserXML = convert.js2xml(signUpUser, {compact: true, ignoreComment: true, spaces: 4});

    this.addPoverenikService.addPoverenik(signUpUserXML).subscribe(
      result => {
        this.snackBar.open('Успешно додат повереник!', 'Ok', { duration: 2000 });
        this.router.navigate(['/pocetna-stranica-poverenik']);
      },
      error => {
        this.snackBar.open('Постојећа е-пошта!', 'Ok', { duration: 2000 });
      }
    );
  }

}
