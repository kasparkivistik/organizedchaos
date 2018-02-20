import {Note} from './note';
export class home {
  constructor(){
    this.notes = [];
    this.noteDescription = '';
  }
  addNote() {
    if (this.noteDescription) {
      this.notes.push(new Note(this.noteDescription));
      this.noteDescription = '';
    }
  }

  removeNote(note) {
    let index = this.notes.indexOf(note);
    if (index !== -1) {
      this.notes.splice(index, 1);
    }
  }
}
