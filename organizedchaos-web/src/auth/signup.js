import {HttpClient, json} from "aurelia-fetch-client";

export class signup {

  constructor() {

  }

  userData = {};

  addUser() {
    let client = new HttpClient();

    if ($('#password').val() === $('#password-confirm').val()) {

      client.fetch('http://localhost:8080/users/add', {
        'method': "POST",
        'body': json(this.userData)
      })
        .then(response => response.json());
      window.location.replace("http://localhost:9000");

    } else {
      $('input#password').addClass('is-invalid');
      $('input#password-confirm').addClass('is-invalid');
      $('.password-check').html("Passwords don't match");
    }
  }
}
