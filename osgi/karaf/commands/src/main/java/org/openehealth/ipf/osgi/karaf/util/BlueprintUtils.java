package org.openehealth.ipf.osgi.karaf.util;

import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Boris Stanojevic
 */
public class BlueprintUtils {

    public static <T> T getOsgiService(BundleContext bundleContext, Class<T> type, long timeout) {
        ServiceTracker tracker = null;
        try {
            String flt = "(" + Constants.OBJECTCLASS + "=" + type.getName() + ")";
            Filter osgiFilter = FrameworkUtil.createFilter(flt);
            tracker = new ServiceTracker(bundleContext, osgiFilter, null);
            tracker.open(true);
            Object svc = tracker.waitForService(timeout);
            if (svc == null) {
                throw new RuntimeException("Gave up waiting for service " + flt);
            }
            return type.cast(svc);
        } catch (InvalidSyntaxException e) {
            throw new IllegalArgumentException("Invalid filter", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
