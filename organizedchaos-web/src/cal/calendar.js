import $ from 'jquery';
import 'fullcalendar';
import moment from "moment";

export class calendar {

  constructor(){

  }

  attached() {
    $('#calendar').fullCalendar({
      header:{
        right: 'addEventButton, next',
        center: '',
        left: 'prev'
      },
      firstDay:1,
      weekNumbers:1,
      customButtons: {
        addEventButton: {
          text: 'add event...',
          click: function () {
            var dateStr = prompt('Enter a date in YYYY-MM-DD format');
            var date = moment(dateStr);

            if (date.isValid()) {
              $('#calendar').fullCalendar('renderEvent', {
                title: 'dynamic event',
                start: date,
                allDay: true
              });
              alert('Great. Now, update your database...');
            } else {
              alert('Invalid date.');
            }
          }
        }
      },
      eventSources: [{
        events: [

        ],
        textColor: 'black'
      }],
      editable: true
    })
  }
}

