package org.calminfotech.hmo.models;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "ehmocategorylist")

	public class EhmoCategoryList {
		
		@Id
		@GeneratedValue
		@Column(name ="rowid")
		private Integer rowId;
		
		@Column(name ="names")
		private String names;

		public Integer getRowId() {
			return rowId;
		}

		public void setRowId(Integer rowId) {
			this.rowId = rowId;
		}

		public String getNames() {
			return names;
		}

		public void setNames(String names) {
			this.names = names;
		}

		
		
	}

