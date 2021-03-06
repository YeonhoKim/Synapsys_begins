package org.kbssm.synapse;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author Yeonho.Kim
 *
 */
public final class SynapseManager implements ISynapse {
	
	/******************************************************************
 		STATICS
	 ******************************************************************/
	/**
	 * 
	 */
	private static SynapseManager sSynapseManager;
	
	/**
	 * 
	 * @return
	 */
	public static SynapseManager getInstance(Context context, ISynapseListener listener) throws SynapseException {
        synchronized (SynapseManager.class) {
            if (sSynapseManager == null) 
                sSynapseManager = new SynapseManager(context, listener);
            
            return sSynapseManager;
        }
    }

	
	
	/******************************************************************
 		FIELDS
	 ******************************************************************/
	/**
	 * 
	 */
	private final Context mContextF;

	private SynapseConnection mConnection;
	private SynapseInteraction mInteraction;
	private ISynapseListener mListener;
	
	
	
	/******************************************************************
		CONSTRUCTORS
	 ******************************************************************/
	/** */
	private SynapseManager(Context context, ISynapseListener listener) {
		this.mContextF = context;
		
		mConnection = new SynapseConnection(this);
		mConnection.setSynapseListener(mListener = listener);
		
		mInteraction = new SynapseInteraction(this);
	}
        
	
	
	/******************************************************************
		METHODS
	 ******************************************************************/
	/** */
	public synchronized void destroy() {
		if (mConnection != null) {
			mConnection.destroy();
			mConnection = null;
		}
		
		if (mInteraction != null) {
			mInteraction.destroy();
			mInteraction = null;
		}
		
		sSynapseManager = null;
	}
	
	public void setUsbTethering(boolean enable) {
		if (mConnection != null)
			mConnection.setUsbTethering(enable);
	}
	
	public boolean isOnUsbTethering() {
		if (mConnection != null)
			mConnection.isOnUsbTethering();
		return false;
	}
	
	public void findConnectedAddress(boolean force) {
		if (mConnection != null)
			mConnection.findConnectedAddress(force);
	}
	

	
	/******************************************************************
 		CALLBACKS
	 ******************************************************************/
	/** */
	@Override
	public boolean synapse() {
		if (mConnection != null && !mConnection.isSynapsed())
			return mConnection.synapse();
		return false;
	}

	@Override
	public boolean insynapse() {
		if (mConnection != null && mConnection.isSynapsed())
			return mConnection.insynapse();
		return false;
	}
	
	@Override
	public boolean resynapse() {
		if (mConnection != null){
			if(mConnection.isSynapsed())
				return mConnection.resynapse();
			else
				return mConnection.synapse();
		}
		return false;
	}

	@Override
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resume() {
		// TODO Auto-generated method stub
		return false;
	}

	public static NetworkInfo getConnectedNetworkInfo() {
		NetworkInfo mNetworkInfo = new NetworkInfo(
				ConnectivityManager.TYPE_ETHERNET,
				ConnectivityManager.TYPE_WIFI,
				"Ethernet/Wifi",
				"Reverse_USB_Tethering");
		
		mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.CONNECTED, "Synapsys","Reverse_USB_Tethering");
		mNetworkInfo.setIsAvailable(true);
		
		return mNetworkInfo;
	}
	

	
	/******************************************************************
 		GETTER & SETTER
	 ******************************************************************/
	/** */
	final Context getContext() {
		return mContextF;
	}
	
	final SynapseConnection getConnection() {
		return mConnection;
	}
	
	final SynapseInteraction getIneraction() {
		return mInteraction;
	}
	
	public void setSynapsysListener(ISynapseListener listener) {
		mConnection.setSynapseListener(mListener = listener);
	}
	
	public String getTetheredAddress() {
		if (mConnection != null)
			return mConnection.getTetheredAddress();
		return null;
	}

}
