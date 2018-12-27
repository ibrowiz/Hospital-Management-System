package org.calminfotech.inventory.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.calminfotech.utils.CommonVariables;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "point_inventory_orders")
@SQLDelete(sql = "UPDATE point_inventory_orders SET is_deleted = 1 WHERE order_id = ?")
@Where(clause = "is_deleted <> 1")
public class Order extends CommonVariables implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "order_id", unique = true, updatable = false)
	private String orderId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id asc")  
	private Set<OrderLine> orderLines;

	@Column(name = "date_of_order")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
		
	@Column(name = "order_by")
	private char orderBy;

	@Transient
	private String totalPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
