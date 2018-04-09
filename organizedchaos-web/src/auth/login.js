import {HttpClient, json} from "aurelia-fetch-client";

let client = new HttpClient();
export class login {

  constructor(){
    this.email = "";
    this.password = "";
  }

  login() {
    fetch('http://localhost:8080/login', {
      'method': "POST",
      'body': json({email: 'admin', password: 'password'})
    }).then(response => response).then(response => {
      console.log(response);
      console.log(response.header, response.headers);
    });
  }
}
