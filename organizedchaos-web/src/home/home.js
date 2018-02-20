import {Note} from './note';

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

  saveNote() {
    let noteHtml = "<div class='bs-component'><div class='card text-white bg-primary mb-3' style='max-width: 20rem;\'>";
    note += "<div class='card-header'>";
    note += this.addNote().header;
    note += "<span onclick='removeNote(this)' style='float: right; cursor: pointer'>✖️</span>️</div>";
    note += "<div class='card-body'><p class='card-text'>" + this.addNote().description + "</p>";
    note += "</div></div></div>";
    document.getElementById("container").innerHTML = noteHtml;
  }

  removeNote(note) {
    let index = this.notes.indexOf(note);
    if (index !== -1) {
      this.notes.splice(index, 1);
    }
  }
}
