import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import $ from "jquery";
import 'fullcalendar';
import {Note} from "../notes/note";

let client = new HttpClient();

export class home {

  constructor() {
    this.notes = [];
    this.noteContent = '';
    this.noteDate = '';

    this.getNotes();
  }

  addNote() {
    if (this.noteContent && this.noteDate) {
      const note = new Note(this.noteDate, this.noteContent);
      client.fetch(environment.url + 'api/notes/save', {
        method: "POST",
        headers: {
          'Authorization': sessionStorage.getItem("token"),
          'Content-Type': 'application/json'
        },
        body: json(note)
      }).then(response => {
        console.log("note added", response.json());
        this.getNotes();
      });
      this.noteContent = '';
      this.noteDate = '';
    }
  }

  getNotes() {
    client.fetch(environment.url + 'api/notes', {
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
      method: "GET"
    })
      .then(response => response.json())
      .then(notes => {
        this.notes = notes;
        console.log("notes loaded", this.notes);
      });
  }

  setIsComplete(id) {
    client.fetch(environment.url + 'api/notes/setComplete?id=' + id + '&complete=true', {
      method: "POST",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
    }).then(() => {
      console.log("note updated");
      this.getNotes();
    });
  }

  attached() {
    $('#calendar').fullCalendar({
      defaultView: 'listWeek',
      header:{
        right: "next",
        center: "title",
        left: "prev"
      },
      firstDay:1,
      weekNumbers:1,
      events: [
        {
          title  : 'org juhtimine jääb ära',
          start  : '2018-04-12'
        },
        {
          title  : 'tarkvaratehnika allnighterid',
          start  : '2018-04-13',
          end    : '2018-04-15'
        },
        {
          title  : 'Keit reisile',
          start  : '2018-04-26T12:30:00',
          allDay : false // will make the time show
        }
      ]
    })
  }
}
