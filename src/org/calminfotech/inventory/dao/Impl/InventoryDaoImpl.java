package org.calminfotech.inventory.dao.Impl;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.daoInterface.InventoryDao;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.UnitItem;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItem WHERE id = ?")
				.setParameter(0, globalItemId).list();

		if (!list.isEmpty()) {
			return (List<GlobalUnitofMeasure>) list.get(0);
		}
		return null;
	}

	@Override
	public UnitItem getUnitOfMeasureToDetails(int globalItemId,
			int currentUnitOfMeasureFrom) {

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM UnitItem WHERE itemId = ? and  unitId=?");
		query.setParameter(0, globalItemId);
		query.setParameter(1, currentUnitOfMeasureFrom);

		List list = query.list();

		if (!list.isEmpty())
			return (UnitItem) list.get(0);

		return null;
	}

	@Override
	public int getGlobalItemCurrentBalance(int globalItemId)
			throws InvalidOpeningStockBalanceException {
		// TODO Auto-generated method stub
		List<Object[]> list = this.sessionFactory.getCurrentSession()
				.createQuery("select quantity FROM GlobalItem WHERE id = ?")
				.setParameter(0, globalItemId).list();

		if (list != null && !list.isEmpty()) {
			try {
				return Integer.parseInt(String.valueOf(list.get(0)));
			} catch (NumberFormatException e) {
			}
		}
		throw new InvalidOpeningStockBalanceException(
				"Invalid stock opening balance for this item");
	}

	@Override
	public void updateGlobalItemCurrentBalance(int currentBalance,
			int globalItemId) {
		// TODO Auto-generated method stub

		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"update  GlobalItem " + "set quantity = ? " + " where id = ?");
		query.setParameter(0, currentBalance);
		query.setParameter(1, globalItemId);
		query.executeUpdate();

	}

	@Override
	public UnitItem getUnitOfMeasureFromDetails(int globalItemId,
			int unitOfMeasure) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM UnitItem WHERE unitId = ? and  contdMeasurement = ?");
		query.setParameter(0, globalItemId);
		query.setParameter(1, unitOfMeasure);

		List list = query.list();

		if (!list.isEmpty())
			return (UnitItem) list.get(0);

		return null;
	}

	@Override
	public int getGlobalItemUnitOfMeasureContainedUnit(int globalItemId,
			int unitOfMeasure) {

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				"select contdValue From UnitItem "
						+ "where unitId = ? and unitId=?");

		qry.setParameter(0, globalItemId);
		qry.setParameter(1, unitOfMeasure);

		List list = qry.list();
		if (list != null && !list.isEmpty() && list.get(0) instanceof Integer) {
			return (Integer) list.get(0);
		}
		return 0;
	}

	@Override
	public void updatePointGlobalItemCurrentBalance(
			PointStockCurrentBalance pointStockCurrentBalance) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				pointStockCurrentBalance);

	}

	@Override
	public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(
			int globalItem, int pointId)
			throws InvalidOpeningStockBalanceException {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?");
		query.setParameter(0, globalItem);
		query.setParameter(1, pointId);
		List list = query.list();
		if (!list.isEmpty())
			return (PointStockCurrentBalance) list.get(0);

		throw new InvalidOpeningStockBalanceException(
				"Invalid stock opening balance for this item");

	}

	@Override
	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int currentPointId) {
		List<PointStockCurrentBalance> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PointStockCurrentBalance where point_id = ? order by visitWorkflowPoint.name")
				.setParameter(0, currentPointId).list();
		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId) throws InvalidOpeningStockBalanceException {

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select currentBalance FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?");

		query.setParameter(0, globalItem);
		query.setParameter(1, currentPointId);

		List<Object[]> list = query.list();

		if (list != null && !list.isEmpty()) {
			try {
				return Integer.parseInt(String.valueOf(list.get(0)));
			} catch (NumberFormatException e) {
			}
		}
		throw new InvalidOpeningStockBalanceException(
				"Invalid stock opening balance for this item");

	}
}
