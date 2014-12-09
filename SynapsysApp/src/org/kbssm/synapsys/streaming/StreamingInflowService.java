package org.kbssm.synapsys.streaming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/**
 * PC�κ��� ��Ʈ������ �޾� ȭ���� ������ִ� Activity.
 *  
 * @author Yeonho.Kim
 *
 */
public class StreamingInflowService extends Service implements Runnable {
	
	public static final int PORT = 1113;
	private static final int TIMEOUT = 10 * 1000; 	// ms
	
	private Thread mInflowThread;

	/**
	 * 
	 */
	private Socket mClientSocket;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stu
		mInflowThread = new Thread(mConnectionRunnable);
		mInflowThread.start();
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
			
         switch(newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
            	break;
            	
            case Configuration.ORIENTATION_PORTRAIT: 
            	break;
         }
	}
	
	@Override
	public void run() {
		
			
	}
	
	
	/**
	 * 
	 */
	private Runnable mConnectionRunnable = new Runnable() {
		
		ServerSocket serverSock;
		
		@Override
		public void run() {
			try {
				if (mClientSocket != null)
					mClientSocket.close();
				
				serverSock = new ServerSocket(PORT);
				serverSock.setSoTimeout(TIMEOUT);
				
				mClientSocket = serverSock.accept();

			} catch (SocketException e) {
				// Server Socket Timeout !!
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}  finally {
				if (serverSock != null) {
					try {
						serverSock.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	};
	
}
