function radiobuttonClick(obj) {
    var nameId = obj.getAttribute("name");
    var elem = document.getElementById(nameId);
    elem.click();
}

function result() {
    var radios = document.getElementsByTagName('input');
    var value;
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked && radios[i].name === 'radio') {
            value = radios[i].value;
        }
    }
    alert(value);
}

var tr = null;

function selectFunction(td) {
    if (null != tr) {
        tr.className = "";
    }
    tr = td.parentNode;
    tr.className = "selected";
}

