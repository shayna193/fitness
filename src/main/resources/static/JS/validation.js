function login(){

    var pass=true;
    var usernameLogin=document.forms["login-form"]["username-login"].value;
    var passwordLogin=document.forms["login-form"]["password-login"].value;
    
    if (usernameLogin.length > 20){
        pass=false; 
        alert("Username must be max 20 characters")
    }
    if (passwordLogin.length < 8){
        pass=false; 
        alert("Password must be min 8 characters")
    }
    return pass;
}

function register(){

    var pass=true
    var usernameRegister=document.forms["register-form"]["username-register"].value;
    var email=document.forms["register-form"]["email-register"].value;
    var passwordRegister=document.forms["register-form"]["password-register"].value;
    var reEnter=document.forms["register-form"]["re-enter"].value;
    
    if (usernameRegister.length > 20){
        pass=false; 
        alert("Username must be max 20 characters")
    }
    //REF: Regular expression taken from https://masteringjs.io/tutorials/fundamentals/email-regex
    if (RegExp(/[^\s@]+@[^\s@]+\.[^\s@]+/).test(email) == false){
        pass=false;
        alert("Please enter a valid email address")
    }
    if (passwordRegister.length < 8){
        pass=false; 
        alert("Password must be min 8 characters")}
    if (reEnter !== passwordRegister){
        pass=false; 
        alert("Passwords must match")
    }
    return pass;
}

function addPark() {
    var pass=true;
    var parkName =document.forms["park-form"]["park-name"].value;
    var location=document.forms["park-form"]["location"].value;
    var description=document.forms["park-form"]["description"].value;

    if (parkName.length < 5 || parkName.length > 50) {
        pass=false;
        alert("Park name must be between 5 and 50 characters");
    }

    if (location.length < 5 || location.length > 50) {
        pass=false;
        alert("Location must be between 5 and 50 characters")
    }

    if (typeof description !== 'string') {
        pass=false;
        alert("Description should be in string format")
    }

    return pass;
}