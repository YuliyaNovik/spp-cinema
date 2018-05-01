import { Component, OnInit } from "@angular/core";
import { AuthService } from "../../service/authService";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.less"],
  providers: [AuthService]
})
export class LoginComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {
    sessionStorage.clear();
  }

  ngOnInit() { }

  login(email, password) {
    this.authService.login(email, password, () => {
      this.router.navigate(["/"])
    });
  }
}
