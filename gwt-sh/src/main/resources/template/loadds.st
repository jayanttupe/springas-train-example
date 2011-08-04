function loadjs(dsName){ 
		var fileref=document.createElement('script'); 
		fileref.setAttribute("type","text/javascript"); 
		fileref.setAttribute("src", "shared/ds/json/"+dsName+".ds"); 
		document.getElementById("loadDsMock").appendChild(fileref);
}

$[dsList]:{ds|loadjs("$ds$");}$