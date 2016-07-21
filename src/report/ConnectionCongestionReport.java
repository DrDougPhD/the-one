/*
 * Copyright 2016 AFRL, Douglas McGeehan <doug.mcgeehan@mst.edu>
 * Released under GPLv3. See LICENSE.txt for details.
 */
package report;

import core.ConnectionListener;
import core.DTNHost;

/**
 * Report to monitor the metrics that would indicate connection congestion.
 * 
 * @author Douglas McGeehan <doug.mcgeehan@mst.edu>
 */
public class ConnectionCongestionReport extends Report implements ConnectionListener {

	/**
	 * Method that is called when two nodes create a connection.
	 * @see core.ConnectionListener#hostsConnected(core.DTNHost, core.DTNHost)
	 */
	@Override
	public void hostsConnected(DTNHost host1, DTNHost host2) {
		// TODO Auto-generated method stub

	}

	/**
	 *  Method that is called when two nodes break a connection.
	 * @see core.ConnectionListener#hostsDisconnected(core.DTNHost, core.DTNHost)
	 */
	@Override
	public void hostsDisconnected(DTNHost host1, DTNHost host2) {
		// TODO Auto-generated method stub

	}

}
