package com.example;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Context;
import android.content.Intent;

import com.example.gcm.GCMUtility;
import com.example.utility.Logcat;
import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService
{
	public GCMIntentService()
	{
		super(ExampleConfig.GCM_SENDER_ID);
	}


	@Override
	protected void onRegistered(Context context, String registrationId)
	{
		Logcat.d("GCMIntentService.onRegistered(): GCM service successfully registered device with registration ID " + registrationId);
		GCMUtility.register(context, registrationId);
	}


	@Override
	protected void onUnregistered(Context context, String registrationId)
	{
		Logcat.d("GCMIntentService.onUnregistered(): GCM service successfully unregistered device with registration ID " + registrationId);
		GCMUtility.unregister(context, registrationId);
	}


	@Override
	protected void onMessage(Context context, Intent intent)
	{
		Logcat.d("GCMIntentService.onMessage(): received message " + intent.getExtras());
		
		// get message
		String message = intent.getStringExtra("message");
		
		// decode message
		try
		{
			if(message!=null) message = URLDecoder.decode(message, "UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		// TODO
	}


	@Override
	protected void onDeletedMessages(Context context, int total)
	{
		Logcat.d("GCMIntentService.onDeletedMessages(): server deleted " + total + " pending messages");
	}


	@Override
	protected void onError(Context context, String errorId)
	{
		Logcat.d("GCMIntentService.onError(): received error " + errorId);
	}


	@Override
	protected boolean onRecoverableError(Context context, String errorId)
	{
		Logcat.d("GCMIntentService.onRecoverableError(): received recoverable error " + errorId);
		return super.onRecoverableError(context, errorId);
	}
}
