package org.eclipse.remail;

import org.eclipse.remail.couchdb.util.CouchDBCreator;
import org.eclipse.remail.daemons.ChangeViewDaemon;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emailrecommender";
	public static final String connString = "jdbc:postgresql://localhost:5432/mydb";
	public static final String login = "vitus";
	public static final String password = "ubo4tore";
	public static final String mailSource = "mbox";
	public static final String mboxPath = "/Users/vita/Library/Thunderbird/Profiles/40tfoa42.default/"
			+ "Mail/Local Folders/org_freenetproject_devl";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator()
	{
		/*
		 * Creates the thread responsible for updating the views
		 * depending on the active class editor
		 */
		Thread gene = new Thread(new ChangeViewDaemon());
		gene.setDaemon(true);
		gene.setPriority(3); //Priorities range: 1-10 default 5
		gene.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

}
