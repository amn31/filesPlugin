
/*
	Definition du contenu de la classe Javascript propose

	Le nom de la classe est propose 
        <clobbers target="filesPlugin" />
*/
MyPlugin = {

	// 1ere methode args 
	alert: function (args, success, failure) {
		// la méthode appelée en cas de success 
		if (!success)
			success = function(s){};
		// la méthode appelée en cas d'erreur  
		if (!failure)
			failure = function(m){};
		
        cordova.exec(	success, // callbackContext.success("RESULT:" + scontent);
						failure, // callbackContext.error("No action not found !!!");
						"FilesPlugin", // le nom de la class Java (le service)
						"alert", // mot clé définissant l'action à effectuer
						args);   // tableau javascript [] qui sera reçu dans JSONArray args
    },
    
	// 2éme methode 
	// On passe un paramétre (le message et le titre eventuel)  
	// Il n'y a aucun controle de retour
	showMessage: function (message,title) {
        cordova.exec(function(){}, function(){}, "FilesPlugin", "alert", [message,title]);
    },
    
	// 3éme methode 
	// On passe un paramétre un json  
	// Il a controle de retour possible
    jsonMessage: function (args, success, failure) {
		// Optionnel
		if (!success)
			success = function(){};
		if (!failure)
			failure = function(){};
		  
        cordova.exec(success, failure, "FilesPlugin", "jsonMessage", [args]);
    }
    
};

module.exports = MyPlugin;

/*

	// L'utilisation du module sera ainsi
	filesPlugin.showMessage('Good news');
	
	// utilisation de alerte
	successCallback = function(uri) {
		alert('Success:'+uri);
	};
	
	failureCallback = function(error) {
		alert('Error:'+error);
	};
		
    var nothingToReturn = filesPlugin.alert(['Voici le message','Le title','not used'],successCallback, failureCallback);

	ou
	
	filesPlugin.jsonMessage({
        		title: 'LE TITRE',
        		message: 'LE MESSAGE'
        	},successCallback, failureCallback);
			

*/