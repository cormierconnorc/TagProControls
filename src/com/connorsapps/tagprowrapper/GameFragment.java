package com.connorsapps.tagprowrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameFragment extends Fragment
{
	private Callback callback;
	private ViewGroup root;
	private WebView web;
	private String currentUrl;
	private final Pattern PORT_PATTERN = Pattern.compile(":\\d{4}");
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		root = (ViewGroup)inflater.inflate(R.layout.fragment_game, container, false);
		web = (WebView)root.findViewById(R.id.game_view);
		
		web.loadUrl("http://tagpro.koalabeast.com");
		web.setWebViewClient(new WebViewClient() {
			private boolean wasInGame;

			private void changeControlVisibility()
			{
				//Check if this is a game url (will be on non-http port)
				boolean match = isInGame();
				if (match && !wasInGame)
				{
					callback.onEnterGame();
					wasInGame = true;
				}
				else if (!match && wasInGame)
				{
					callback.onLeaveGame();
					wasInGame = false;
				}		
			}
			
			@Override
			public void onPageFinished(WebView view, String url)
			{
				currentUrl = url;
				changeControlVisibility();
				super.onPageFinished(view, url);
			}
			
		});
		
		WebSettings setup = web.getSettings();
		setup.setJavaScriptEnabled(true);
		setup.setBuiltInZoomControls(true);
		setup.setDisplayZoomControls(false);
		setup.setLoadWithOverviewMode(true);
		setup.setUseWideViewPort(true);
		
		return root;
	}
	
	public boolean isInGame()
	{
		Matcher m = PORT_PATTERN.matcher(currentUrl);
		return m.find();
	}
	
	public Callback getCallback()
	{
		return callback;
	}

	public void setCallback(Callback callback)
	{
		this.callback = callback;
	}
	
	public void notifyKeyEvent(KeyEvent... k)
	{
		//Send key events to webview
		for (KeyEvent key : k)
			web.dispatchKeyEvent(key);
	}
	
	public WebView getWebView()
	{
		return this.web;
	}

	/**
	 * Step back the web view if it can go back.
	 * @return true if stepped back, false otherwise
	 */
	public boolean stepBack()
	{
		if (web != null && web.canGoBack())
		{
			web.goBack();
			return true;
		}
		return false;
	}

	public static interface Callback
	{
		public void onEnterGame();
		public void onLeaveGame();
	}
}
