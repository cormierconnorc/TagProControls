package com.connorsapps.tagprowrapper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class ControlFragment extends Fragment
{
	private Callback callback;
	private ViewGroup root;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		root = (ViewGroup)inflater.inflate(R.layout.fragment_control, container, false);
		
		setupButtons();
		setupOverlay();
		
		return root;
	}
	
	public void setupButtons()
	{		
		//Set the listeners for the ImageButtons
		ImageButton bNw = (ImageButton)root.findViewById(R.id.b_nw);
		bNw.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP),
									 new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP),
									 new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
		});
		
		ImageButton bN = (ImageButton)root.findViewById(R.id.b_n);
		bN.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP)};
				return events;
			}
		});
		
		ImageButton bNe = (ImageButton)root.findViewById(R.id.b_ne);
		bNe.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP),
									 new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP),
									 new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
		});
		
		ImageButton bE = (ImageButton)root.findViewById(R.id.b_e);
		bE.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
		});
		
		ImageButton bSe = (ImageButton)root.findViewById(R.id.b_se);
		bSe.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_DOWN),
									 new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN),
									 new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT)};
				return events;
			}
		});
		
		ImageButton bS = (ImageButton)root.findViewById(R.id.b_s);
		bS.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_DOWN)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN)};
				return events;
			}
		});
		
		ImageButton bSw = (ImageButton)root.findViewById(R.id.b_sw);
		bSw.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_DOWN),
									 new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN),
									 new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
		});
		
		ImageButton bW = (ImageButton)root.findViewById(R.id.b_w);
		bW.setOnTouchListener(new TemplateTouchListener()
		{
			
			@Override
			public KeyEvent[] getUpEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
			
			@Override
			public KeyEvent[] getDownEvents()
			{
				KeyEvent[] events = {new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT)};
				return events;
			}
		});
		
		
		View.OnTouchListener clickListener = new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				//On action up, perform click
				if (event.getAction() == MotionEvent.ACTION_UP)
				{
					v.performClick();
					return true;
				}
				return false;
			}
		};
		
		Button bEsc = (Button)root.findViewById(R.id.b_esc);
		bEsc.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				callback.performEsc();
			}
		});
		bEsc.setOnTouchListener(clickListener);
		
		Button bRet = (Button)root.findViewById(R.id.b_ret);
		bRet.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				callback.performRet();
			}
		});
		bRet.setOnTouchListener(clickListener);
		
		Button bT = (Button)root.findViewById(R.id.b_t);
		bT.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				callback.performT();
			}
		});
		bT.setOnTouchListener(clickListener);
		
		Button toggleVisible = (Button)root.findViewById(R.id.b_toggle_controls);
		toggleVisible.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Change text
				Button b = (Button)v;
				b.setText(b.getText().toString().equals("Hide Controls") ? "Show Controls" : "Hide Controls");
				
				//Toggle visibility
				ControlFragment.this.toggleControls();
			}
		});
		toggleVisible.setOnTouchListener(clickListener);
	}
	
	public void setupOverlay()
	{
		final ViewGroup relRoot = (ViewGroup)root.findViewById(R.id.rel_root);
		
		// Now set the touch listener for the overlay
		FrameLayout.LayoutParams params = 
				new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		
		View overlay = new View(this.getActivity());
		overlay.setLayoutParams(params);
		
		
		overlay.setOnTouchListener(new View.OnTouchListener()
		{			
			View curTouchView;
			
			private boolean checkTriangleContains(float[] triX, float[] triY, float pX, float pY)
			{
				//Check contains using Barycentric coordinates
				float area = 0.5f * (-triY[1] * triX[2] + triY[0] * (-triX[1] + triX[2]) + triX[0] * (triY[1] - triY[2]) + triX[1] * triY[2]);
				
				float s = 1.0f / (2 * area) * (triY[0] * triX[2] - triX[0] * triY[2] + (triY[2] - triY[0]) * pX + (triX[0] - triX[2]) * pY);
				float t = 1.0f / (2 * area) * (triX[0] * triY[1] - triY[0] * triX[1] + (triY[0] - triY[1]) * pX + (triX[1] - triX[0]) * pY);
				
				return (s <= 1 && s >= 0) && (t <= 1 && t >= 0) && (s + t <= 1);
			}
			
			private boolean checkTriangularPaths(ViewGroup group, View child, float tX, float tY)
			{
				//Make sure this is the control grid and a button
				if (!(group.getId() == R.id.button_grid && (child instanceof Button || child instanceof ImageButton)))
					return false;
				
				//Center point of grid
				float xCent = (float)group.getWidth() / 2;
				float yCent = (float)group.getHeight() / 2;
				
				float cX = child.getX();
				float cY = child.getY();
				
				//All vertices
				float[] xLocs = {cX, cX, cX + child.getWidth(), cX + child.getWidth()};
				float[] yLocs = {cY, cY + child.getHeight(), cY, cY + child.getHeight()};
				
				for (int vOneIndex = 0; vOneIndex < xLocs.length; vOneIndex++)
					for (int vTwoIndex = vOneIndex + 1; vTwoIndex < xLocs.length; vTwoIndex++)
					{
						//Construct a triangle
						float[] xVerts = {xLocs[vOneIndex], xLocs[vTwoIndex], xCent};
						float[] yVerts = {yLocs[vOneIndex], yLocs[vTwoIndex], yCent};
						
						if (checkTriangleContains(xVerts, yVerts, tX, tY))
							return true;
					}
				
				return false;
			}

			private View findChildRecurse(ViewGroup group, float x, float y)
			{	
				for (int i = 0; i < group.getChildCount(); i++)
				{
					View child = group.getChildAt(i);

					if (x >= child.getX() && y >= child.getY()
							&& y <= (child.getY() + child.getHeight())
							&& x <= (child.getX() + child.getWidth())
							//Special handling for control grid
							|| checkTriangularPaths(group, child, x, y))
					{
						//Only allow player to click visible children
						if (child.getVisibility() != View.VISIBLE)
							return null;
						else if (child instanceof ViewGroup)
							return findChildRecurse((ViewGroup)child, x - child.getX(), y - child.getY());
						else
							return child;
					}
				}
				return null;
			}
			
			private View findChild(float x, float y)
			{
				return findChildRecurse(relRoot, x, y);
			}

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				View newView = findChild(event.getX(), event.getY());
				
				switch (event.getAction())
				{
				case MotionEvent.ACTION_MOVE:
					//Switching views
					if (curTouchView != null && newView != null && curTouchView.getId() != newView.getId())
					{
						// Simulate release of other button
						MotionEvent relay = MotionEvent.obtain(0, 0,
								MotionEvent.ACTION_UP, 0, 0, 0);
						curTouchView.dispatchTouchEvent(relay);
						relay.recycle();
						curTouchView = null;
					}
		
					//Handling new press
					if (curTouchView == null && newView != null)
					{
						curTouchView = newView;
						// Relay touch event to child
						curTouchView.dispatchTouchEvent(event);
					}
					
					//Note: Maintain previous touch event if new view is null (allows dragging off control compass)
					break;
				case MotionEvent.ACTION_UP:
					if (curTouchView != null)
					{
						curTouchView.dispatchTouchEvent(event);
						curTouchView = null;
					}
					break;
				case MotionEvent.ACTION_DOWN:
					//Down action on non-control section? Don't handle (and set current to null)
					if (newView == null)
					{
						curTouchView = null;
						return false;
					}
				}
				
				return true;
			}
		});
		
		root.addView(overlay);
	}
	
	
	
	public void toggleControls()
	{
		View grid = root.findViewById(R.id.button_grid);
		
		if (grid.getVisibility() == View.VISIBLE)
			hideControls();
		else
			showControls();
	}
	
	public void hideControls()
	{
		View grid = root.findViewById(R.id.button_grid);
		View cont = root.findViewById(R.id.control_grid);
		
		grid.setVisibility(View.INVISIBLE);
		cont.setVisibility(View.INVISIBLE);
	}
	
	public void showControls()
	{
		View grid = root.findViewById(R.id.button_grid);
		View cont = root.findViewById(R.id.control_grid);
		
		grid.setVisibility(View.VISIBLE);
		cont.setVisibility(View.VISIBLE);
	}
	
	public Callback getCallback()
	{
		return callback;
	}

	public void setCallback(Callback callback)
	{
		this.callback = callback;
	}

	public static interface Callback
	{
		public void onKeyEvent(KeyEvent... k);
		public void performRet();
		public void performEsc();
		public void performT();
	}
	
	private abstract class TemplateTouchListener implements View.OnTouchListener
	{
		private boolean isActive;
		
		@Override
		public boolean onTouch(View v, MotionEvent e)
		{
			switch (e.getAction())
			{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				if (!isActive)
				{
					isActive = true;
					callback.onKeyEvent(getDownEvents());
				}
				return true;
			case MotionEvent.ACTION_UP:
				if (isActive)
				{
					isActive = false;
					callback.onKeyEvent(getUpEvents());
				}
				return true;
			}
			
			return false;
		}
		
		public abstract KeyEvent[] getUpEvents();
		public abstract KeyEvent[] getDownEvents();
	}
}
