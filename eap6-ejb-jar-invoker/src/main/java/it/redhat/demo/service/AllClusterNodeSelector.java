package it.redhat.demo.service;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import org.jboss.ejb.client.ClusterNodeSelector;

public class AllClusterNodeSelector implements ClusterNodeSelector {
	
	private static final Logger LOGGER = Logger.getLogger(AllClusterNodeSelector.class.getName());

	public AllClusterNodeSelector() {
		LOGGER.info(this.toString());
	}

	@Override
	public String selectNode(String clusterName, String[] connectedNodes, String[] availableNodes) {

		if (availableNodes.length == 1) {
			return availableNodes[0];
		}

		final Random random = new Random();
		final int randomSelection = random.nextInt(availableNodes.length);
		LOGGER.info(
				"INSTANCE "+ this + 
				" : cluster:"+ clusterName +
				" connected:"+ Arrays.deepToString(connectedNodes) +
				" available:"+ Arrays.deepToString(availableNodes) +
				" selected: "+ randomSelection
			);
		return availableNodes[randomSelection];	
	}

}
