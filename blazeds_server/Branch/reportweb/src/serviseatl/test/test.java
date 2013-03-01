package serviseatl.test;
import serviseatl.resultmeter.SPResultMeter;
import serviseatl.resultmeter.SPResultMeterSummary;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;
import flex.samples.product.testfecha;
import serviseatl.casinoweb.SPCasinoWeb;
import serviseatl.billestimation.SPBillEstimation;
import serviseatl.summarycasinov2.SPSummaryCasinoV2;
import serviseatl.summarycasinodate.SPSummaryCasinoDate;
import serviseatl.summarycasinomonth.SPSummaryCasinoMonth;
import serviseatl.jackpotshopperfills.SPJackpotsHopperFills;
import serviseatl.reportmeter.SPReportMeter;
import serviseatl.session.SPSession;
import serviseatl.project.SPProject;
import serviseatl.projection.SPProjection;
import serviseatl.test.SPTemporal;
import serviseatl_gis.iniciarsesion.SPIniciarSesion;
import serviseatl_gis.estructura.SPEstructura;
import serviseatl_gis.slotgis.SPSlotGis;
import serviseatl_gis.betmaquina.SPBetMaquina;
import serviseatl_gis.jackpot.SPJackpot;
import serviseatl_gis.traffic.SPTraffic;
import serviseatl.tables.SPGrupoTabla;
import serviseatl.tables.SPTables1;
import serviseatl.w_raffle.SPW_Raffle;
import serviseatl.yearlygraphical.SPYearlyGraphical;
import serviseatl.connection.DAOException;
import serviseatl.customers.SPCustomers;
import serviseatl.customers.RSCustomers;
import serviseatl.mystery.SPGroupMysterys;
import serviseatl.mystery.SPLastPaids;
import serviseatl.mystery.RSLastPaids;
import serviseatl.mystery.RSDetailMonth;
import serviseatl.mystery.SPDetailMonth;
import serviseatl_gis.rating.SPRating;
import serviseatl.permisosFlex.SPPermisosFlex;
import serviseatl.cabinets.SPCabinets;
import serviseatl.customers.SPMaxDailyCustomer;
import serviseatl.percentages.SPPercentages;
import serviseatl.compiler.Compiler;
import serviseatl.jackpotlist.SPJackpotList;
import serviseatl.eventos.SPEvento;
import serviseatl.eventos.SPEventoCritico;
import serviseatl.eventos.SPDatosMaquina;
import serviseatl.eventos.SPSASExceptions;
public class test {
	public static void main(String[] args) throws DAOException, ServerStatusException {
//		SPResultMeter xx = new SPResultMeter();
//		xx.getSPResultMeter(1,(byte)0,3,1233615676281L,1233615676281L);

		SPResultMeterSummary xx = new SPResultMeterSummary();
		xx.getSPResultMeterSummary(1,(byte)0,6000033, 1291350600000L, 1291437000000L,2);

//		testfecha yy = new testfecha();
//		yy.getTestFecha();

//		SPCasinoWeb zz = new SPCasinoWeb();
//		zz.getSPCasinoWeb(1,2);

//		SPBillEstimation qq = new SPBillEstimation();
//		qq.getSPBillEstimation(1, (byte)0, 3, 1204257600000L, 1204257600000L, 1204257600000L, -1);

		SPSummaryCasinoV2 aa = new SPSummaryCasinoV2();
		aa.getSPSummaryCasinoV2(1, 1, 1288845000000L, 1288845000000L, 2);

//		SPTemporal tt = new SPTemporal();
//		tt.getSPTemporal();
		
//		SPSummaryCasinoDate dd = new SPSummaryCasinoDate();
//		dd.getSPSummaryCasinoDate(1, 1, 1236484800000L, 2);

//		SPSummaryCasinoMonth mm = new SPSummaryCasinoMonth();
//		mm.getSPSummaryCasinoMonth(1, 1, 1240200000000L, 2);

//		SPReportMeter rr = new SPReportMeter();
//		rr.getSPReportMeter((byte)0, 3, 1237521600000L);

//		SPJackpotsHopperFills jj = new SPJackpotsHopperFills();
//		jj.getSPJackpotsHopperFills(1,(byte)0, 3, 1, 1236484800000L, 1236484800000L);

//		SPSession ss = new SPSession();
//		ss.getSPSession("admin","svratl2004");

//		SPProject dd = new SPProject();
//		dd.getSPProject((byte)0, 3, 2, 2);
	
//		SPMaxDailyCustomer jj = new SPMaxDailyCustomer();
//		jj.getSPMaxDailyCustomer((byte)0, 3, 568008000000L, 1247198400000L);
		
//		SPProjection jj = new SPProjection();
//		jj.getSPProjection(1,(byte)0, 2, 568008000000L, 1247198400000L,3, 2);
		
/*		
		String xx = "";
		SPIniciarSesion ss = new SPIniciarSesion();
		xx = ss.getSPIniciarSesion((byte)0, 3, 2);
		System.out.println(xx);		
		System.out.println("** fin **");
*/		
		
		/*SPEstructura ee = new SPEstructura();
		ee.getSPEstructura((byte)0, 3);
		*/
		
		//SPSlotGis ll = new SPSlotGis();
		//ll.getSPSlotGis((byte)0, 3, "");
//		SPBetMaquina bb = new SPBetMaquina();
//		bb.getSPBetMaquina((byte)0, 3, "W2042349");
		
//		SPJackpot bb = new SPJackpot();
//		bb.getSPJackpot((byte)0, 3);
		
//		SPTraffic tt = new SPTraffic();
//		tt.getSPTraffic((byte)1,(byte)0, 3);
		
//		SPGrupoTabla gg = new SPGrupoTabla();
//		gg.getSPGrupoTabla();
		
//		SPTables1 gg = new SPTables1();
//		gg.getSPTables1((byte)0,(byte)0,3,1248753600000L);
		
		
//		SPW_Raffle gg = new SPW_Raffle();
//		gg.getSPW_Raffle((byte)0,3);

//		SPYearlyGraphical gg = new SPYearlyGraphical();
//		gg.getSPYearlyGraphical(0,3,568008000000L,568008000000L,2);

//		SPCustomers gg = new SPCustomers();
//		gg.getSPCustomers((byte)0,3,1248753600000L,1248753600000L);

//		RSCustomers ff = new RSCustomers();
//		ff.getRSCustomers((byte)0,3,1248753600000L,1248753600000L);
		
//		SPGroupMysterys ff = new SPGroupMysterys();
//		ff.getSPGroupMysterys((byte)0,3,(byte)1,"abc");
		
//		SPLastPaids ff = new SPLastPaids();
		//ff.getSPLastPaids((byte)0,3,(byte)1);

//		RSLastPaids vv = new RSLastPaids();
//		vv.getRSLastPaids((byte)0,3,(byte)1);

//		RSDetailMonth vv = new RSDetailMonth();
//		vv.getRSDetailMonth((byte)0,3,(byte)1, 1248753600000L);
		
//		SPDetailMonth qq = new SPDetailMonth();
//		qq.getSPDetailMonth((byte)0,3,(byte)1, 1248753600000L);
		
//		SPRating qq = new SPRating();
//		qq.getSPRating(4, (byte)0, 50315);

//		SPPercentages pe = new SPPercentages();
//		pe.getSPPercentages();

//		SPPermisosFlex qq = new SPPermisosFlex();
//		qq.getSPPermisosFlex((byte)0, 2);

//		System.out.println("gfhfgh");
		//SPCabinets ca = new SPCabinets();
//		ca.getSPCabinets();
//		System.out.println("'/gfhfgh");		

//		SPJackpotList jj = new SPJackpotList();
//		jj.getSPJackpotList((byte)0, 3,  1288758600000L);
		
//		SPDatosMaquina jj = new SPDatosMaquina();
//		jj.getSPDatosMaquina((byte)0, 0, 0,"W2079055");
		
//		SPEvento jj = new SPEvento();
//		jj.getSPEvento((byte)0,3, "W2079055",0,1288758600000L, 1288758600000L);

//		SPSASExceptions jj = new SPSASExceptions();
//		jj.getSPSASExceptions();
		
//		SPEventoCritico jj = new SPEventoCritico();
//		jj.getSPEventoCritico((byte)0,3, 1288758600000L, -1,0,false);
		
		Compiler qq = Compiler.getCompiler();
		System.out.print(qq.getLocationID_Compiler());
		System.out.print(qq.getCodCasino_Compiler());		
		
	}
}
