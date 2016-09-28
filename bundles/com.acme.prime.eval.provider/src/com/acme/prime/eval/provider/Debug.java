package com.acme.prime.eval.provider;

import org.eclipse.ecf.osgi.services.remoteserviceadmin.DebugRemoteServiceAdminListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

@Component
public class Debug extends DebugRemoteServiceAdminListener implements RemoteServiceAdminListener {

}
