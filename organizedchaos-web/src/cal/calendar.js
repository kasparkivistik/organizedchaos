export class calendar {

  constructor(){

  }
}

$(function() {

  // page is now ready, initialize the calendar...

  $('#calendar').fullCalendar({
    header:{
      right: "next",
      center: "title",
      left: "prev"
    },
    firstDay:1,
    weekNumbers:1
  })

});
