import {LogManager} from 'aurelia-framework';
import {AuthService} from "./AuthService";

export class App {

  constructor() {
    this.logger = LogManager.getLogger('app');
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = 'Chaos organized';
    config.map([
      {route: ['', 'home'], name: 'home', moduleId: 'home/home'},
      {route: 'cal/calendar', name: 'calendar', moduleId: 'cal/calendar', title: 'Calendar', nav: true},
      {route: 'auth/login', name: 'login', moduleId: 'auth/login', title: 'Log in', nav: true},
      {route: 'auth/signup', name: 'signup', moduleId: 'auth/signup', title: 'Sign up', nav: true},
    ]);
  }
}
