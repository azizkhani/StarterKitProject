//-------------- Autocomplete --------------------------------------------------------------
function comboEditTyped(e){
	var ev;
	var elm;
	if(window.event == null){
		ev = e;
		elm = e.target;
	}		
	else{
		ev = window.event;
		elm = event.srcElement;
	}
	var typeElemId = elm.getAttribute('id');
	autoComplFieldName = typeElemId.substring(0, typeElemId.length - 5);
	var selElemId = autoComplFieldName + 'Select';
	if(ev.keyCode != 9){//tab
		showElement(autoComplFieldName + 'Div');
	}
	var sel = getById(selElemId);

	switch(ev.keyCode){
		case 38://up
	 		if(sel.selectedIndex > 0){
				sel.selectedIndex-=1;
				setComboValue(selElemId);
			}
		break;
		case 40://down{
			if(sel.selectedIndex < sel.options.length -1){
				sel.selectedIndex+=1;
				setComboValue(selElemId);
			}
		break;
		case 13:
				setComboValue(selElemId);
				hideElement(autoComplFieldName + 'Div');
		break;
		case 27://Esc key
			hideElement(autoComplFieldName + 'Div');
		break;
		default:
			if(ev.keyCode > 40 || ev.keyCode == 8){ //8 is code of backspace
				comboEditChanged();
			}
			
	}
}

function comboEditChanged(){
	var selElm = getById(autoComplFieldName + "Select");
	var typeElm = getById(autoComplFieldName + "Title");

	if(typeElm.value == '' || typeElm.value ==' '){
		selElm.selectedIndex = 0;
		setComboValue(selElm.getAttribute('id'));
		return;
	}
	typeElm.value = toFarsi(typeElm.value);
	var find = false;
	for(i = 0; i<selElm.options.length; i++){
		if(selElm.options[i].text.indexOf(typeElm.value) == 0){
			selElm.selectedIndex = i;
			find = true;
			break;
		}
	}
	if(!find){
		selElm.selectedIndex = 0;
		//setComboValue(selElm.id);
	}
	lastType = (new Date()).getTime();
	timeout = setTimeout(loadOptions, 1000);
}
var lastType=0;
var autoComplFieldName;
var timeout;
var loadOptions = function loadOptions(){
	if((new Date()).getTime() - lastType < 800){
		try{cancelTimeout(timeout);}catch(e){}
		return;
	}
	var selElm = getById(autoComplFieldName + 'Select');
	var typeElm = getById(autoComplFieldName + 'Title');
	if(typeElm.value.length ==0)
		return;
	var entityName = getById(autoComplFieldName +'EntityName').value;
	var filter = getById(autoComplFieldName +'Filter').value + '@@empty@@'+ typeElm.value.replace('*','%');
	var options='';
	dwr.util.useLoadingMessage();
	dwr.engine.beginBatch();
	creator.getPairValues(entityName, filter,  function(values) {
		var selected= false;
		for(i = 0; i < values.length ; i++){
			if(values[i].value.indexOf(typeElm.value) == 0 && !selected){
				options += '<option selected value="' + values[i].key +'">'+values[i].value+'</option>';
				selected = true;
			}
			else
				options += '<option value="' + values[i].key +'">'+values[i].value+'</option>';
		}
		if(selected)
			options= '<option value="-1"></option>' + options;
		else
			options= '<option value="-1" selected></option>' + options;
		if(isIE()){
			try{
				var elmdiv = getById(autoComplFieldName + 'Div');
				elmdiv.style.display='none';
				var x = selElm.outerHTML.replace(selElm.innerHTML, options);
				var id = selElm.getAttribute('id');
				
				elmdiv.removeChild(selElm);
				elmdiv.insertAdjacentHTML('BeforeEnd', x);//IE
				selElm = getById(id);
				elmdiv.style.display='block';
			}catch(e){}
		}else{
			selElm.innerHTML = options;
		}
		selElm.size = values.length + 1; 
	});
	if(typeElm.value=='*')
		typeElm.value='';
  dwr.engine.endBatch();	
}

function setComboValue(selectId){
	try{
		var typeElemId = selectId.substring(0, selectId.length - 6) + 'Title';
		var sel = getById(selectId);
		if(selectId.indexOf("search_") == -1)
			getById(typeElemId).value=sel.options[sel.selectedIndex].text.split('-')[0];
		else
			getById(typeElemId).value=sel.options[sel.selectedIndex].text;
		
		//set element value
		var fieldName = getById(selectId.substring(0, selectId.length - 6) +'FieldName').value;
		getById(fieldName).value = sel.options[sel.selectedIndex].value;
	}catch(e){}
}

//add an option to a auto complete with this id and title
function addToAutoComplete(name, id, title){
		if(title == null || title.length == 0 )
		return;
		title= title.split('-')[0];
		if(getById(name + 'Select')){
			if(isIE()){
				try{
					var selElm = getById(name + 'Select');
					var x = selElm.outerHTML.replace(selElm.innerHTML, '<option value="-1"></option><option selected value="' + id +'">'+ title +'</option>');
					var elmdiv = getById(name + 'Div');
					elmdiv.removeChild(selElm);
					elmdiv.insertAdjacentHTML('BeforeEnd', x);//IE
				}catch(e){}
			}else{
				getById(name + 'Select').innerHTML = '<option value="-1"></option><option selected value="' + id +'">'+ title +'</option>';
			}
		}
}

// Add an option to auto comple field and set its value to this option
function setAutoComplete(name, id, title){
		addToAutoComplete(name, id, title);
		getById(name + 'Select').selectedIndex = 1;
		setComboValue(name + 'Select');
}

function addOptionForCurrent(elmId){
	if(getById(elmId + 'Select').options.length <2){
		if(elmId.indexOf("search_") == -1)
			var title = getById(elmId + 'Title').value.split('-')[0];
		else
			var title = getById(elmId + 'Title').value;
		var id = getById(elmId + 'Id').value;
		if(title.length > 0 && id != '-1')
			addToAutoComplete(elmId, id, title);
	}
}
//-------------- Autocomplete -------------------------------------------------------------end-