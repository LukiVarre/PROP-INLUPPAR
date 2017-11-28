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

obj0 = myObject.create(null);
obj0.func = function (arg) {
    return "func0: " + arg;
};
result = obj0.call("func", ["hello"]);
console.log("should print 'func0: hello' ->", result);