import {LogManager} from 'aurelia-framework';

export class App {

  constructor() {
    this.logger = LogManager.getLogger('app');
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = 'Chaos organized';
    config.map([
      {route: ['', 'home'], name: 'home', moduleId: 'home/home'},
      {route: 'auth/login', name: 'login', moduleId: 'auth/login', title: 'Log in', nav: true},
      {route: 'auth/signup', name: 'signup', moduleId: 'auth/signup', title: 'Sign up', nav: true},
    ]);
  }
}
