let [executable, absPath, target, ...message] = process.argv;

console.log("executable:" + executable);
console.log("absPath:" + absPath);
console.log("target:" + target);
console.log("message:" + message);
// ********************************************************
console.log("new line")

let obj = {
    aaa: 'aaa111',
    bbb: 'bbb222',
    fff: 'fff333'
};

let { aaa, bbb } = obj;

console.log(aaa, bbb);
// ********************************************************
console.log("new line")

const week = ['mon', 'tue', 'wed', 'thur', 'fri'];
const weekend = ['sat', 'sun'];

console.log([...week, ...weekend]); // ['mon','tue','wed','thur','fri','sat','sun']

week.push(...weekend);
console.log(week); // ['mon','tue','wed','thur','fri','sat','sun']

// ********************************************************
console.log("new line")

function Counter1() {
    this.count = 0;

    setInterval(function() { 
        console.log(this);
        console.log(this.count++);
      }, 1000);
}


function Counter2() {
    this.count = 0;
    
    setInterval(() => { 
        console.log(this);
        console.log(this.count++);
    }, 1000);
}

//new Counter1();
//new Counter2();

// ********************************************************
console.log("new line")

let name = 'shai'
console.log('my name is ' + name);
console.log(`my name is ${name}`)

console.log(`2 + 2= ${2+2}`);

for(let c of 'shalom shalom'){
    console.log(c)
}

// ********************************************************
console.log("new line")
let string = [...'shalom  shalom'];
for (let i=0;i<string.length-1;i=i+2){
    console.log(string[i]+string[i+1])
}
