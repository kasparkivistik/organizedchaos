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
      weekNumbers:1,
      eventSources: [{
        events: [
          {
            title  : 'Tarkvaratehnika II iteratsiooni kaitsmine',
            start  : '2018-04-11T14:00:00',
            allDay: false
          },
          {
            title  : 'Keit reisil',
            start  : '2018-04-26',
            end    : '2018-05-03'
          }
        ],
        textColor: 'black'
      }],
      editable: true
    })
  }
}

