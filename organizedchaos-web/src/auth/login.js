import {HttpClient, json} from "aurelia-fetch-client";
import {Router} from 'aurelia-router';
import {inject} from 'aurelia-framework';


let client = new HttpClient();
@inject(Router)
export class login {

  constructor(router){
    this.email = "";
    this.password = "";
    this.router = router;
  }

  login() {
    client.fetch('http://localhost:8080/getToken', {
      'method': "POST",
      'body': json({email: this.email, password: this.password})
    }).then(response => response.json()).then(response => {
      const token = response.token;
      sessionStorage.setItem('token', token);
      this.router.navigate("/notes/notes");
    });
  }
}
