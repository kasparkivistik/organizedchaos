import {HttpClient, json} from "aurelia-fetch-client";

export class signup {

  constructor(){

  }

  userData = {};

  addUser() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/users/add', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json());
    window.location.replace("http://localhost:9000")
    }
}
