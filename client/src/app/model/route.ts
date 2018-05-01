import { Role } from "../enum/role";

export class Route {
  public title: string;
  public paths: Array<string>;
  public component: any;
  public level: Role
}