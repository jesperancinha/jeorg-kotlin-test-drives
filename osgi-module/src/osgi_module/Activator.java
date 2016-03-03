package osgi_module;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator, CommandProvider {

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("Command activator started!");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Command activator stopped!");
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

}
