
// Nom du package
// doit correspondre a id dans le fichier plugin.xml 
package com.morillon.cordova;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.R;
//import android.util.log;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Activity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import android.net.Uri;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.view.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FilesPlugin extends CordovaPlugin implements View.OnClickListener {

	private Activity activity;
	private CallbackContext _callbackContext;
	
	public void onClick(View view) {
		
	}

	// @SuppressLint("NewApi")
	@Override
	public void initialize(final CordovaInterface cordova,
			final CordovaWebView webView) {
		super.initialize(cordova, webView);
		activity = cordova.getActivity();
		// final View view = webView.getView();
		// root = (ViewGroup) view.getParent();
	}

	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {

		try {
			String message = "";
			String title = "Alert By MA";
			
			_callbackContext = callbackContext;
			// CAS de l'action "alert"
			if (action.equals("alert")) {
				
				if (args.length() > 0) {
					message += args.getString(0);
				}
				if (args.length() > 1 && !args.isNull(1)) {
					title = args.getString(1);
				}
				
				// Affichage du message
				showAlert(message,title);
				
				// Possible de faire un retour ici avec un tableau de donnees
				//JSONArray res = new JSONArray();
				//res.put("priv");
				//res.put("pub");
				//callbackContext.success("RESULT:" + scontent);
				return true;
				
				
			} else if (action.equals("jsonMessage")) {
				// CAS de l'action "jsonMessage"
				
				if (args.length() > 0) {
					// On tente la recupération du JSON sur le premier argument
					JSONObject jsonParams = args.getJSONObject(0);
					// Si une valeur sur le titre ...
					if (!jsonParams.isNull("title")) {
						title = jsonParams.getString("title");
					}
					if (jsonParams.has("message")) {
						message = jsonParams.getString("message");
					}
					// Affichage du message
					showAlert(message,title);
					return true;
				}
			} else {
				
				// Exception sur une mauvaise action
				callbackContext.error("No action not found !!!");
				return false;
			}
		} catch (Exception ex) {

			callbackContext.error("Unexpected error:" + ex.toString());
		}
		return false;
	}

	private void showAlert(String content,String title) {
		
		// see http://developer.android.com/guide/topics/ui/dialogs.html
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				this.cordova.getActivity(),
				AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		alertDialog.setTitle(title);
		alertDialog.setMessage(content);
		
		// Ajout du bouton OK
		alertDialog.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						
						// Retour au javascript 
						_callbackContext.success("OK");
						
						// Ouverture dans un browser du lien 
						//activity.startActivity(new Intent(
						//		Intent.ACTION_VIEW,
						//			Uri.parse("http://lupc.elimit.eu")));
						
						// Fermeture du dialog
						dialog.dismiss();
					}
				});
				
		// Ajout d'un bouton CANCEL
		alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                
            	// Retour au javascript 
            	_callbackContext.success("CANCEL");
				
				// Fermeture du dialog
            	dialog.dismiss();
            }
        });
		// Affichage du dialog
		alertDialog.show();
	}
}