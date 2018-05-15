let loggedIn;

export class navbar {
  constructor() {
    this.isAuthenticated();
  }


  isAuthenticated() {
    if (sessionStorage.getItem('token')) {
      console.log("logged in");
      this.loggedIn = true;
    } else {
      console.log("logged out");
      this.loggedIn = false;
    }
  }

  getOut() {
    sessionStorage.clear();
    location.reload();
  }
}
