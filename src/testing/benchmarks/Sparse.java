package src.testing.benchmarks;

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

public class Sparse extends UniversalActor  {
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
		Sparse instance = (Sparse)new Sparse(uan, ual).construct();
		{
			Object[] _arguments = { args };
			instance.send( new Message(instance, instance, "act", _arguments, null, null) );
		}
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Sparse(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Sparse.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Sparse(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Sparse.getReferenceByLocation(new UAL(ual)); }
	public Sparse(boolean o, UAN __uan)	{ super(o,__uan); }
	public Sparse(boolean o, UAL __ual)	{ super(o,__ual); }

	public Sparse(UAN __uan)	{ this(__uan, null); }
	public Sparse(UAL __ual)	{ this(null, __ual); }
	public Sparse()		{ this(null, null);  }
	public Sparse(UAN __uan, UAL __ual) {
		if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getReception().getLocation())) {
			createRemotely(__uan, __ual, "src.testing.benchmarks.Sparse");
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
		public Sparse self;
		public Sparse.State  _this = this;
		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "src.testing.benchmarks.Sparse$State" );
			addMethodsForClasses();
		}

		public void updateSelf(ActorReference actorReference) {
			self = (Sparse)actorReference;
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

		public void act(String[] arguments) {
			int size = 0;
			int edges = 0;
			int messageSendingDelay = 0;
			int messageSize = 0;
			int processingTime = 0;
			String nameServer = "";
			Vector theaters = new Vector();
			try {
				if (arguments.length!=6) {
					System.err.println("Usage: ");
					System.err.println("\tjava io.testing.benchmarks.Sparse <naming/theater information file> "+"<nodes> <sparse> <messageSendingDelay(ms)> <messageSize(bytes)> <processingTime(ms)>");
					System.exit(0);
				}
				FileInputStream file;
				byte input[];
				try {
					file = new FileInputStream(arguments[0]);
					input = new byte[file.available()];
					file.read(input);
					StringTokenizer st = new StringTokenizer(new String(input), "\n");
					nameServer = st.nextToken();
					while (st.hasMoreTokens()) {
						theaters.add(st.nextToken());
					}
				}
				catch (Exception e) {
					System.err.println("Error reading naming/theater information file: "+e);
					System.exit(0);
				}

				size = new Integer(arguments[1]).intValue();
				edges = new Integer(arguments[2]).intValue();
				messageSendingDelay = new Integer(arguments[3]).intValue();
				messageSize = new Integer(arguments[4]).intValue();
				processingTime = new Integer(arguments[5]).intValue();
			}
			catch (Exception e) {
				System.err.println("Usage: ");
				System.err.println("\tjava io.testing.benchmarks.Sparse <naming/theater information file> "+"<nodes> <edges> <messageSendingDelay(ms)> <messageSize(bytes)> <processingTime(ms)>");
				System.exit(0);
			}

			Token synchToken = new Token("synchToken");
			{
				Token token_2_0 = new Token();
				Token token_2_1 = new Token();
				Token token_2_2 = new Token();
				Token token_2_3 = new Token();
				Token token_2_4 = new Token();
				Token token_2_5 = new Token();
				Token token_2_6 = new Token();
				Token token_2_7 = new Token();
				Token token_2_8 = new Token();
				// standardOutput<-println("Creating a sparse actor graph with: ")
				{
					Object _arguments[] = { "Creating a sparse actor graph with: " };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, token_2_0 );
					__messages.add( __message );
				}
				// standardOutput<-println("\tnodes in sparse actor graph: "+size)
				{
					Object _arguments[] = { "\tnodes in sparse actor graph: "+size };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_0, token_2_1 );
					__messages.add( __message );
				}
				// standardOutput<-println("\twidth in sparse actor graph: "+edges)
				{
					Object _arguments[] = { "\twidth in sparse actor graph: "+edges };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_1, token_2_2 );
					__messages.add( __message );
				}
				// standardOutput<-println("\tdelay between message sends: "+messageSendingDelay+"(ms)")
				{
					Object _arguments[] = { "\tdelay between message sends: "+messageSendingDelay+"(ms)" };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_2, token_2_3 );
					__messages.add( __message );
				}
				// standardOutput<-println("\tsize of messages: "+messageSize+"(bytes)")
				{
					Object _arguments[] = { "\tsize of messages: "+messageSize+"(bytes)" };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_3, token_2_4 );
					__messages.add( __message );
				}
				// standardOutput<-println("\tprocessing intensity of a message: "+processingTime+"(ms)")
				{
					Object _arguments[] = { "\tprocessing intensity of a message: "+processingTime+"(ms)" };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_4, token_2_5 );
					__messages.add( __message );
				}
				// standardOutput<-println()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_5, token_2_6 );
					__messages.add( __message );
				}
				// standardOutput<-println("Using name server:")
				{
					Object _arguments[] = { "Using name server:" };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_6, token_2_7 );
					__messages.add( __message );
				}
				// standardOutput<-println("\t"+nameServer)
				{
					Object _arguments[] = { "\t"+nameServer };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_7, token_2_8 );
					__messages.add( __message );
				}
				// token synchToken = standardOutput<-println("Loading actor graph randomly on theater(s): ")
				{
					Object _arguments[] = { "Loading actor graph randomly on theater(s): " };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_8, synchToken );
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
			}
			Node[] nodes = new Node[size];
			for (int i = 0; i<size; i++){
				UAN uan = new UAN(nameServer+"benchmark/sparse_node_"+i);
				UAL ual = new UAL(theaters.get(i%theaters.size())+"benchmark/sparse_node_"+i);
				nodes[i] = ((Node)new Node(uan, ual).construct(messageSendingDelay, messageSize, processingTime));
			}
			{
				Token token_2_0 = new Token();
				Token token_2_1 = new Token();
				Token token_2_2 = new Token();
				Token token_2_3 = new Token();
				// standardOutput<-println("Generating links...")
				{
					Object _arguments[] = { "Generating links..." };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, token_2_0 );
					Object[] _propertyInfo = { synchToken };
					__message.setProperty( "waitfor", _propertyInfo );
					__messages.add( __message );
				}
				// join block
				token_2_1.setJoinDirector();
				for (int i = 0; i<edges; i++){
					int source = (int)(java.lang.Math.random()*size);
					int target = (int)(java.lang.Math.random()*size);
					{
						Token token_4_0 = new Token();
						// nodes[source]<-addReference(nodes[target])
						{
							Object _arguments[] = { nodes[target] };
							Message __message = new Message( self, nodes[source], "addReference", _arguments, token_2_0, token_4_0 );
							__messages.add( __message );
						}
						// standardOutput<-println("\tGenerated link from node_"+source+" to node_"+target)
						{
							Object _arguments[] = { "\tGenerated link from node_"+source+" to node_"+target };
							Message __message = new Message( self, standardOutput, "println", _arguments, token_4_0, token_2_1 );
							__messages.add( __message );
						}
					}
				}
				token_2_1.createJoinDirector();
				// standardOutput<-println("Starting computation...")
				{
					Object _arguments[] = { "Starting computation..." };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_1, token_2_2 );
					__messages.add( __message );
				}
				// join block
				token_2_3.setJoinDirector();
				for (int i = 0; i<size; i++){
					{
						Token token_4_0 = new Token();
						// nodes[i]<-startSending()
						{
							Object _arguments[] = {  };
							Message __message = new Message( self, nodes[i], "startSending", _arguments, token_2_2, token_4_0 );
							__messages.add( __message );
						}
						// standardOutput<-println("\tnode_"+i+" is now processing.")
						{
							Object _arguments[] = { "\tnode_"+i+" is now processing." };
							Message __message = new Message( self, standardOutput, "println", _arguments, token_4_0, token_2_3 );
							__messages.add( __message );
						}
					}
				}
				token_2_3.createJoinDirector();
				// standardOutput<-println("finished.")
				{
					Object _arguments[] = { "finished." };
					Message __message = new Message( self, standardOutput, "println", _arguments, token_2_3, null );
					__messages.add( __message );
				}
			}
		}
	}
}