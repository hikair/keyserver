function keypress(t) {
    t.on("keydown",function (e) {
        t.val("");
        var str="";
        if(e.shiftKey){
            if(!isEmpty(t)){
                str+="+";
            }
            str+="shift";
            t.val(remove(str));
        }
        if(e.ctrlKey){
            if(!isEmpty(t)){
                str+="+";
            }
            str+="ctrl";
            t.val(remove(str));

        }
        if(e.altKey){
            if(!isEmpty(t)){
                str+="+";
            }
            str+="alt";
            t.val(remove(str));
        }
        str = remove(str);
        var keyName = getKeyName(e.keyCode);
        if(keyName!==""){
            if(!isEmpty(t)){
                str+="+";
            }
            str+=keyName;
        }
        t.val(str);
    });
}

function isEmpty(t) {
    var temp = t.val();
    return temp === "";
}

function remove(str) {
    if(str.indexOf("+")===0){
        str=str.substr(1,str.length);
    }
    return str;
}

function getKeyName(keyCode) {
    switch (keyCode) {
        case 65:return "a";
        case 66:return "b";
        case 67:return "c";
        case 68:return "d";
        case 69:return "e";
        case 70:return "f";
        case 71:return "g";
        case 72:return "h";
        case 73:return "i";
        case 74:return "j";
        case 75:return "k";
        case 76:return "l";
        case 77:return "m";
        case 78:return "n";
        case 79:return "o";
        case 80:return "p";
        case 81:return "q";
        case 82:return "r";
        case 83:return "s";
        case 84:return "t";
        case 85:return "u";
        case 86:return "v";
        case 87:return "w";
        case 88:return "x";
        case 89:return "y";
        case 90:return "z";
        case 48:return "0";
        case 49:return "1";
        case 50:return "2";
        case 51:return "3";
        case 52:return "4";
        case 53:return "5";
        case 54:return "6";
        case 55:return "7";
        case 56:return "8";
        case 57:return "9";
        case 8:return "??????";
        case 9:return "tab";
        case 13:return "??????";
        case 20:return "caps";
        case 27:return "esc";
        case 32:return "??????";
        case 33:return "?????????";
        case 34:return "?????????";
        case 35:return "end";
        case 36:return "home";
        case 37:return "?????????";
        case 38:return "?????????";
        case 39:return "?????????";
        case 40:return "?????????";
        case 46:return "??????";
        case 144:return "num";
        case 186:return "??????";
        case 187:return "??????";
        case 188:return "?????????";
        case 189:return "?????????";
        case 190:return "?????????";
        case 191:return "??????";
        case 192:return "?????????";
        case 219:return "?????????";
        case 220:return "?????????";
        case 221:return "?????????";
        case 222:return "??????";
        case 96:return "0(x)";
        case 97:return "1(x)";
        case 98:return "2(x)";
        case 99:return "3(x)";
        case 100:return "4(x)";
        case 101:return "5(x)";
        case 102:return "6(x)";
        case 103:return "7(x)";
        case 104:return "8(x)";
        case 105:return "9(x)";
        case 106:return "??????";
        case 107:return "??????";
        case 109:return "??????";
        case 111:return "??????";
        case 112:return "f1";
        case 113:return "f2";
        case 114:return "f3";
        case 115:return "f4";
        case 116:return "f5";
        case 117:return "f6";
        case 118:return "f7";
        case 119:return "f8";
        case 120:return "f9";
        case 121:return "f10";
        case 122:return "f11";
        case 123:return "f12";
        default:return "";
    }
}