import { Route } from "../model/route";

export const getActiveRouteIndex = (routes: Array<Route>): Number => {
  let currentRoute = location.pathname.slice(1);
  for (let route of routes) {
    if (route.path === currentRoute) {
      return routes.indexOf(route);
    }
  }
  return 0;
};
