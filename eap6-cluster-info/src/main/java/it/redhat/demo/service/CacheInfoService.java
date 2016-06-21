package it.redhat.demo.service;

import org.infinispan.manager.CacheContainer;
import org.jboss.as.server.CurrentServiceContainer;
import org.jboss.msc.service.ServiceName;
import org.jgroups.Address;
import org.jgroups.Channel;
import org.jgroups.Event;
import org.jgroups.PhysicalAddress;
import org.jgroups.stack.IpAddress;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 21/06/16
 *
 * copy from Master the Boss:
 * http://www.mastertheboss.com/jboss-server/jboss-cluster/jboss-cluster-discover-cluster-members?showall=&start=1
 *
 */
@Stateless
public class CacheInfoService {

    @Inject
    private Logger log;

    @Resource(lookup="java:jboss/infinispan/container/singleton")
    private CacheContainer singleton;

    @Resource(lookup="java:jboss/infinispan/container/web")
    private CacheContainer web;

    @Resource(lookup="java:jboss/infinispan/container/ejb")
    private CacheContainer ejb;

    @Resource(lookup="java:jboss/infinispan/container/hibernate")
    private CacheContainer hibernate;

    public Collection<IpAddress> jgroups() {

        Channel channel = (Channel) CurrentServiceContainer.getServiceContainer().getService(ServiceName.JBOSS.append("jgroups", "channel", "web")).getValue();

        List<Address> members = channel.getView().getMembers();
        List< IpAddress> addresses = new ArrayList<IpAddress>();

        for (org.jgroups.Address member: members) {
            PhysicalAddress physicalAddr = (PhysicalAddress)channel.down(new Event(Event.GET_PHYSICAL_ADDRESS, member));
            IpAddress ipAddr = (IpAddress)physicalAddr;
            addresses.add(ipAddr);
        }

        return addresses;

    }

    public Collection<org.infinispan.remoting.transport.Address> infinispan() {

        return web.getCache().getCacheManager().getMembers();

    }

    public Object jmx() {

        try {

            return ManagementFactory.getPlatformMBeanServer().getAttribute(ObjectName.getInstance("jgroups:type=channel,cluster=\"web\""), "View");

        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            return null;

        }
    }

}
