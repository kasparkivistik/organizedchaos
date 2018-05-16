import {Event} from './event';
import {HttpClient, json} from 'aurelia-fetch-client';

let client = new HttpClient();

export class events {

  constructor() {
    this.events = [];
    this.eventName = '';
    this.eventDescription = '';
    this.startDate = undefined;
    this.endDate = undefined;
    this.editableEventId = undefined;
    this.editableEvent = undefined;

    this.getEvents();
  }

  addEvent() {
    if (this.eventDescription && this.eventName) {
      const event = new Event(this.eventName, this.eventDescription, this.startDate, this.endDate);
      client.fetch('http://localhost:8080/events/save', {
        method: "POST",
        headers: {
          'Authorization': sessionStorage.getItem("token"),
          'Content-Type': 'application/json'
        },
        body: json(event)
      }).then(response => {
        console.log("event added", response.json());
        this.getEvents();
      });
      this.eventName = '';
      this.eventDescription = '';
    }
  }

  getEvents() {
    client.fetch('http//localhost:8080/events', {
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
      method: "GET"
    })
      .then(response => response.json())
      .then(events => {
        this.events = events;
        console.log("events loaded", this.events);
      })
  }

  removeEvent(id) {
    client.fetch('http://localhost:8080/events/' +id, {
      method: "DELETE",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
    }).then(() => {
      console.log("event deleted");
      this.getEvents();
    });
  }

  editEvent(event) {
    this.editableEventId = event.id;
    this.editableEvent = new Calendar(event.name, event.notes);
  }

  cancelEdit(event) {
    this.editableEventId = undefined;
  }

  saveEvent() {
    this.editableEventId.id = this.editableEventId;
    client.fetch('http://localhost:8080/events/save', {
      method: "POST",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
      body: json(this.editableEvent)
    }).then(response => {
      console.log("event saved", response.json());
      this.getEvents();
      this.cancelEdit();
    })
  }
}
