import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/authService';
import { User } from '../../model/user';
import { Role } from '../../enum/role';
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.less'],
  providers: [AuthService]
})
export class RegistrationComponent implements OnInit {
  private passwordHasError: boolean;
  private emailHasError: boolean;
  private nameHasError: boolean;

  constructor(private authService: AuthService, private router: Router) {
    this.passwordHasError = false;
    this.emailHasError = false;
    this.nameHasError = false;
  }

  ngOnInit() {
  }

  registration(userName, email, password, rePassword) {
    if (!userName.length) {
      this.nameHasError = true;
      this.passwordHasError = false;
      this.emailHasError = false;
      return;
    } else if (!email.length) {
      this.passwordHasError = false;
      this.emailHasError = true;
      this.nameHasError = false;
      return;
    } else if (!password.length || password !== rePassword) {
      this.passwordHasError = true;
      this.emailHasError = false;
      this.nameHasError = false;
      return;
    }

    let user = new User();
    user.email = email;
    user.role = Role.DEFAULT;
    user.password = password;
    user.userName = userName;

    this.passwordHasError = false;
    this.emailHasError = false;
    this.nameHasError = false;
    this.authService.registration(user).subscribe((user) => {
      this.router.navigate(["/login"])
    })
  }
}
