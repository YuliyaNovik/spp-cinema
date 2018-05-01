import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/userService";
import {User} from "../../model/user";
import * as FileSaver from "file-saver";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.less'],
  providers: [UserService]
})
export class UsersComponent implements OnInit {
  private users: Array<User>;

  constructor(private userService: UserService) {
    this.userService.getAllUser().subscribe((users) => {
      this.users = users;
    })
  }

  updateFilter(name: any, role: any, email: any) {
    console.log();
    this.userService.getUserByFilter(
      name.value,
      role.value.split(",").map(item => item.replace(" ", "").toLowerCase()),
      email.value.split(",").map(item => item.replace(" ", "").toLowerCase())
    ).subscribe(users => {
      this.users = users;
    });
  }

  toDocument(type, name, role, email) {
    this.userService.exportToDocument(
      type,
      name.value,
      role.value.split(",").map(item => item.replace(" ", "").toLowerCase()),
      email.value.split(",").map(item => item.replace(" ", "").toLowerCase())
    ).subscribe((response: Blob) => {
      FileSaver.saveAs(response, "export" + (type === "csv" ? ".csv" : ""));
    });
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.id).subscribe((data) => {
      this.users = this.users.filter(item => item.id !== user.id);
    })
  }

  ngOnInit() {}
}
