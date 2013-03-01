package serviseatl_gis.redeem;

import java.io.Serializable;

public class Redeem  implements Serializable {
    static final long serialVersionUID = 104844666002332701L;
    
    private String CCasino;
    private int FYear;
    private String FMonth;
    private String FF;
    private int RedeemPoints;
    private int Balance;
    private String TypeRedeem;
    private double AmountRedeem;
    private double AmountRedeemSus;
    private String SimboloM;
    
    public Redeem() {

    }

    public Redeem(String CCasino,
            int FYear,
            String FMonth,
            String FF,
            int RedeemPoints,
            int Balance,
            String TypeRedeem,
            double AmountRedeem,
            double AmountRedeemSus,
            String SimboloM)    
    {
        this.CCasino = CCasino;
        this.FYear = FYear;
        this.FMonth = FMonth;
        this.FF = FF;
        this.RedeemPoints = RedeemPoints;
        this.Balance = Balance;
        this.TypeRedeem = TypeRedeem;
        this.AmountRedeem = AmountRedeem;
        this.AmountRedeemSus = AmountRedeemSus;
        this.SimboloM = SimboloM;
     }

	public void setCCasino(String cCasino) {
		CCasino = cCasino;
	}

	public String getCCasino() {
		return CCasino;
	}

	public void setFYear(int fYear) {
		FYear = fYear;
	}

	public int getFYear() {
		return FYear;
	}

	public void setFMonth(String fMonth) {
		FMonth = fMonth;
	}

	public String getFMonth() {
		return FMonth;
	}

	public void setFF(String fF) {
		FF = fF;
	}

	public String getFF() {
		return FF;
	}

	public void setRedeemPoints(int redeemPoints) {
		RedeemPoints = redeemPoints;
	}

	public int getRedeemPoints() {
		return RedeemPoints;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}

	public int getBalance() {
		return Balance;
	}

	public void setTypeRedeem(String typeRedeem) {
		TypeRedeem = typeRedeem;
	}

	public String getTypeRedeem() {
		return TypeRedeem;
	}

	public void setAmountRedeem(double amountRedeem) {
		AmountRedeem = amountRedeem;
	}

	public double getAmountRedeem() {
		return AmountRedeem;
	}

	public void setAmountRedeemSus(double amountRedeemSus) {
		AmountRedeemSus = amountRedeemSus;
	}

	public double getAmountRedeemSus() {
		return AmountRedeemSus;
	}

	public void setSimboloM(String simboloM) {
		SimboloM = simboloM;
	}

	public String getSimboloM() {
		return SimboloM;
	}


}
