package com.secoo.bigdata.kaggle.bimbo.domain;

public class TestData {
	private long Id;
	private long Semana;
	private long Agencia_ID;
	private long Canal_ID;
	private long Ruta_SAK;
	private long Cliente_ID;
	private long Producto_ID;

	public static TestData make(String line) {
		String[] cols = line.split(",");
		TestData t = new TestData();
		int i = 0;
		t.Id = Long.valueOf(cols[i++]);
		t.Semana = Long.valueOf(cols[i++]);
		t.Agencia_ID = Long.valueOf(cols[i++]);
		t.Canal_ID = Long.valueOf(cols[i++]);
		t.Ruta_SAK = Long.valueOf(cols[i++]);
		t.Cliente_ID = Long.valueOf(cols[i++]);
		t.Producto_ID = Long.valueOf(cols[i++]);
		return t;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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
}
