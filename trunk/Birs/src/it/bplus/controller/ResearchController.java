package it.bplus.controller;


import it.bplus.bean.MeterBean;
import it.bplus.business.GeographicBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Comuni;
import it.bplus.model.Province;
import it.bplus.model.Regioni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.Highlight;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name="researchController")
@ViewScoped
public class ResearchController extends BasicController implements Serializable {


	private static final long serialVersionUID = 1L;

	private String nome_sala;
	private String code_id;
	private String regione;
	private String provincia;
	private String comune;
	private String gestore;

	private String exporttype;

	Date data1 = new GregorianCalendar().getTime();
	Date data2 = new GregorianCalendar().getTime();
	protected Effect valueChangeEffect;

	private List<MeterBean> ricercaList;
	private MeterBean ivItem;
	private List<Regioni> listRegioni;
	private List<Province> listProvince;
	private List<Comuni> listComuni;
	private List<SelectItem> lista_regioni;
	private List<SelectItem> lista_province;
	private List<SelectItem> lista_comuni;
	private HtmlSelectOneMenu cmbRegioni;
	private HtmlSelectOneMenu cmbProvince;
	private HtmlSelectOneMenu cmbComuni;
	

	private String visibile_province="default";
	private String visibile_comuni="default";


	public ResearchController(){
		super();
		valueChangeEffect = new Highlight("#fda505");
		valueChangeEffect.setFired(true);
	}
	

	
//	public void ricerca() {
//
//		try {
//			ricercaList = MeterBusinessDelegate.getInstance()
//					.trasformaFromMeterfactToMeterBean(
//							MeterBusinessDelegate.getInstance().retrieveMeter(
//									nome_sala, code_id, regione, provincia,
//									comune, gestore, data1, data2));
//
//			ivView = IConstants.LIST_RES;
//			ivItem = null;
//		} catch (BusinessLayerException e) {
//			e.printStackTrace();
//			getLog().error(e.toString());
//			addFacesMessageForUI("Exception detected. Message: " +e.toString());
//		}
//	}


	public List<SelectItem> getLista_regioni() throws BusinessLayerException {
		if (lista_regioni == null) {
			lista_regioni = new ArrayList<SelectItem>();

			listRegioni = this.getListRegioni();

			Iterator<Regioni> i = listRegioni.iterator();
			while (i.hasNext()) {
				Regioni reg = i.next();
				lista_regioni.add(new SelectItem(reg.getIdReg(),reg.getNome()));
			}

		}
		return lista_regioni;
	}

	public List<SelectItem> getLista_province() throws BusinessLayerException {
		if (lista_province == null) {
			lista_province = new ArrayList<SelectItem>();
		}
		else
			lista_province.clear();
		listProvince = this.getListProvince();
		if (listProvince != null) {

			Iterator<Province> i = listProvince.iterator();
			while (i.hasNext()) {
				Province pro = i.next();
				lista_province.add(new SelectItem(pro.getIdProv(),pro.getNome()));
			}
		}

		return lista_province;
	}

	public List<SelectItem> getLista_comuni() throws BusinessLayerException {
		if (lista_comuni == null) {
			lista_comuni = new ArrayList<SelectItem>();
		}
		else
			lista_comuni.clear();		
		listComuni = this.getListComuni();
		if (listComuni != null) {

			Iterator<Comuni> i = listComuni.iterator();
			while (i.hasNext()) {
				Comuni com = i.next();
				lista_comuni.add(new SelectItem(String.valueOf(com.getIdComune()),com.getNome()));
			}
		}

		return lista_comuni;
	}

	public void changeRegione(ValueChangeEvent ev) {
		logger.debug("changeRegione: " +ev.toString());
		if (ev.getNewValue() != null) {
			changeRegione((String) ev.getNewValue());
		}
	}

	/**
	 * carica le provincie della regione e resetta i valori di selezione di
	 * provincia e comune
	 * 
	 * @param value
	 */
	public void changeRegione(String value) {
		if (lista_comuni != null && lista_comuni.size() > 0)
			lista_comuni.clear();
		if (lista_province != null && lista_province.size() > 0)
			lista_province.clear();		

		if (listComuni != null && listComuni.size() > 0)
			listComuni.clear();
		if (listProvince != null && listProvince.size() > 0)
			listProvince.clear();	
		regione = value;
		cmbRegioni.setValue(value);
		cmbProvince.setValue(" - ");
		cmbComuni.setValue(" - ");
		provincia = " - ";
		comune = "-1";
	}
	
