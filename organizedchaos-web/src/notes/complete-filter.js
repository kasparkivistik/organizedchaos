export class CompleteFilterValueConverter {
  toView(items, complete) {

    return items.filter((item) => item.complete === complete);
  }
}
