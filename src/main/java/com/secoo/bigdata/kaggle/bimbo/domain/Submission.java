package com.secoo.bigdata.kaggle.bimbo.domain;

public class Submission {
	private long Id;
	private double Demanda_uni_equil;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public double getDemanda_uni_equil() {
		return Demanda_uni_equil;
	}

	public void setDemanda_uni_equil(double demanda_uni_equil) {
		Demanda_uni_equil = demanda_uni_equil;
	}
}
