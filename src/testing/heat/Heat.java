package src.testing.heat;

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

import java.io.*;
import java.util.*;
import src.resources.ObservationCollector;

public class Heat extends UniversalActor  {
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
		Heat instance = (Heat)new Heat(uan, ual).construct();
		{
			Object[] _arguments = { args };
			instance.send( new Message(instance, instance, "act", _arguments, null, null) );
		}
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Heat(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Heat.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Heat(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Heat.getReferenceByLocation(new UAL(ual)); }
	public Heat(boolean o, UAN __uan)	{ super(o,__uan); }
	public Heat(boolean o, UAL __ual)	{ super(o,__ual); }

	public Heat(UAN __uan)	{ this(__uan, null); }
	public Heat(UAL __ual)	{ this(null, __ual); }
	public Heat()		{ this(null, null);  }
	public Heat(UAN __uan, UAL __ual) {
		if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getReception().getLocation())) {
			createRemotely(__uan, __ual, "src.testing.heat.Heat");
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
		public Heat self;
		public Heat.State  _this = this;
		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "src.testing.heat.Heat$State" );
			addMethodsForClasses();
		}

		public void updateSelf(ActorReference actorReference) {
			self = (Heat)actorReference;
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

		int iterations;
		int x;
		int y;
		int number_actors;
		long initialTime;
		String filename;
		Collector collector;
		HeatWorker[] workers;
		public void produceFile(LowMemoryArray data) {
			try {
				PrintWriter out = new PrintWriter(new FileWriter(filename));
				out.println(x+" "+y);
				for (int i = 0; i<x; i++){
					for (int j = 0; j<y; j++){
						try {
							out.println(data.get(i, j));
						}
						catch (Exception e) {
continue;						}

					}
				}
				out.flush();
				out.close();
			}
			catch (IOException ioe) {
				{
					// standardOutput<-println("[error] Can't open the file "+filename+" for writing.")
					{
						Object _arguments[] = { "[error] Can't open the file "+filename+" for writing." };
						Message __message = new Message( self, standardOutput, "println", _arguments, null, null );
						__messages.add( __message );
					}
				}
			}

		}
		public void doWork(int iterations, String[] theaters, String name_server) {
			int rowsPerActor = x/number_actors;
			workers = new HeatWorker[number_actors];
			collector = ((Collector)new Collector().construct(number_actors, iterations));
			if (System.getProperty("random")!=null) {
				java.util.Random rand = new java.util.Random(System.currentTimeMillis());
				for (int i = 0; i<number_actors; i++){
					int target_theater = rand.nextInt(theaters.length-1);
					System.err.println("Creating worker: "+i+" at: "+theaters[target_theater]+"heatworker_"+i);
					workers[i] = ((HeatWorker)new HeatWorker(new UAN(name_server+"heatworker_"+i), new UAL(theaters[target_theater]+"heatworker_"+i)).construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(i), collector));
				}
			}
			else if (theaters!=null) {
				int a = 0;
				double actors_per_theater = (double)number_actors/(double)theaters.length;
				for (int i = 0; i<theaters.length; i++){
					for (int j = (int)java.lang.Math.round(i*actors_per_theater); j<java.lang.Math.round((i+1)*actors_per_theater); j++){
						System.err.println("Creating worker: "+a+" at: "+theaters[i]+"heatworker_"+a);
						workers[a] = ((HeatWorker)new HeatWorker(new UAN(name_server+"heatworker_"+a), new UAL(theaters[i]+"heatworker_"+a)).construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(a), collector));
						a++;
					}
				}
			}
			else {
				for (int a = 0; a<number_actors; a++){
					workers[a] = ((HeatWorker)new HeatWorker().construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(a), collector));
				}
			}
			if (number_actors>1) {
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					// standardOutput<-println("Connecting worker neighbors")
					{
						Object _arguments[] = { "Connecting worker neighbors" };
						Message __message = new Message( self, standardOutput, "println", _arguments, null, token_3_0 );
						__messages.add( __message );
					}
					// join block
					token_3_1.setJoinDirector();
					for (int a = 0; a<number_actors; a++){
						if (a>0) 						{
							// workers[a]<-connectTop(workers[a-1])
							{
								Object _arguments[] = { workers[a-1] };
								Message __message = new Message( self, workers[a], "connectTop", _arguments, token_3_0, token_3_1 );
								__messages.add( __message );
							}
						}
						if (a<number_actors-1) 						{
							// workers[a]<-connectBottom(workers[a+1])
							{
								Object _arguments[] = { workers[a+1] };
								Message __message = new Message( self, workers[a], "connectBottom", _arguments, token_3_0, token_3_1 );
								__messages.add( __message );
							}
						}
					}
					token_3_1.createJoinDirector();
					// doWork2(theaters)
					{
						Object _arguments[] = { theaters };
						Message __message = new Message( self, self, "doWork2", _arguments, token_3_1, null );
						__messages.add( __message );
					}
				}
			}
			else {
				{
					// doWork2(theaters)
					{
						Object _arguments[] = { theaters };
						Message __message = new Message( self, self, "doWork2", _arguments, null, null );
						__messages.add( __message );
					}
				}
			}
		}
		public void doWork2(String[] theaters) {
			String observationUAN = System.getProperty("observationUAN");
			String observationUAL = System.getProperty("observationUAL");
			if (observationUAN!=null) {
				ObservationCollector observationCollector = ((ObservationCollector)new ObservationCollector(new UAN(observationUAN), new UAL(observationUAL)).construct(30000, 20));
				{
					Token token_3_0 = new Token();
					// join block
					token_3_0.setJoinDirector();
					for (int i = 0; i<workers.length; i++){
						{
							// observationCollector<-register_actor(workers[i])
							{
								Object _arguments[] = { workers[i] };
								Message __message = new Message( self, observationCollector, "register_actor", _arguments, null, token_3_0 );
								__messages.add( __message );
							}
						}
						System.err.println("registering actor: "+workers[i].getUAN());
					}
					for (int i = 0; i<theaters.length; i++){
						{
							// observationCollector<-register_location(theaters[i])
							{
								Object _arguments[] = { theaters[i] };
								Message __message = new Message( self, observationCollector, "register_location", _arguments, null, token_3_0 );
								__messages.add( __message );
							}
						}
						System.err.println("registering location: "+theaters[i]);
					}
					token_3_0.createJoinDirector();
					// observationCollector<-initiate_collection()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, observationCollector, "initiate_collection", _arguments, token_3_0, null );
						Object[] _propertyInfo = { new Integer(30000) };
						__message.setProperty( "delay", _propertyInfo );
						__messages.add( __message );
					}
				}
			}
			for (int i = 0; i<number_actors; i++){
				{
					// workers[i]<-startWork()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, workers[i], "startWork", _arguments, null, null );
						__messages.add( __message );
					}
				}
			}
			initialTime = System.currentTimeMillis();
			{
				// standardOutput<-println("Starting the computation at: "+initialTime)
				{
					Object _arguments[] = { "Starting the computation at: "+initialTime };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( __message );
				}
			}
		}
		Vector results = new Vector();
		int work_received = 0;
		public void workFinished() {
			work_received++;
			if (work_received==number_actors) {
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					// collector<-printTimes()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, collector, "printTimes", _arguments, null, token_3_0 );
						__messages.add( __message );
					}
					// endTimer()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, self, "endTimer", _arguments, token_3_0, token_3_1 );
						__messages.add( __message );
					}
					// standardOutput<-println("Computation is done, received work from: "+work_received+" actors!")
					{
						Object _arguments[] = { "Computation is done, received work from: "+work_received+" actors!" };
						Message __message = new Message( self, standardOutput, "println", _arguments, token_3_1, null );
						__messages.add( __message );
					}
				}
			}
		}
		public void workFinished(LowMemoryArray work) {
			results.add(work);
			work_received++;
			if (work_received==number_actors) {
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					Token token_3_2 = new Token();
					// endTimer()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, self, "endTimer", _arguments, null, token_3_0 );
						__messages.add( __message );
					}
					// composeMatrix()
					{
						Object _arguments[] = {  };
						Message __message = new Message( self, self, "composeMatrix", _arguments, token_3_0, token_3_1 );
						__messages.add( __message );
					}
					// produceFile(token)
					{
						Object _arguments[] = { token_3_1 };
						Message __message = new Message( self, self, "produceFile", _arguments, token_3_1, token_3_2 );
						__messages.add( __message );
					}
					// standardOutput<-println("Computation is done, received work from: "+work_received+" actors!")
					{
						Object _arguments[] = { "Computation is done, received work from: "+work_received+" actors!" };
						Message __message = new Message( self, standardOutput, "println", _arguments, token_3_2, null );
						__messages.add( __message );
					}
				}
			}
		}
		public void actor_split() {
			System.err.println("Farmer received split!");
			number_actors++;
		}
		public void actor_merged() {
			System.err.println("Farmer received merge!");
			number_actors--;
		}
		public void endTimer() {
			long finalTime = System.currentTimeMillis();
			long runningTime = finalTime-initialTime;
			double processingRate = 1.0*iterations*(x-2)*(y-2)/runningTime;
			System.out.println("Time for "+iterations+" iterations on "+x+","+y+" grid: "+runningTime+"ms.");
			System.out.println("number of workers: "+number_actors);
		}
		public LowMemoryArray composeMatrix() {
			LowMemoryArray cresults;
			LowMemoryArray m = new LowMemoryArray(x, y);
			int i = 0;
			for (int a = 0; a<results.size(); a++){
				cresults = (LowMemoryArray)results.get(a);
				for (int j = 0; j<cresults.x; j++){
					for (int k = 0; k<y; k++){
						m.set((cresults.y*a)+j, k, cresults.get(j, k));
					}
					i++;
				}
			}
			{
				// standardOutput<-println("Returned matrix size:"+m.x+","+m.y)
				{
					Object _arguments[] = { "Returned matrix size:"+m.x+","+m.y };
					Message __message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( __message );
				}
			}
			return m;
		}
		public void act(String args[]) {
			if (args.length!=7&&args.length!=5) {
				System.err.println("Incorrect arguments.");
				System.err.println("Usage:");
				System.err.println("java heat.Heat [<theaters_file> <name_server>] <iterations> <x> <y> <number of actors> <filename>");
				System.exit(0);
			}
			String[] theaters = null;
			String name_server = null;
			int i = 0;
			if (args.length==7) {
				String theater_file = args[0];
				try {
					BufferedReader in = new BufferedReader(new FileReader(theater_file));
					String line = in.readLine();
					Vector theaters_vector = new Vector();
					while (line!=null) {
						theaters_vector.add(line);
						line = in.readLine();
					}
					theaters = new String[theaters_vector.size()];
					for (int j = 0; j<theaters_vector.size(); j++){
						theaters[j] = "rmsp://"+(String)theaters_vector.get(j)+"/";
					}
				}
				catch (Exception e) {
					System.err.println("Error reading theaters file: "+e);
					e.printStackTrace();
				}

				name_server = args[1];
				i = 2;
			}
			iterations = Integer.parseInt(args[i]);
			i++;
			x = Integer.parseInt(args[i]);
			i++;
			y = Integer.parseInt(args[i]);
			i++;
			number_actors = Integer.parseInt(args[i]);
			i++;
			filename = args[i];
			x += 2;
			y += 2;
			{
				// doWork(new Integer(iterations), theaters, name_server)
				{
					Object _arguments[] = { new Integer(iterations), theaters, name_server };
					Message __message = new Message( self, self, "doWork", _arguments, null, null );
					__messages.add( __message );
				}
			}
			{
				// ((Heat)self)<-keepAlive()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, ((Heat)self), "keepAlive", _arguments, null, null );
					Object[] _propertyInfo = { new Integer(60000) };
					__message.setProperty( "delay", _propertyInfo );
					__messages.add( __message );
				}
			}
		}
		public void keepAlive() {
			System.out.println("Keep alive!");
			{
				// ((Heat)self)<-keepAlive()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, ((Heat)self), "keepAlive", _arguments, null, null );
					Object[] _propertyInfo = { new Integer(60000) };
					__message.setProperty( "delay", _propertyInfo );
					__messages.add( __message );
				}
			}
		}
	}
}