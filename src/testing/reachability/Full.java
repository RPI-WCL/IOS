package src.testing.reachability;

// Import declarations generated by the SALSA compiler, do not modify.
import java.io.IOException;
import java.util.Vector;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import salsa.language.Actor;
import salsa.language.ActorReference;
import salsa.language.Message;
import salsa.language.RunTime;
import salsa.language.ServiceFactory;
import salsa.language.Token;
import salsa.language.exceptions.*;
import salsa.language.exceptions.CurrentContinuationException;

import salsa.language.UniversalActor;

import salsa.naming.UAN;
import salsa.naming.UAL;
import salsa.naming.MalformedUALException;
import salsa.naming.MalformedUANException;

// End SALSA compiler generated import delcarations.

import java.io.FileInputStream;
import java.util.Vector;
import java.util.StringTokenizer;
import src.protocol_agent.ATSProtocolActor;

public class Full extends UniversalActor  {
	public static void main(String args[]) {
		UAN uan = null;
		UAL ual = null;
		if (System.getProperty("uan") != null) {
			uan = new UAN( System.getProperty("uan") );
			ServiceFactory.getTheater();
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("ual") != null) {
			ual = new UAL( System.getProperty("ual") );

			if (uan == null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an actor to have a ual at runtime without a uan.");
				System.err.println("	To give an actor a specific ual at runtime, use the identifier system property.");
				System.exit(0);
			}
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("identifier") != null) {
			if (ual != null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an identifier and a ual with system properties when creating an actor.");
				System.exit(0);
			}
			ual = new UAL( ServiceFactory.getReception().getLocation() + System.getProperty("identifier"));
		}
		Full instance = (Full)new Full(uan, ual).construct();
		{
			Object[] _arguments = { args };
			instance.send( new Message(instance, instance, "act", _arguments, null, null) );
		}
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Full(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Full.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Full(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Full.getReferenceByLocation(new UAL(ual)); }
	public Full(boolean o, UAN __uan)	{ super(o,__uan); }
	public Full(boolean o, UAL __ual)	{ super(o,__ual); }

	public Full(UAN __uan)	{ this(__uan, null); }
	public Full(UAL __ual)	{ this(null, __ual); }
	public Full()		{ this(null, null);  }
	public Full(UAN __uan, UAL __ual) {
		if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getReception().getLocation())) {
			createRemotely(__uan, __ual, "src.testing.reachability.Full");
		} else {
			State state = new State(__uan, __ual);
			state.updateSelf(this);
			ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(), state);
			if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
		}
	}

	public UniversalActor construct() {
		Object[] __arguments = { };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor.State {
		public Full self;
		public Full.State  _this = this;
		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "src.testing.reachability.Full$State" );
			addMethodsForClasses();
		}

		public void updateSelf(ActorReference actorReference) {
			self = (Full)actorReference;
			self.setUAN(getUAN());
			self.setUAL(getUAL());
			super.updateSelf(self);
		}

		public void construct() {}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Throwable exception = null;

			if (matches != null) {
				for (int i = 0; i < matches.length; i++) {
					try {
						if (matches[i].getParameterTypes().length != message.getArguments().length) continue;
						returnValue = matches[i].invoke(this, message.getArguments());
					} catch (Exception e) {
						if (e.getCause() instanceof CurrentContinuationException) {
							sendGeneratedMessages();
							return;
						} else if (e instanceof InvocationTargetException) {
							sendGeneratedMessages();
							exception = e.getCause();
							break;
						} else {
							continue;
						}
					}
					sendGeneratedMessages();
					currentMessage.resolveContinuations(returnValue);
					return;
				}
			}

			String exceptionMessage = "Message processing exception:\n";
			if (message.getSource() != null) {
				exceptionMessage += "\tSent by: " + message.getSource().toString() + "\n";
			} else exceptionMessage += "\tSent by: unknown\n";
			exceptionMessage += "\tReceived by actor: " + toString() + "\n";
			exceptionMessage += "\tMessage: " + message.toString() + "\n";
			if (exception == null) {
				if (matches == null) {
					exceptionMessage += "\tNo methods with the same name found.\n";
				System.err.println(exceptionMessage);
					return;
				}
				exceptionMessage += "\tDid not match any of the following: \n";
				for (int i = 0; i < matches.length; i++) {
					exceptionMessage += "\t\tMethod: " + matches[i].getName() + "( ";
					Class[] parTypes = matches[i].getParameterTypes();
					for (int j = 0; j < parTypes.length; j++) {
						exceptionMessage += parTypes[j].getName() + " ";
					}
					exceptionMessage += ")\n";
				}
				System.err.println(exceptionMessage);
				return;
			} else {
				exceptionMessage += "\tThrew exception: " + exception + "\n";
				System.err.println(exceptionMessage);
				exception.printStackTrace();
			}
		}

		Vector theaters = new Vector();
		public void act(String[] arguments) {
			try {
				if (arguments.length!=1) {
					this.errorMessage();
				}
				FileInputStream file;
				byte input[];
				try {
					file = new FileInputStream(arguments[0]);
					input = new byte[file.available()];
					file.read(input);
					StringTokenizer st = new StringTokenizer(new String(input), "\n");
					while (st.hasMoreTokens()) {
						theaters.add(st.nextToken());
					}
				}
				catch (Exception e) {
					System.err.println("Error reading naming/theater information file: "+e);
					System.exit(0);
				}

			}
			catch (Exception e) {
				this.errorMessage();
			}

			Token synchToken = new Token("synchToken");
			{
				// token synchToken = standardOutput<-println("Connecting the following theaters fully.")
				{
					Object _arguments[] = { "Connecting the following theaters fully." };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, synchToken );
					__messages.add( __message );
				}
			}
			for (int i = 0; i<theaters.size(); i++){
				{
					// synchToken = standardOutput<-println("\t"+(String)theaters.get(i))
					Token synchToken_next = new Token("<-_next");
					{
						Object _arguments[] = { "\t"+(String)theaters.get(i) };
						Message __message = new Message( self, standardOutput, "println", _arguments, null, synchToken_next );
						Object[] _propertyInfo = { synchToken };
						__message.setProperty( "waitfor", _propertyInfo );
						__messages.add( __message );
					}
					synchToken = synchToken_next;
				}
			}
			{
				Token token_2_1 = new Token();
				Token token_2_2 = new Token();
				// synchToken = standardOutput<-println()
				Token synchToken_next = new Token("<-_next");
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, synchToken_next );
					Object[] _propertyInfo = { synchToken };
					__message.setProperty( "waitfor", _propertyInfo );
					__messages.add( __message );
				}
				synchToken = synchToken_next;
				// standardOutput<-println("Generating links...")
				{
					Object _arguments[] = { "Generating links..." };
					Message __message = new Message( self, standardOutput, "println", _arguments, synchToken_next, token_2_1 );
					__messages.add( __message );
				}
				// join block
				token_2_2.setJoinDirector();
				for (int i = 0; i<theaters.size(); i++){
					String theaterName1 = (String)theaters.get(i);
					ATSProtocolActor protocolActor1 = (ATSProtocolActor)ATSProtocolActor.getReferenceByLocation("rmsp://"+theaterName1+"/io/protocolActor");
					for (int j = 0; j<theaters.size(); j++){
						if (j!=i) {
							String theaterName2 = (String)theaters.get(j);
							ATSProtocolActor protocolActor2 = (ATSProtocolActor)ATSProtocolActor.getReferenceByLocation("rmsp://"+theaterName2+"/io/protocolActor");
							{
								Token token_6_0 = new Token();
								// protocolActor1<-addPeer(protocolActor2)
								{
									Object _arguments[] = { protocolActor2 };
									Message __message = new Message( self, protocolActor1, "addPeer", _arguments, token_2_1, token_6_0 );
									__messages.add( __message );
								}
								// standardOutput<-println("\tGenerated link from "+theaterName1+" to "+theaterName2)
								{
									Object _arguments[] = { "\tGenerated link from "+theaterName1+" to "+theaterName2 };
									Message __message = new Message( self, standardOutput, "println", _arguments, token_6_0, token_2_2 );
									__messages.add( __message );
								}
							}
						}
					}
				}
				token_2_2.createJoinDirector();
				// standardOutput<-println("finished.")
				{
					Object _arguments[] = { "finished." };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_2, null );
					__messages.add( __message );
				}
			}
		}
		public void errorMessage() {
			System.err.println("Usage: ");
			System.err.println("\tjava io.testing.reachability.Full <theater information file>");
			System.exit(0);
		}
	}
}