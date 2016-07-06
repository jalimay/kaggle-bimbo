package com.secoo.bigdata.kaggle.bimbo.domain;

public class TrainData implements Comparable<TrainData> {
	private long Semana;
	private long Agencia_ID;
	private long Canal_ID;
	private long Ruta_SAK;
	private long Cliente_ID;
	private long Producto_ID;
	private int Venta_uni_hoy;
	private double Venta_hoy;
	private int Dev_uni_proxima;
	private double Dev_proxima;
	private int Demanda_uni_equil;

	public static TrainData make(String line) {
		String[] cols = line.split(",");
		TrainData t = new TrainData();
		int i = 0;
		t.Semana = Long.valueOf(cols[i++]);
		t.Agencia_ID = Long.valueOf(cols[i++]);
		t.Canal_ID = Long.valueOf(cols[i++]);
		t.Ruta_SAK = Long.valueOf(cols[i++]);
		t.Cliente_ID = Long.valueOf(cols[i++]);
		t.Producto_ID = Long.valueOf(cols[i++]);
		t.Venta_uni_hoy = Integer.valueOf(cols[i++]);
		t.Venta_hoy = Double.valueOf(cols[i++]);
		t.Dev_uni_proxima = Integer.valueOf(cols[i++]);
		t.Dev_proxima = Double.valueOf(cols[i++]);
		t.Demanda_uni_equil = Integer.valueOf(cols[i++]);
		return t;
	}

	public long getSemana() {
		return Semana;
	}

	public void setSemana(long semana) {
		Semana = semana;
	}

	public long getAgencia_ID() {
		return Agencia_ID;
	}

	public void setAgencia_ID(long agencia_ID) {
		Agencia_ID = agencia_ID;
	}

	public long getCanal_ID() {
		return Canal_ID;
	}

	public void setCanal_ID(long canal_ID) {
		Canal_ID = canal_ID;
	}

	public long getRuta_SAK() {
		return Ruta_SAK;
	}

	public void setRuta_SAK(long ruta_SAK) {
		Ruta_SAK = ruta_SAK;
	}

	public long getCliente_ID() {
		return Cliente_ID;
	}

	public void setCliente_ID(long cliente_ID) {
		Cliente_ID = cliente_ID;
	}

	public long getProducto_ID() {
		return Producto_ID;
	}

	public void setProducto_ID(long producto_ID) {
		Producto_ID = producto_ID;
	}

	public int getVenta_uni_hoy() {
		return Venta_uni_hoy;
	}

	public void setVenta_uni_hoy(int venta_uni_hoy) {
		Venta_uni_hoy = venta_uni_hoy;
	}

	public double getVenta_hoy() {
		return Venta_hoy;
	}

	public void setVenta_hoy(double venta_hoy) {
		Venta_hoy = venta_hoy;
	}

	public int getDev_uni_proxima() {
		return Dev_uni_proxima;
	}

	public void setDev_uni_proxima(int dev_uni_proxima) {
		Dev_uni_proxima = dev_uni_proxima;
	}

	public double getDev_proxima() {
		return Dev_proxima;
	}

	public void setDev_proxima(double dev_proxima) {
		Dev_proxima = dev_proxima;
	}

	public int getDemanda_uni_equil() {
		return Demanda_uni_equil;
	}

	public void setDemanda_uni_equil(int demanda_uni_equil) {
		Demanda_uni_equil = demanda_uni_equil;
	}

	@Override
	public int compareTo(TrainData o) {
		return Long.compare(this.Semana, o.getSemana());
	}

}
