# filesPlugin
Example how create a sample Plugin for Android

Cordova FilesPlugin Plugin

Install with Cordova CLI
	
	$ cordova plugin add https://github.com/amn31/filesPlugin.git

API

	fileChooser.open(successCallback, failureCallback);


	// L'utilisation du module sera ainsi
	filesPlugin.showMessage('Good news');
	
	ou
	
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
