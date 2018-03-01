import {Note} from './note';
import {HttpClient, json} from 'aurelia-fetch-client';

let note;

export class home {

  constructor() {
    this.notes = [];
    this.noteDescription = '';
    this.noteHeader = '';
  }

  addNote() {



    note = new Note(this.noteHeader, this.noteDescription);
    if (this.noteDescription && this.noteHeader) {
      this.notes.push(note);
      this.noteDescription = '';
      this.noteHeader = '';
    }
    return note;
  }

  removeNote(note) {
    let index = this.notes.indexOf(note);
    if (index !== -1) {
      this.notes.splice(index, 1);
    }
  }
}
