package cn.whu.zl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "stockindex")
public class StockIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	public StockIndex() {
		super();
	}
	
	public StockIndex(String id) {
		super();
		this.id = id;
	}

	public StockIndex(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "id",length=6)
	private String id;

	@Column(name = "name")
	private String name;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
