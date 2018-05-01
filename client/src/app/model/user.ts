import { Role } from "../enum/role";

export class User {
  public id: number;
  public userName: string;
  public email: string;
  public password: string;
  public role: Role;
}
