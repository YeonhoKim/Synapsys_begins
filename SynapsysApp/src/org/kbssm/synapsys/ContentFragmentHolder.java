package org.kbssm.synapsys;

import org.kbssm.synapsys.usb.USBConnectionFragment;
import org.kbssm.synapsys.wireless.WirelessConnectionFragment;


/**
 * {@link NavigationDrawerFragment}���� �ٷ� Fragment���� �ٷ�� Holder Class.
 * 
 * @author Yeonho.Kim
 *
 */
public class ContentFragmentHolder {

	private static final NavigationFragmentCallbacks[] sContentFragments;
	static {
		HomeFragment home = new HomeFragment();
		USBConnectionFragment usb = new USBConnectionFragment();
		WirelessConnectionFragment wireless = new WirelessConnectionFragment();
		SettingFragment setting = new SettingFragment();
		
		sContentFragments = new NavigationFragmentCallbacks[]{
			home,
			usb,
			wireless,
			setting
		};
	}
	
	public static NavigationFragmentCallbacks getInstance(int position) {
		return sContentFragments[position];
	}
}
