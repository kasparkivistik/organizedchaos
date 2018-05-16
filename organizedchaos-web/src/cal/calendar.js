import $ from 'jquery';
import 'fullcalendar';
import moment from "moment";
import environment from 'environment';

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
              alert('Great. Now, update your database...');
            } else {
              alert('Invalid date.');
            }
          }
        }
      },
      eventSources: [{
        events: [
          function () {
            $.ajax({
              url: environment.url + 'events',
              type: 'GET',
              headers: {
                'Authorizaton': sessionStorage.getItem('token')
              },
              data: {
                format: 'json'
              },
              success: function (data) {
                for (let i = 0; i < data.length; i++) {
                  let outputData = {
                    d
                  }
                }
              }
            })
          }
        ],
        textColor: 'black'
      }],
      editable: true
    })
  }
}

