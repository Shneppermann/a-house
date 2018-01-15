
function validateForm() {
	var result = true; 
		var PWD_NOT_EQUAL = "This password don't match! Try again?";
			
		var errPwd1 =  document.getElementById("err-pwd1");
			
		errPwd1.innerHTML = "";		

		var pwd1 = document.forms[0]["password"].value,      
			pwd2 = document.forms[0]["passwordCheck"].value;    
				
		if (pwd1 && pwd2 && pwd1 !== pwd2) {
			errPwd1.innerHTML = PWD_NOT_EQUAL;         
            document.forms[0]["passwordCheck"].value = "";  
			result = false;
		}   				
		return result;
	}
	