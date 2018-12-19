import { Subject } from "rxjs/Subject";

var subject = new Subject();

subject.subscribe(
  data => addItem("Observer 1: " + data),
  err => addItem(err),
  () => addItem("Observer 1 Completed")
);

var observer1 = subject.next("The first thig has been sent");
var observer2 = subject.subscribe(data => addItem("Observer2: " + data));

subject.next("The second thig has been sent");

function addItem(val: any) {
  var node = document.createElement("li");
  var textnode = document.createTextNode(val);
  node.appendChild(textnode);
  document.getElementById("output").appendChild(node);
}
