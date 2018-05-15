import {Note} from './note';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import moment from 'moment';

let client = new HttpClient();
moment.locale('et');

export class notes {

  constructor() {
    this.notes = [];
    this.noteContent = '';
    this.noteDate = '';
    this.editableNoteId = undefined;
    this.editableNote = undefined;

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

  removeNote(id) {
    client.fetch(environment.url + 'api/notes/' + id, {
      method: "DELETE",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
    }).then(() => {
      console.log("note deleted");
      this.getNotes();
    });
  }

  editNote(note) {
    this.editableNoteId = note.id;
    this.editableNote = new Note(note.date, note.content);
  }

  cancelEdit() {
    this.editableNoteId = undefined;
  }

  saveNote() {
    this.editableNote.id = this.editableNoteId;
    client.fetch(environment.url + 'api/notes/save', {
      method: "POST",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
      body: json(this.editableNote)
    }).then(response => {
      console.log("note saved");
      this.getNotes();
      this.cancelEdit();
    });
  }

  setComplete(id, isComplete) {
    client.fetch(environment.url + 'api/notes/setComplete?id=' + id + '&complete=' + isComplete, {
      method: "POST",
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Content-Type': 'application/json'
      },
    }).then(response => {
      console.log("note updated");
      this.getNotes();
    });
  }


}
