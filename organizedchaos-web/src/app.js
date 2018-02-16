import {LogManager} from 'aurelia-framework';

export class App {

  constructor() {
    this.logger = LogManager.getLogger('app');
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = 'Chaos organized';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index' },
      { route: 'users',            name: 'users',      moduleId: 'users/index', nav: true, title: 'Users' },
    ]);
  }
}
