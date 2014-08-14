package com.connorsapps.tagprowrapper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class ChatDialogFragment extends DialogFragment
{
	private Callback callback;
	private boolean isTeam;
	
	public ChatDialogFragment(Callback callback, boolean isTeam)
	{
		this.callback = callback;
		this.isTeam = isTeam;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
		
		build.setTitle("Enter " + (isTeam ? "team" : "global") + " chat text:");
		
		final EditText input = new EditText(getActivity());
		input.setSingleLine();
		
		build.setView(input);
		
		build.setPositiveButton("Send", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				callback.onChatSubmit(input.getText().toString(), isTeam);
			}
		});
		
		build.setNegativeButton("Cancel", null);
		
		return build.create();
	}
	
	public static interface Callback
	{
		public void onChatSubmit(String dialog, boolean isTeam);
	}
}
