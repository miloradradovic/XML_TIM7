import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AddPoverenikService } from 'src/app/services/add-poverenik/add-poverenik.service';

declare var require: any;

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  form: FormGroup;
  email;
  role;
  constructor(
    private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private addSluzbenikService: AddPoverenikService,
    public router: Router
  ) {
    this.form = this.fb.group({
      name : [null, Validators.required],
      surname: [null, Validators.required],
      password: [null, [Validators.minLength(8)]],
    });
  }

  ngOnInit(): void {
    console.log(JSON.parse(localStorage.getItem('user')).email.replace('.', '-'))
    this.addSluzbenikService.getUser(JSON.parse(localStorage.getItem('user')).email.replace('.', '-')).subscribe(
      result => {
        const convert = require('xml-js');
        let currentUser = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        this.email = JSON.parse(localStorage.getItem('user')).email;
        this.role = currentUser['u:user']['u:role']['_text'];
        this.form.patchValue({
          name: currentUser['u:user']['u:name']['_text'],
          surname: currentUser['u:user']['u:last_name']['_text'],
          password: ''
        });  
      },
      error => {
      }
    );
  }

  submit(): void {
    const signUpUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      user: { _attributes:
          { xmlns: 'http://user',
            'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance',
            'xsi:schemaLocation': 'http://user ../xsd/user.xsd' },
        name: { _text: ''}, last_name: { _text: ''},
        email: { _text: '' }, password: { _text: '' }, role: { _text: ''} } };
    signUpUser.user.name = this.form.value.name;
    signUpUser.user.last_name = this.form.value.surname;
    signUpUser.user.email = this.email;
    signUpUser.user.password = this.form.value.password;
    signUpUser.user.role._text = this.role;
    console.log(signUpUser)

    // @ts-ignore
    const convert = require('xml-js');
    const signUpUserXML = convert.js2xml(signUpUser, {compact: true, ignoreComment: true, spaces: 4});

    console.log(signUpUserXML)

    this.addSluzbenikService.editProfile(signUpUserXML).subscribe(
      result => {
        this.snackBar.open('Успешно измењен профил!', 'ОК', { duration: 2000 });
        if (this.role === 'ROLE_ORGAN_VLASTI') {
          this.router.navigate(['/pocetna-stranica-poverenik']);
        } else {
          this.router.navigate(['/pocetna-stranica-gradjanin']);
        }
      },
      error => {
        this.snackBar.open('Грешка!', 'ОК', { duration: 2000 });
      }
    );
  }

}
