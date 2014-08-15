package com.connorsapps.tagprowrapper;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class SoleActivity extends ActionBarActivity implements GameFragment.Callback, ControlFragment.Callback, ChatDialogFragment.Callback
{
	private GameFragment game;
	private ControlFragment control;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sole);
		
		this.getSupportActionBar().hide();
		
		initFragments();
		
		showGame();
		showControls();
	}
	
	public void initFragments()
	{
		game = new GameFragment();
		game.setCallback(this);
		
		control = new ControlFragment();
		control.setCallback(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sole, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed()
	{
		//Step back if possible, hand off to super if not.
		if (!game.isAdded() || !game.stepBack())
			super.onBackPressed();
	}
	
	public void showGame()
	{
		if (!this.game.isAdded())
			this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, this.game).commit();
	}
	
	public void showControls()
	{
		if (!this.control.isAdded())
			this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, this.control).commit();
	}
	
	public void hideControls()
	{
		if (this.control.isAdded())
			this.getSupportFragmentManager().beginTransaction().remove(this.control).commit();
	}
	
	@Override
	public void onKeyEvent(KeyEvent... k)
	{
		//Send key event to game fragment
		game.notifyKeyEvent(k);
	}
	
	public void sendKeyPress(int keycode)
	{
		KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, keycode),
				new KeyEvent(KeyEvent.ACTION_UP, keycode)};
		game.notifyKeyEvent(events);
	}
	
	public void createChatDialog(boolean isTeam)
	{
		ChatDialogFragment frag = new ChatDialogFragment(this, isTeam);
		frag.show(getSupportFragmentManager(), "chatFragTag");
	}
	
	@Override
	public void performRet()
	{
		//Create chat dialog
		this.createChatDialog(false);
	}

	@Override
	public void performEsc()
	{
		this.sendKeyPress(KeyEvent.KEYCODE_ESCAPE);
	}

	@Override
	public void performT()
	{
		//Create team chat dialog
		this.createChatDialog(true);
	}

	@Override
	public void onEnterGame()
	{
		//Show control frag upon entering game
		showControls();
	}

	@Override
	public void onLeaveGame()
	{
		//Hide control frag upon leaving game
		hideControls();
	}

	@Override
	public void onChatSubmit(String dialog, boolean isTeam)
	{
		//wow such hack
		//much stupid
		int startKeycode = (isTeam ? KeyEvent.KEYCODE_T : KeyEvent.KEYCODE_ENTER);
		
		List<KeyEvent> events = new ArrayList<KeyEvent>(4 + 2 * dialog.length());
		
		events.add(new KeyEvent(KeyEvent.ACTION_DOWN, startKeycode));
		events.add(new KeyEvent(KeyEvent.ACTION_UP, startKeycode));
		
		char[] vals = dialog.toCharArray();
		KeyCharacterMap map = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
		KeyEvent[] eves = map.getEvents(vals);
		
		for (KeyEvent e : eves)
			events.add(e);
		
		events.add(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
		events.add(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
		
		KeyEvent[] kEvents = new KeyEvent[events.size()];
		events.toArray(kEvents);
		
		game.notifyKeyEvent(kEvents);
	}
}
