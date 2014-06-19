/**
 * 
 */
package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author B506-13-1
 * 
 */
@Entity
@Table(name="industryreport")
public class IndustryReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "IndustryReport [idtPK=" + idtPK + ", grade=" + grade
				+ ", gradeChange=" + gradeChange + ", orgName=" + orgName
				+ ", orgWeight=" + orgWeight + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idtPK == null) ? 0 : idtPK.hashCode());
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
		IndustryReport other = (IndustryReport) obj;
		if (idtPK == null) {
			if (other.idtPK != null)
				return false;
		} else if (!idtPK.equals(other.idtPK))
			return false;
		return true;
	}

	@EmbeddedId
	private IndustryNameDataTitlePK idtPK;

	@Column(name = "grade")
	private byte grade;

	@Column(name = "gradeChange")
	private byte gradeChange;

	@Column(name = "orgName")
	private String orgName;
	
	@Column(name = "orgWeight")
	private byte orgWeight;
	
	@Column(name = "title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public IndustryNameDataTitlePK getIdtPK() {
		return idtPK;
	}

	public void setIdtPK(IndustryNameDataTitlePK idtPK) {
		this.idtPK = idtPK;
	}

	public byte getGrade() {
		return grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}

	public byte getGradeChange() {
		return gradeChange;
	}

	public void setGradeChange(byte gradeChange) {
		this.gradeChange = gradeChange;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public byte getOrgWeight() {
		return orgWeight;
	}

	public void setOrgWeight(byte orgWeight) {
		this.orgWeight = orgWeight;
	}

}
