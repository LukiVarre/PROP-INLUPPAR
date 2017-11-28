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

myObject.call = function (funcName, funcArgument) {
    if (this.hasOwnProperty(funcName)) {
        return this[funcName](funcArgument);
    } else {
        return findFunc(this, funcName, funcArgument);
    }
};

var findFunc = function (object, funcName, funcArgument) {
    for (var i = 0; i < object.prototypeList.length; ++i) {
        if (object.prototypeList[i].hasOwnProperty(funcName)) {
            return object.prototypeList[i][funcName](funcArgument);
        } else {
            return findFunc(object.prototypeList[i], funcName, funcArgument);
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