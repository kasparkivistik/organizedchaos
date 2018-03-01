import {HttpClient, json} from "aurelia-fetch-client";

export class signup {

  constructor(){

  }

  userData = {};
  userList = [];

  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/users')
      .then(response => response.json())
      .then(users => this.userList = users);
  }

  addUser() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/users/add', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json());
    }
}
