import $ from 'jquery';
import 'fullcalendar';

export class calendar {

  constructor(){

  }

  attached() {
    $('#calendar').fullCalendar({
      header:{
        right: "next",
        center: "title",
        left: "prev"
      },
      firstDay:1,
      weekNumbers:1
    })
  }
}

