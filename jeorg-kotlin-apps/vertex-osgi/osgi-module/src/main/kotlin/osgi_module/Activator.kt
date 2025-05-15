package osgi_module

import org.eclipse.osgi.framework.console.CommandProvider
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

class Activator : BundleActivator, CommandProvider {
    @Throws(Exception::class)
    override fun start(arg0: BundleContext) {
        println("Command activator started!")
    }

    @Throws(Exception::class)
    override fun stop(arg0: BundleContext) {
        println("Command activator stopped!")
    }

    override fun getHelp(): String?  = null
}