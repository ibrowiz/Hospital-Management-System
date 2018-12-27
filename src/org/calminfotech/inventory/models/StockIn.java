package org.calminfotech.inventory.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.utils.CommonVariables;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_stock_in")
@SQLDelete(sql = "UPDATE inventory_stock_in SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class StockIn extends CommonVariables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "stock_in_batch_id",unique = true, updatable=false)
	private String batchId;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendor vendor;
	
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "stockInBatch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause="is_deleted <> '1'")
    private Set<StockInLine> stockInLines;
	
	
	@Column(name = "date_stock_added")
	private String supplyDate;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Vendor getVendor() {
		return vendor;
	}
	

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Set<StockInLine> getStockInLines() {
		return stockInLines;
	}

	public void setStockInLines(Set<StockInLine> supplyLines) {
		this.stockInLines = supplyLines;
	}

	
	public String getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}

}
