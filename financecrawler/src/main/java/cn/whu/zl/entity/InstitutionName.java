package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="institutionname")
public class InstitutionName implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public InstitutionName(){
		super();
	}
	
	public InstitutionName(String institutionID,String institutionName){
		super();
		this.institutionName=institutionName;
		this.institutionID=institutionID;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((institutionID == null) ? 0 : institutionID.hashCode());
		result = prime * result
				+ ((institutionName == null) ? 0 : institutionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstitutionName other = (InstitutionName) obj;
		if (institutionID == null) {
			if (other.institutionID != null)
				return false;
		} else if (!institutionID.equals(other.institutionID))
			return false;
		if (institutionName == null) {
			if (other.institutionName != null)
				return false;
		} else if (!institutionName.equals(other.institutionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InstitutionName [institutionName=" + institutionName
				+ ", institutionID=" + institutionID + "]";
	}

	public String getInstitutionName(){
		return institutionName;
	}
	
	public void setInstitutionName(String in){
		this.institutionName=in;
	}
	
	public String getInstitutionID(){
		return institutionID;
	}
	
	public void setgetInstitutionID(String iID){
		this.institutionID=iID;
	}
	
	
	@Column(name="institutionName")
	private String institutionName;
	
	@Id
	@Column(name="institutionID")
	private String institutionID;

}
