package synergynet3.projector;

import java.net.SocketException;
import java.util.ArrayList;

import synergynet3.additionalitems.AdditionalItemUtilities;
import synergynet3.cluster.SynergyNetCluster;
import synergynet3.projector.network.ProjectorSync;
import synergynet3.projector.network.ProjectorTransferUtilities;
import synergynet3.projector.network.messages.ContentTransferedMessage;
import synergynet3.projector.web.ProjectorDeviceControl;
import synergynet3.web.core.AppSystemControlComms;
import synergynet3.web.shared.DevicesSelected;
import multiplicity3.appsystem.IMultiplicityApp;
import multiplicity3.appsystem.IQueueOwner;
import multiplicity3.appsystem.MultiplicityClient;
import multiplicity3.config.identity.IdentityConfigPrefsItem;
import multiplicity3.csys.MultiplicityEnvironment;
import multiplicity3.csys.items.item.IItem;
import multiplicity3.csys.stage.IStage;
import multiplicity3.input.MultiTouchInputComponent;

/** Class to be run to produce a projection environment.*/
public class SynergyNetProjector implements IMultiplicityApp {	
	
	private IStage stage;
	private ProjectorSync projectorSync;
	
	@Override
	public void shouldStart(MultiTouchInputComponent input, IQueueOwner iqo) {
		stage = MultiplicityEnvironment.get().getLocalStages().get(0);
		
		AdditionalItemUtilities.loadAdditionalItems(stage);
		
		String projectorID = SynergyNetCluster.get().getIdentity();
	
		ProjectorDeviceControl projectorDeviceController = new ProjectorDeviceControl(projectorID);
		projectorSync = new ProjectorSync(projectorDeviceController, this);	
		
		new TopRightProjectorMenu(this);	
		new BottomRightProjectorMenu(this);	
	}	
		
	/**
	 * Send current contents of the projector a the list of tables provided.
	 * 
	 * 	* @param tablesToSendTo
	 *         List of table identities to send contents to.
	 */
	public void sendProjectedContents(final String[] tablesToSendTo) {	
		Thread cachingThread = new Thread(new Runnable() {	  
			public void run() {	  
				ArrayList<ContentTransferedMessage> messages = ProjectorTransferUtilities.get().prepareToTransferAllContents(tablesToSendTo);		
				for (String table: tablesToSendTo){
					if (table.equals(DevicesSelected.ALL_TABLES_ID)){
						AppSystemControlComms.get().allTablesReceiveContent(messages);
					}else{
						AppSystemControlComms.get().specificTableReceiveContent(messages, table);
					}
				}	
			}    					
		});	
		cachingThread.start();
	}	
	
	/**
	 * Align all contents currently displayed by the projector.
	 */
	public void alignContents(){
		for (IItem item: ProjectorTransferUtilities.get().getContents().values()){
			item.setRelativeRotation(0);
		}
	}
	
	/**
	 * Clear all contents currently displayed by the projector.
	 */
	public void clearContents(){
		for (IItem item: ProjectorTransferUtilities.get().getContents().values()){
			stage.removeItem(item);
		}
		ProjectorTransferUtilities.get().clearContents();
	}
	
	/**
	* Retrieves details from the supplied message to recreate the transfered item
	* 
	* @param message
	*         Structured message detailing the details of an item's arrival.
	* 
	*/
	public void onContentArrival(ArrayList<ContentTransferedMessage> messages){	
		ProjectorTransferUtilities.get().onContentArrival(messages);
	}	
	
	/**
	 * Actions to be performed when the SynergyNetProjector window is closed.
	 */
	@Override
	public void onDestroy() {
		if (projectorSync != null)projectorSync.stop();
		SynergyNetCluster.get().shutdown();
	}
	
	@Override
	public String getFriendlyAppName() {
		return "SynergyNetProjector";
	}

	@Override
	public void shouldStop() {}
	
	public static void main(String[] args) throws SocketException {
		if(args.length > 0) {
			IdentityConfigPrefsItem idprefs = new IdentityConfigPrefsItem();			
			idprefs.setID(args[0]);
		}		
		
		MultiplicityClient client = MultiplicityClient.get();
		client.start();
		SynergyNetProjector app = new SynergyNetProjector();
		client.setCurrentApp(app);		
	}

	public IStage getStage() {
		return stage;
	}

	public void setStage(IStage stage) {
		this.stage = stage;
	}
	
}