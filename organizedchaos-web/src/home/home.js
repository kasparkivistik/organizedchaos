import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import $ from "jquery";
import 'fullcalendar';
import {Note} from "../notes/note";
import moment from "moment";

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
        response.json();
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
      this.getNotes();
    });
  }

  attached() {
    $('#calendar').fullCalendar({
      defaultView: 'listWeek',
      header:{
        right: "next",
        center: "title",
        left: "addEventButton, prev"
      },
      firstDay:1,
      weekNumbers:1,
      customButtons: {
        addEventButton: {
          text: 'Add event',
          click: function () {
            let dateStr = prompt('Enter a date in YYYY-MM-DD format');
            let date = moment(dateStr);
            let titleStr = prompt('What\`s the event?');
            let title = titleStr.toUpperCase();

            if (date.isValid()) {
              $('#calendar').fullCalendar('renderEvent', {
                title: title,
                start: date,
                allDay: true,
                textColor: 'white'
              });
              alert('Great.');
            } else {
              alert('Invalid date.');
            }
          }
        }
      },
      eventSources: [{
        url: environment.url + 'api/events',
        type: 'GET',
        headers: {
          'Authorization': sessionStorage.getItem('token')
        },
        events: 'json',
        success: function (response) {
          console.log(response);
        },
        error: function (response) {
          alert('wrong');
        }
      }
      ],
    })
  }
}
