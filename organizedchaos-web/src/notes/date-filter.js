import moment from 'moment';

export class DateFilterValueConverter {
  toView(items, type) {
    if (type === 'upcoming') {
      return items.filter((item) => moment(item.date).isSameOrAfter(moment(), 'day'));
    } else {
      return items.filter((item) => moment(item.date).isBefore(moment(), 'day'));
    }
  }
}
