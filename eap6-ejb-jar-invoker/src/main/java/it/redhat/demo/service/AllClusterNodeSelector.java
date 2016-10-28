package it.redhat.demo.service;

import org.jboss.ejb.client.ClusterNodeSelector;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by fabio on 26/09/16.
 */
public class AllClusterNodeSelector implements ClusterNodeSelector {
    @Override
    public String selectNode(final String clusterName, final String[] connectedNodes, final String[] availableNodes) {

        String message = "INSTANCE " + this + " : cluster:" + clusterName + " connected:" + Arrays.deepToString(connectedNodes) + " available:" + Arrays.deepToString(availableNodes);

        if (availableNodes.length == 1) {
            return availableNodes[0];
        }
        final Random random = new Random();
        final int randomSelection = random.nextInt(availableNodes.length);

        System.out.println(message + " :: CHOSEN :: " + randomSelection);

        return availableNodes[randomSelection];

    }
}
