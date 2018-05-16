import $ from 'jquery';
import 'fullcalendar';
import moment from "moment";
import environment from 'environment';
import {Event} from "./event";

export class calendar {

  constructor() {

  }

  attached() {
    $('#calendar').fullCalendar({
      header: {
        right: 'addEventButton, next',
        center: '',
        left: 'prev'
      },
      firstDay: 1,
      weekNumbers: 1,
      customButtons: {
        addEventButton: {
          text: 'Add event',
          click: function () {
            let startDateStr = prompt('Enter a start date in YYYY-MM-DD format');
            let startDate = moment(startDateStr);
            let endDateStr = prompt('Enter an end date in YYYY-MM-DD format');
            let endDate = moment(endDateStr);
            let titleStr = prompt('What\`s the event?');
            let title = titleStr.toUpperCase();
            let descriptionStr = prompt('Enter a description');
            let description = descriptionStr.toUpperCase();

            if (startDate.isValid()) {
              $('#calendar').fullCalendar('renderEvent', {
                title: title,
                start: startDate,
                end: endDate,
                description: description,
                allDay: true,
                textColor: 'white',
                stick: true
              });
              alert('Great.');
              /*const createdEvent = new events(this.eventName, this.eventDescription, this.startDate, this.endDate);
              createdEvent.addEvent();*/
            } else {
              alert('Invalid date.');
            }
          }
        }
      },
      eventSources: [{
        url: environment.url + 'api/events',
        type: 'GET',
        headers: {
          'Authorization': sessionStorage.getItem('token')
        },
        events: 'json',
        success: function (response) {
          console.log(response);
          alert('worked');
          //return {events: response};
        },
        error: function (response) {
          alert('wrong');
        }
      }
      ],
      textColor: 'black',
      editable: true
    })
  }
}

