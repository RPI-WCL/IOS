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

import java.net.*;
import java.io.*;
import java.util.*;

public class Linpack extends UniversalActor  {
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
		Linpack instance = (Linpack)new Linpack(uan, ual).construct();
		{
			Object[] _arguments = { args };
			instance.send( new Message(instance, instance, "act", _arguments, null, null) );
		}
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Linpack(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Linpack.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Linpack(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Linpack.getReferenceByLocation(new UAL(ual)); }
	public Linpack(boolean o, UAN __uan)	{ super(o,__uan); }
	public Linpack(boolean o, UAL __ual)	{ super(o,__ual); }

	public Linpack(UAN __uan)	{ this(__uan, null); }
	public Linpack(UAL __ual)	{ this(null, __ual); }
	public Linpack()		{ this(null, null);  }
	public Linpack(UAN __uan, UAL __ual) {
		if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getReception().getLocation())) {
			createRemotely(__uan, __ual, "src.testing.benchmarks.Linpack");
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
		public Linpack self;
		public Linpack.State  _this = this;
		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "src.testing.benchmarks.Linpack$State" );
			addMethodsForClasses();
		}

		public void updateSelf(ActorReference actorReference) {
			self = (Linpack)actorReference;
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

		double second_orig = -1;
		double initialTime, finalTime, total;
		double norma;
		int info;
		public void act(String[] args) {
			Linpack l = ((Linpack)new Linpack().construct());
			{
				// l<-run_benchmark()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, l, "run_benchmark", _arguments, null, null );
					__messages.add( __message );
				}
			}
		}
		public void run_benchmark() {
			double a[][] = new double[200][201];
			double b[] = new double[200];
			double x[] = new double[200];
			int n, i, ntimes, info, lda, ldaa;
			int ipvt[] = new int[200];
			lda = 201;
			ldaa = 200;
			n = 100;
			{
				Token token_2_0 = new Token();
				Token token_2_1 = new Token();
				Token token_2_2 = new Token();
				Token token_2_3 = new Token();
				Token token_2_4 = new Token();
				Token token_2_5 = new Token();
				// matgen(a, new Integer(lda), new Integer(n), b)
				{
					Object _arguments[] = { a, new Integer(lda), new Integer(n), b };
					Message __message = new Message( self, self, "matgen", _arguments, null, token_2_0 );
					__messages.add( __message );
				}
				// startTimer()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, self, "startTimer", _arguments, token_2_0, token_2_1 );
					__messages.add( __message );
				}
				// dgefa(a, b, new Integer(lda), new Integer(n), ipvt, new Integer(0))
				{
					Object _arguments[] = { a, b, new Integer(lda), new Integer(n), ipvt, new Integer(0) };
					Message __message = new Message( self, self, "dgefa", _arguments, token_2_1, token_2_2 );
					__messages.add( __message );
				}
				// endTimer()
				{
					Object _arguments[] = {  };
					Message __message = new Message( self, self, "endTimer", _arguments, token_2_2, token_2_3 );
					__messages.add( __message );
				}
				// negate(b, new Integer(n))
				{
					Object _arguments[] = { b, new Integer(n) };
					Message __message = new Message( self, self, "negate", _arguments, token_2_3, token_2_4 );
					__messages.add( __message );
				}
				// dmxpy(new Integer(n), b, new Integer(n), new Integer(lda), x, a)
				{
					Object _arguments[] = { new Integer(n), b, new Integer(n), new Integer(lda), x, a };
					Message __message = new Message( self, self, "dmxpy", _arguments, token_2_4, token_2_5 );
					__messages.add( __message );
				}
				// computeFlops(new Integer(n), b, x)
				{
					Object _arguments[] = { new Integer(n), b, x };
					Message __message = new Message( self, self, "computeFlops", _arguments, token_2_5, currentMessage.getContinuationToken() );
					__message.setJoinPosition(currentMessage.getJoinPosition());
					__messages.add( __message );
				}
				throw new CurrentContinuationException();
			}
		}
		final double abs(double d) {
			return (d>=0)?d:-d;
		}
		public void startTimer() {
			if (second_orig==-1) {
				second_orig = System.currentTimeMillis();
			}
			initialTime = (System.currentTimeMillis()-second_orig)/1000;
		}
		public void endTimer() {
			if (second_orig==-1) {
				second_orig = System.currentTimeMillis();
			}
			finalTime = (System.currentTimeMillis()-second_orig)/1000;
			total = finalTime-initialTime;
		}
		public void negate(double b[], Integer n) {
			for (int i = 0; i<n.intValue(); i++){
				b[i] = -b[i];
			}
		}
		final void computeFlops(Integer on, double[] b, double[] x) {
			int n = on.intValue();
			double mflops_result = 0.0;
			double residn_result = 0.0;
			double time_result = 0.0;
			double eps_result = 0.0;
			double cray, ops, normx;
			double resid, time;
			cray = .056;
			ops = (2.0e0*(n*n*n))/3.0+2.0*(n*n);
			resid = 0.0;
			normx = 0.0;
			for (int i = 0; i<n; i++){
				resid = (resid>this.abs(b[i]))?resid:this.abs(b[i]);
				normx = (normx>this.abs(x[i]))?normx:this.abs(x[i]);
			}
			eps_result = this.epslon((double)1.0);
			residn_result = resid/(n*norma*normx*eps_result);
			residn_result += 0.005;
			residn_result = (int)(residn_result*100);
			residn_result /= 100;
			time_result = total;
			time_result += 0.005;
			time_result = (int)(time_result*100);
			time_result /= 100;
			mflops_result = ops/(1.0e6*total);
			mflops_result += 0.0005;
			mflops_result = (int)(mflops_result*1000);
			mflops_result /= 1000;
			System.out.println("Mflops/s: "+mflops_result+"  Time: "+time_result+" secs"+"  Norm Res: "+residn_result+"  Precision: "+eps_result);
		}
		final void dgefa(double[][] a, double[] b, Integer olda, Integer on, int[] ipvt, Integer ojob) {
			double[] col_k, col_j;
			double t;
			int j, k, kp1, l, nm1;
			int info;
			int lda = olda.intValue();
			int n = on.intValue();
			int job = ojob.intValue();
			int kb;
			info = 0;
			nm1 = n-1;
			if (nm1>=0) {
				for (k = 0; k<nm1; k++){
					col_k = a[k];
					kp1 = k+1;
					l = this.idamax(new Integer(n-k), col_k, new Integer(k), new Integer(1))+k;
					ipvt[k] = l;
					if (col_k[l]!=0) {
						if (l!=k) {
							t = col_k[l];
							col_k[l] = col_k[k];
							col_k[k] = t;
						}
						t = -1.0/col_k[k];
{
							int i, nincx, num, incx = 1;
							num = n-(kp1);
							if (num>0) {
								if (incx!=1) {
									nincx = n*incx;
									for (i = 0; i<nincx; i += incx)col_k[i+kp1] *= t;
								}
								else {
									for (i = 0; i<num; i++)col_k[i+kp1] *= t;
								}
							}
						}
						for (j = kp1; j<n; j++){
							col_j = a[j];
							t = col_j[l];
							if (l!=k) {
								col_j[l] = col_j[k];
								col_j[k] = t;
							}
{
								int i, ix, iy;
								int num = n-(kp1);
								double da = t;
								int incx = 1;
								int incy = 1;
								int dx_off = kp1;
								double dx[] = col_k;
								double dy[] = col_j;
								int dy_off = kp1;
								if ((num>0)&&(da!=0)) {
									if (incx!=1||incy!=1) {
										ix = 0;
										iy = 0;
										if (incx<0) ix = (-n+1)*incx;
										if (incy<0) iy = (-n+1)*incy;
										for (i = 0; i<num; i++){
											dy[iy+dy_off] += da*dx[ix+dx_off];
											ix += incx;
											iy += incy;
										}
										return;
									}
									else {
										for (i = 0; i<num; i++)dy[i+dy_off] += da*dx[i+dx_off];
									}
								}
							}
						}
					}
					else {
						info = k;
					}
				}
			}
			ipvt[n-1] = n-1;
			if (a[(n-1)][(n-1)]==0) info = n-1;
			nm1 = n-1;
			if (job==0) {
				if (nm1>=1) {
					for (k = 0; k<nm1; k++){
						l = ipvt[k];
						t = b[l];
						if (l!=k) {
							b[l] = b[k];
							b[k] = t;
						}
						kp1 = k+1;
{
							int i, ix, iy;
							int num = n-(kp1);
							double da = t;
							int incx = 1;
							int incy = 1;
							int dx_off = kp1;
							double dx[] = a[k];
							double dy[] = b;
							int dy_off = kp1;
							if ((num>0)&&(da!=0)) {
								if (incx!=1||incy!=1) {
									ix = 0;
									iy = 0;
									if (incx<0) ix = (-n+1)*incx;
									if (incy<0) iy = (-n+1)*incy;
									for (i = 0; i<num; i++){
										dy[iy+dy_off] += da*dx[ix+dx_off];
										ix += incx;
										iy += incy;
									}
									return;
								}
								else {
									for (i = 0; i<num; i++)dy[i+dy_off] += da*dx[i+dx_off];
								}
							}
						}
					}
				}
				for (kb = 0; kb<n; kb++){
					k = n-(kb+1);
					b[k] /= a[k][k];
					t = -b[k];
{
						int i, ix, iy;
						int num = k;
						double da = t;
						int incx = 1;
						int incy = 1;
						int dx_off = 0;
						double dx[] = a[k];
						double dy[] = b;
						int dy_off = 0;
						if ((num>0)&&(da!=0)) {
							if (incx!=1||incy!=1) {
								ix = 0;
								iy = 0;
								if (incx<0) ix = (-n+1)*incx;
								if (incy<0) iy = (-n+1)*incy;
								for (i = 0; i<num; i++){
									dy[iy+dy_off] += da*dx[ix+dx_off];
									ix += incx;
									iy += incy;
								}
								return;
							}
							else {
								for (i = 0; i<num; i++)dy[i+dy_off] += da*dx[i+dx_off];
							}
						}
					}
				}
			}
			else {
				for (k = 0; k<n; k++){
					t = this.ddot(k, a[k], 0, 1, b, 0, 1);
					b[k] = (b[k]-t)/a[k][k];
				}
				if (nm1>=1) {
					for (kb = 1; kb<nm1; kb++){
						k = n-(kb+1);
						kp1 = k+1;
						b[k] += this.ddot(n-kp1, a[k], kp1, 1, b, kp1, 1);
						l = ipvt[k];
						if (l!=k) {
							t = b[l];
							b[l] = b[k];
							b[k] = t;
						}
					}
				}
			}
		}
		final double ddot(int n, double[] dx, int dx_off, int incx, double[] dy, int dy_off, int incy) {
			double dtemp;
			int i, ix, iy;
			dtemp = 0;
			if (n>0) {
				if (incx!=1||incy!=1) {
					ix = 0;
					iy = 0;
					if (incx<0) ix = (-n+1)*incx;
					if (incy<0) iy = (-n+1)*incy;
					for (i = 0; i<n; i++){
						dtemp += dx[ix+dx_off]*dy[iy+dy_off];
						ix += incx;
						iy += incy;
					}
				}
				else {
					for (i = 0; i<n; i++)dtemp += dx[i+dx_off]*dy[i+dy_off];
				}
			}
			return (dtemp);
		}
		final int idamax(Integer on, double[] dx, Integer odx_off, Integer oincx) {
			int n = on.intValue();
			int incx = oincx.intValue();
			int dx_off = odx_off.intValue();
			double dmax, dtemp;
			int i, ix, itemp = 0;
			if (n<1) {
				itemp = -1;
			}
			else if (n==1) {
				itemp = 0;
			}
			else if (incx!=1) {
				dmax = this.abs(dx[0+dx_off]);
				ix = 1+incx;
				for (i = 1; i<n; i++){
					dtemp = this.abs(dx[ix+dx_off]);
					if (dtemp>dmax) {
						itemp = i;
						dmax = dtemp;
					}
					ix += incx;
				}
			}
			else {
				itemp = 0;
				dmax = this.abs(dx[0+dx_off]);
				for (i = 1; i<n; i++){
					dtemp = this.abs(dx[i+dx_off]);
					if (dtemp>dmax) {
						itemp = i;
						dmax = dtemp;
					}
				}
			}
			return (itemp);
		}
		final double epslon(double x) {
			double a, b, c, eps;
			a = 4.0e0/3.0e0;
			eps = 0;
			while (eps==0) {
				b = a-1.0;
				c = b+b+b;
				eps = this.abs(c-1.0);
			}
			return (eps*this.abs(x));
		}
		final void dmxpy(Integer on1, double[] y, Integer on2, Integer oldm, double[] x, double[][] m) {
			int j, i;
			int n1 = on1.intValue();
			int n2 = on2.intValue();
			int ldm = oldm.intValue();
			for (j = 0; j<n2; j++){
				for (i = 0; i<n1; i++){
					y[i] += x[j]*m[j][i];
				}
			}
		}
		final void matgen(double[][] a, Integer olda, Integer on, double[] b) {
			int init, i, j;
			int lda = olda.intValue();
			int n = on.intValue();
			init = 1325;
			norma = 0.0;
			for (i = 0; i<n; i++){
				for (j = 0; j<n; j++){
					init = 3125*init%65536;
					a[j][i] = (init-32768.0)/16384.0;
					norma = (a[j][i]>norma)?a[j][i]:norma;
				}
			}
			for (i = 0; i<n; i++){
				b[i] = 0.0;
			}
			for (j = 0; j<n; j++){
				for (i = 0; i<n; i++){
					b[i] += a[j][i];
				}
			}
		}
	}
}