import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user/user';
import { UserService } from '../user/user.service';
import { Role } from '../role/role';
import { RoleService } from '../role/role.service';
import { HttpErrorResponse } from '@angular/common/http';
export function matchPasswordValidator(g: FormGroup) {
  const password = g.get('password')?.value;
  const verifpassword = g.get('verifpassword')?.value;

  if (password !== verifpassword) {
    return { mismatch: true };
  } else {
    return null;
  }
  
}
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user: User = new User();
  passwordForm: FormGroup;
  roles: Role[] = [];
  selectedRole: Role;

  constructor(
    private userService: UserService,
    private route: Router,
    private fb: FormBuilder,
    private roleService: RoleService
  ) {
    this.passwordForm = this.fb.group(
      {
        password: ['', Validators.required],
        verifpassword: ['', Validators.required],
        roles: [this.selectedRole, Validators.required] // Set selectedRole as initial value
      },
      { validator: matchPasswordValidator }
    );
  }

  ngOnInit(): void {
    this.getRoles();
  }

  getRoles(): void {
    this.roleService.getRoles().subscribe(
      (response: Role[]) => {
        this.roles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  saveUser() {
    console.log(this.selectedRole);
    this.user = {
      idUser: 0,
      username: this.user.username,
      mail: this.user.mail,
      password: this.user.password,
      verifPassword: this.user.verifPassword,
      roles: this.selectedRole,
      roleName: "Default"
    };
    this.userService.addUsers(this.user).subscribe();
    this.route.navigateByUrl('/signin');
  }
}
