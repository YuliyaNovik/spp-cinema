import { Route } from "../model/route";

export const getActiveRouteIndex = (routes: Array<Route>): Number => {
  let currentRoute = location.pathname.slice(1);
  for (let route of routes) {
    for (let path of route.paths) {
      if (path === currentRoute) {
        return routes.indexOf(route);
      }
    }
  }
  return 0;
};
