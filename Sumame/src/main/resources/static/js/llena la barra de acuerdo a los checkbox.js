function check(form){
	var j = 0;
	for (var i = 0; i < 10; i++) {//i<3
		if (form.adaptado[i].checked==true) {
			j++;
		}
	}
	if (j==0) {
		document.getElementById("barra").value = "0";
	} else if (j==1) {
		document.getElementById("barra").value = "10";
	} else if (j==2) {
		document.getElementById("barra").value = "20";
	} else if (j==3) {
		document.getElementById("barra").value = "30";
	} else if (j==4) {
		document.getElementById("barra").value = "40";
	} else if (j==5) {
		document.getElementById("barra").value = "50";
	} else if (j==6) {
		document.getElementById("barra").value = "60";
	} else if (j==7) {
		document.getElementById("barra").value = "70";
	} else if (j==8) {
		document.getElementById("barra").value = "80";
	} else if (j==9) {
		document.getElementById("barra").value = "90";
	} else if (j==10) {
		document.getElementById("barra").value = "100";
	}
}