	public void changeProvincia(ValueChangeEvent ev) {
		logger.debug("changeProvincia: " +ev.toString());
		if (ev.getNewValue() != null) {
			changeProvincia((String) ev.getNewValue());
		}
	}
	
	
	public void changeProvincia(String value) {
		if (lista_comuni != null && lista_comuni.size() > 0)
			lista_comuni.clear();
		if (listComuni != null && listComuni.size() > 0)
			listComuni.clear();

		provincia = value;	
		comune = "-1";
		cmbProvince.setValue(value);
		cmbComuni.setValue(" - ");		
	}	
	
	
	public Effect getValueChangeEffect() {
		return valueChangeEffect;
	}

	public void setValueChangeEffect(Effect valueChangeEffect) {
		this.valueChangeEffect = valueChangeEffect;
	}

	public HtmlSelectOneMenu getCmbRegioni() {
		return cmbRegioni;
	}

	public void setCmbRegioni(HtmlSelectOneMenu cmbRegioni) {
		this.cmbRegioni = cmbRegioni;
	}

	public HtmlSelectOneMenu getCmbProvince() {
		return cmbProvince;
	}

	public void setCmbProvince(HtmlSelectOneMenu cmbProvince) {
		this.cmbProvince = cmbProvince;
	}

	public HtmlSelectOneMenu getCmbComuni() {
		return cmbComuni;
	}

	public void setCmbComuni(HtmlSelectOneMenu cmbComuni) {
		this.cmbComuni = cmbComuni;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}



	public void effectChangeListener(ValueChangeEvent event){
		valueChangeEffect.setFired(false);
	}

	public String getNome_sala() {
		return nome_sala;
	}

	public void setNome_sala(String nome_sala) {
		this.nome_sala = nome_sala;
	}

	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}


	public String getGestore() {
		return gestore;
	}

	public void setGestore(String gestore) {
		this.gestore = gestore;
	}


	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub

	}

	public void setExporttype(String exporttype) {
		this.exporttype = exporttype;
	}

	public String getExporttype() {
		return exporttype;
	}


	public List<MeterBean> getRicercaList() {
		return ricercaList;
	}

	public void setRicercaList(List<MeterBean> ricercaList) {
		this.ricercaList = ricercaList;
	}

	public MeterBean getItem() {
		return ivItem;
	}

	public void setItem(MeterBean ivItem) {
		this.ivItem = ivItem;
	}

	public Date getData1(){
		return data1;
	}

	public void setData1(Date data1){
		this.data1 = data1;
	}


	public Date getData2(){
		return data2;
	}

	public void setData2(Date data2){
		this.data2 = data2;
	}

	public void setListRegioni(List<Regioni> listRegioni) {
		this.listRegioni = listRegioni;
	}

	public List<Regioni> getListRegioni() throws BusinessLayerException {
		try {
			if (listRegioni == null) {
				listRegioni = GeographicBusinessDelegate.getInstance().getRegioni();
			}
			return listRegioni;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}

	public void setListProvince(List<Province> listProvince) {
		this.listProvince = listProvince;
	}

	public List<Province> getListProvince() throws BusinessLayerException {
		try {
			if (listProvince == null || (listProvince!=null && listProvince.size()==0)) {
				if (regione != null && (regione.length() > 0 && !regione.equals(" - ")))
					listProvince = GeographicBusinessDelegate.getInstance()
					.getProvinceByIdRegione(regione);
				
			}
			return listProvince;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("ListProvince: "+e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}

	public void setListComuni(List<Comuni> listComuni) {
		this.listComuni = listComuni;
	}

	public List<Comuni> getListComuni() throws BusinessLayerException {
		try {
			if (listComuni == null || (listComuni!=null && listComuni.size()==0)) {
				if (provincia != null && (provincia.length() > 0 && !provincia.equals(" - ")))
					listComuni = GeographicBusinessDelegate.getInstance()
					.getComuniByIdProvincia(provincia);

			}
			return listComuni;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			addFacesMessageForUI("getListComuni: "+ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("getListComuni: "+e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}

	public String getVisibile_province() {
		return visibile_province;
	}

	public void setVisibile_province(String visibile_province) {
		this.visibile_province = visibile_province;
	}

	public String getVisibile_comuni() {
		return visibile_comuni;
	}

	public void setVisibile_comuni(String visibile_comuni) {
		this.visibile_comuni = visibile_comuni;
	}

	public void setLista_regioni(List<SelectItem> lista_regioni) {
		this.lista_regioni = lista_regioni;
	}
}
