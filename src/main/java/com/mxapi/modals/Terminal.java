package com.mxapi.modals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Terminal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty
	private long logic;
	@NotEmpty
	private String serial;
	@NotEmpty
	private String model;
	private Integer sam;
	private String ptid;
	private Integer plat;
	@NotEmpty
	private String version;
	private Integer mxr;
	private Integer mxf;
	private String verfm;
	
	public Terminal() {
	}
	
	public Terminal(long id, @NotEmpty long logic, @NotEmpty String serial, @NotEmpty String model, Integer sam,
			String ptid, Integer plat, @NotEmpty String version, Integer mxr, Integer mxf, String verfm) {
		super();
		this.id = id;
		this.logic = logic;
		this.serial = serial;
		this.model = model;
		this.sam = sam;
		this.ptid = ptid;
		this.plat = plat;
		this.version = version;
		this.mxr = mxr;
		this.mxf = mxf;
		this.verfm = verfm;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLogic() {
		return logic;
	}
	public void setLogic(long logic) {
		this.logic = logic;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getSam() {
		return sam;
	}
	public void setSam(Integer sam) {
		this.sam = sam;
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public Integer getPlat() {
		return plat;
	}
	public void setPlat(Integer plat) {
		this.plat = plat;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getMxr() {
		return mxr;
	}
	public void setMxr(Integer mxr) {
		this.mxr = mxr;
	}
	public Integer getMxf() {
		return mxf;
	}
	public void setMxf(Integer mxf) {
		this.mxf = mxf;
	}
	public String getVerfm() {
		return verfm;
	}
	public void setVerfm(String verfm) {
		this.verfm = verfm;
	}
	

}
