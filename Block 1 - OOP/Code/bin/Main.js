/**
 * Grupp: 10
 * Peter Yakob
 * Lukas Varli
 * */

var myObject = {};

myObject.create = function (prototypeList) {
    return {
        prototypeList: prototypeList,
        create: myObject.create,
        call: myObject.call
    };
};

// Search for a function called 'funcName'
myObject.call = function (funcName, funcParameter) {
    // check if current object has a function called 'funcName'
    if (this.hasOwnProperty(funcName)) {
        return this[funcName](funcParameter);
    } else {
        // call find method if current object does not contain function
        return find(this, funcName, funcParameter);
    }
};

var find = function (object, funcName, funcParameter) {
    for (var i = 0; i < object.prototypeList.length; ++i) {
        // Check if the 'objects' 'prototypeList' contains a function 'funcName'
        if (object.prototypeList[i].hasOwnProperty(funcName)) {
            //return "Hello this is a test output";
            return object.prototypeList[i][funcName](funcParameter);
        } else {
            // recursive call if current object does not contain function
            return find(object.prototypeList[i], funcName, funcParameter);
        }
    }
};

var objectOne = myObject.create(null);
objectOne.func = function (arg) {
    return "func0: " + arg;
};

var objectTwo = myObject.create([objectOne]);
var objectThree = myObject.create([]);
objectThree.func = function (arg) {
    return "func2: " + arg;
};

var objectFour = myObject.create([objectTwo, objectThree]);
var result = objectFour.call("func", ["hello"]);
console.log("should print 'func0: hello' ->", result);
