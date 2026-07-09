function confirmDelete(){

return confirm("Are you sure you want to delete this employee?");

}

function validateSalary(){

let salary=document.getElementById("salary");

if(salary!=null){

if(Number(salary.value)<=0){

alert("Salary must be greater than zero");

salary.focus();

return false;

}

}

return true;

}