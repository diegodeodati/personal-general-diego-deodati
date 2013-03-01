package it.betplus.wri.managedbeans;

import it.betplus.web.framework.managedbeans.GeneralBean;
import it.betplus.wri.db.dto.SummaryCasinoV3;
import it.betplus.wri.db.procedure.SPSummaryCasinoV3;
import it.betplus.wri.frontend.beans.UserBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "summaryManagedBean")
@SessionScoped
public class SummaryManagedBean extends GeneralBean {

	private int footerNumCity;
	private double footerNetWL;
	private double footerRtp;
	private double footerPreu;
	private double footer05Aams;
	private double footer03Aams;
	private double footerPercTerziIncaricati;
	private double footerPerSupplier;
	private double footerPreuAndAamsFee;
	private double footerTotalHouseWin;
	private double footerQuotaTerziIncaricati;
	private double footerQuotaHwSupplier;
	private double footerQuotaHwBplus;
	private double footerAvgNetWLforVltforDay;
	private double footerBet;
	private int footerCurrentVltNovomatic;
	private int footerCurrentVltInspired;

	private List<SummaryCasinoV3> summaryCasinoV3List;

	public SummaryManagedBean() {
		summaryCasinoV3List = new ArrayList<SummaryCasinoV3>();
		populateSummaryCasinoV3();
	}

	private void populateSummaryCasinoV3() {

		// dati da DB
		SPSummaryCasinoV3 spSummary = new SPSummaryCasinoV3();

		try {

			// today date
			Calendar cal = Calendar.getInstance();
			java.sql.Date dateToday = new java.sql.Date(cal.getTime().getTime());

			// get current user employee
			LoginWri loginBean = findBean("loginBean");
			UserBean user = loginBean.getLoggedUser();

			summaryCasinoV3List = spSummary.getSPSummaryCasinoV3(1, 1,
					dateToday.getTime(), dateToday.getTime(),
					user.getEmployeeID(), 1000);

			for (SummaryCasinoV3 s : summaryCasinoV3List) {
				footerNumCity++;
				footerNetWL += s.getNetWinLose();
				footerPercTerziIncaricati += s.getPercentGestore();
				footerPerSupplier += s.getPercentSupplier();
				footerRtp += s.getRTP(); // non è la somma trovare formula giusta
				footerPreu += s.getPREU();
				footerBet += s.getBet(); // non è la somma trovare formula giusta
				footer05Aams += s.getAMMS05p();
				footer03Aams += s.getAMMS03p();
				footerPreuAndAamsFee += s.getPREUAMMSfee(); // non è la somma trovare formula giusta
				footerTotalHouseWin += s.getTotalHouseWin();
				footerQuotaTerziIncaricati += s.getRetQuotaGestore();
				footerQuotaHwSupplier += s.getRetQuotaSupplier();
				footerQuotaHwBplus += s.getQuotaHWBPlus();
				footerAvgNetWLforVltforDay += s.getAvgNWPerMachine(); // non è la somma trovare formula giusta
				footerCurrentVltNovomatic += s.getM1();
				footerCurrentVltInspired += s.getM2();

			}

			System.out.println("Executed query results n. " + summaryCasinoV3List.size());

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<SummaryCasinoV3> getSummaryCasinoV3List() {
		return summaryCasinoV3List;
	}

	public void setSummaryCasinoV3List(List<SummaryCasinoV3> summaryCasinoV3List) {
		this.summaryCasinoV3List = summaryCasinoV3List;
	}

	public int getFooterNumCity() {
		return footerNumCity;
	}

	public void setFooterNumCity(int footerNumCity) {
		this.footerNumCity = footerNumCity;
	}

	public double getFooterNetWL() {
		return footerNetWL;
	}

	public void setFooterNetWL(double footerNetWL) {
		this.footerNetWL = footerNetWL;
	}

	public double getFooterRtp() {
		return footerRtp;
	}

	public void setFooterRtp(double footerRtp) {
		this.footerRtp = footerRtp;
	}

	public double getFooterPreu() {
		return footerPreu;
	}

	public void setFooterPreu(double footerPreu) {
		this.footerPreu = footerPreu;
	}

	public double getFooter05Aams() {
		return footer05Aams;
	}

	public void setFooter05Aams(double footer05Aams) {
		this.footer05Aams = footer05Aams;
	}

	public double getFooter03Aams() {
		return footer03Aams;
	}

	public void setFooter03Aams(double footer03Aams) {
		this.footer03Aams = footer03Aams;
	}

	public double getFooterPercTerziIncaricati() {
		return footerPercTerziIncaricati;
	}

	public void setFooterPercTerziIncaricati(double footerPercTerziIncaricati) {
		this.footerPercTerziIncaricati = footerPercTerziIncaricati;
	}

	public double getFooterPerSupplier() {
		return footerPerSupplier;
	}

	public void setFooterPerSupplier(double footerPerSupplier) {
		this.footerPerSupplier = footerPerSupplier;
	}

	public double getFooterPreuAndAamsFee() {
		return footerPreuAndAamsFee;
	}

	public void setFooterPreuAndAamsFee(double footerPreuAndAamsFee) {
		this.footerPreuAndAamsFee = footerPreuAndAamsFee;
	}

	public double getFooterTotalHouseWin() {
		return footerTotalHouseWin;
	}

	public void setFooterTotalHouseWin(double footerTotalHouseWin) {
		this.footerTotalHouseWin = footerTotalHouseWin;
	}

	public double getFooterQuotaTerziIncaricati() {
		return footerQuotaTerziIncaricati;
	}

	public void setFooterQuotaTerziIncaricati(double footerQuotaTerziIncaricati) {
		this.footerQuotaTerziIncaricati = footerQuotaTerziIncaricati;
	}

	public double getFooterQuotaHwSupplier() {
		return footerQuotaHwSupplier;
	}

	public void setFooterQuotaHwSupplier(double footerQuotaHwSupplier) {
		this.footerQuotaHwSupplier = footerQuotaHwSupplier;
	}

	public double getFooterQuotaHwBplus() {
		return footerQuotaHwBplus;
	}

	public void setFooterQuotaHwBplus(double footerQuotaHwBplus) {
		this.footerQuotaHwBplus = footerQuotaHwBplus;
	}

	public double getFooterAvgNetWLforVltforDay() {
		return footerAvgNetWLforVltforDay;
	}

	public void setFooterAvgNetWLforVltforDay(double footerAvgNetWLforVltforDay) {
		this.footerAvgNetWLforVltforDay = footerAvgNetWLforVltforDay;
	}

	public double getFooterBet() {
		return footerBet;
	}

	public void setFooterBet(double footerBet) {
		this.footerBet = footerBet;
	}

	public int getFooterCurrentVltNovomatic() {
		return footerCurrentVltNovomatic;
	}

	public void setFooterCurrentVltNovomatic(int footerCurrentVltNovomatic) {
		this.footerCurrentVltNovomatic = footerCurrentVltNovomatic;
	}

	public int getFooterCurrentVltInspired() {
		return footerCurrentVltInspired;
	}

	public void setFooterCurrentVltInspired(int footerCurrentVltInspired) {
		this.footerCurrentVltInspired = footerCurrentVltInspired;
	}

}
