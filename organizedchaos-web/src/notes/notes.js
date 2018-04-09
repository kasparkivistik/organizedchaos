import {Note} from './note';
import {HttpClient, json} from 'aurelia-fetch-client';

let client = new HttpClient();

export class notes {

  constructor() {
    this.notes = [];
    this.noteDescription = '';
    this.noteHeader = '';
    this.editableNoteId = undefined;
    this.editableNote = undefined;

    this.getNotes();
  }

  addNote() {
    if (this.noteDescription && this.noteHeader) {
      const note = new Note(this.noteHeader, this.noteDescription);
      client.fetch('http://localhost:8080/notes/save', {
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
      this.noteDescription = '';
      this.noteHeader = '';
    }
  }

  getNotes() {
    client.fetch('http://localhost:8080/notes', {
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
    client.fetch('http://localhost:8080/notes/' + id, {
      'method': "DELETE"
    }).then(() => {
      console.log("note deleted");
      this.getNotes();
    });
  }

  editNote(note) {
    this.editableNoteId = note.id;
    this.editableNote = new Note(note.name, note.content);
  }

  cancelEdit(){
    this.editableNoteId = undefined;
  }

  saveNote(){
    this.editableNote.id = this.editableNoteId;
    client.fetch('http://localhost:8080/notes/save', {
      'method': "POST",
      'body': json(this.editableNote)
    }).then(response => {
      console.log("note saved", response.json());
      this.getNotes();
      this.cancelEdit();
    });
  }


}
