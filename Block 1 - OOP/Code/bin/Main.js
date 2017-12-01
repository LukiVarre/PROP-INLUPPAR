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

myObject.call = function (funcName, funcParameter) {
    if (this.hasOwnProperty(funcName)) {
        return this[funcName](funcParameter);
    } else {
        return find(this, funcName, funcParameter);
    }
};

var find = function (object, funcName, funcParameter) {
    for (var i = 0; i < object.prototypeList.length; i++) {
        if (object.prototypeList[i].hasOwnProperty(funcName)) {
            return object.prototypeList[i][funcName](funcParameter);
        } else {
            for (var j = 0; j < object.prototypeList[i].prototypeList.length; i++) {
                if (object.prototypeList[i].prototypeList[j].hasOwnProperty(funcName)) {
                    return find(object.prototypeList[i], funcName, funcParameter);
                }
            }
        }
        }
    }
;

// Exempel Kod 1
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


// Exempel Kod 2
objZero = myObject.create(null);
objZero.func = function (arg) {
    return "func0: " + arg;
};
objOne = myObject.create([objZero]);
objTwo = myObject.create([]);

objThree = myObject.create([objTwo, objOne]);
res = objThree.call("func", ["hello"]);
console.log("should print 'func0: hello' ->", res);


// Exempel Kod 3
obj0 = myObject.create(null);
obj0.func = function (arg) {
    return "func0: " + arg;
};
r = obj0.call("func", ["hello"]);
console.log("should print 'func0: hello' ->", r);

