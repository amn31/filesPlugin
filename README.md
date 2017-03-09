# filesPlugin
Example how create a sample Plugin for Android

Cordova FilesPlugin Plugin

Install with Cordova CLI
	
	$ cordova plugin add https://github.com/amn31/filesPlugin.git

Exemple d'utilisation de l'API Javascript



	// Affichage du message 'Good news'
	filesPlugin.showMessage('Good news');
	
	ou
	
	// cas de success 
	// click sur bouton OK
	successCallback = function(uri) {
		alert('Success:'+uri);
	};
	
	// cas d'erreur 
	// click sur bouton CANCEL
	failureCallback = function(error) {
		alert('Error:'+error);
	};
		
	// Affichage du message
    var nothingToReturn = filesPlugin.alert(['Voici le message','Le title','not used'],successCallback, failureCallback);

	ou
	
	// Autre appel à partir d'un JSON
	filesPlugin.jsonMessage({
        		title: 'LE TITRE',
        		message: 'LE MESSAGE'
        	},successCallback, failureCallback);	
