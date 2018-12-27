package org.calminfotech.inventory.utils;

import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.daoInterface.InventoryDao;
import org.calminfotech.system.models.UnitItem;
import org.calminfotech.utils.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureConverter {

	@Autowired
	private InventoryDao inventoryDao;

	public int convertUnitOfMeasureToUnitOptionA(int globalItemId,
			int unitOfMeasure, int qty)
			throws InvalidUnitOfMeasureConfiguration {
		// if unit of measure to convert to/selected by user is unit, then we
		// simply return the qty

		int unit = AppConfig.UNIT_OF_MEASURE_UNIT;
		if (unitOfMeasure == unit) {
			return qty;
		}
		int containedUnit = this.inventoryDao
				.getGlobalItemUnitOfMeasureContainedUnit(globalItemId,
						unitOfMeasure);
		if (containedUnit > 0) {
			return containedUnit * qty;
		}
		throw new InvalidUnitOfMeasureConfiguration(
				"invalid unit of measure configuration");
	}

	public int convertUnitOfMeasureToContainedUnitOptionA(int globalItemId,
			int unitOfMeasure) throws InvalidUnitOfMeasureConfiguration {

		int containedUnit = this.inventoryDao
				.getGlobalItemUnitOfMeasureContainedUnit(globalItemId,
						unitOfMeasure);
		if (containedUnit > 0) {
			return containedUnit;

		}
		throw new InvalidUnitOfMeasureConfiguration(
				"invalid unit of measure configuration");

	}

	public int convertUnitOfMeasureToUnitOptionB(int globalItemId,
			int unitOfMeasureToConvertTo, int qty, int currentUnitOfMeasureFrom)
			throws InvalidUnitOfMeasureConfiguration {

		// if unit of measure to convert to/selected by user is unit, then we
		// simply return the qty
		int unit = AppConfig.UNIT_OF_MEASURE_UNIT;
		if (unitOfMeasureToConvertTo == unit) {
			return qty;
		}

		UnitItem globalItemUnitofMeasure = this.inventoryDao
				.getUnitOfMeasureToDetails(globalItemId,
						currentUnitOfMeasureFrom);

		/*
		 * if no record found and current unit of measure is unit, then unit has
		 * not been configured for current item
		 */
		if (globalItemUnitofMeasure == null) {
			if (currentUnitOfMeasureFrom == unit) {
				throw new InvalidUnitOfMeasureConfiguration(
						"invalid unit of measure configuration");
			} else {
				return qty;
			}
		}

		// if there exist record continue
		qty *= globalItemUnitofMeasure.getContdValue();
		currentUnitOfMeasureFrom = globalItemUnitofMeasure.getContdMeasurement();
		/*
		 * if unit of measure selected by user equals next unitof measure
		 * selected from db then we ve gotten our qty
		 */
		if (unitOfMeasureToConvertTo == currentUnitOfMeasureFrom) {
			return qty;
		}
		return this.convertUnitOfMeasureToUnitOptionB(globalItemId,
				unitOfMeasureToConvertTo, qty, currentUnitOfMeasureFrom);

	}

	public int convertUnitOfMeasureToContainedUnitOptionB(
			int totalContainedUnit, int globalItemId, int unitOfMeasureToConvert)
			throws InvalidUnitOfMeasureConfiguration {

		// if unit of measure to convert to/selected by user is unit, then we
		// simply return the qty
		int unit = AppConfig.UNIT_OF_MEASURE_UNIT;
		// if(unitOfMeasureToConvert==unit){
		// return qty;
		// }
		UnitItem globalItemUnitofMeasure = this.inventoryDao
				.getUnitOfMeasureFromDetails(globalItemId,
						unitOfMeasureToConvert);
		/*
		 * if no record found and current unit of measure is unit, then unit has
		 * not been configured for current item
		 */
		if (globalItemUnitofMeasure == null) {
			if (unitOfMeasureToConvert == unit) {
				// return
				// totalContainedUnit*=globalItemUnitofMeasure.getContainedValue();
				return totalContainedUnit;
			} else {
				throw new InvalidUnitOfMeasureConfiguration(
						"invalid unit of measure configuration");
			}
		} else {

			totalContainedUnit *= globalItemUnitofMeasure.getContdValue();
			this.convertUnitOfMeasureToContainedUnitOptionB(totalContainedUnit,
					globalItemId,
					globalItemUnitofMeasure.getUnitId());
		}

		return totalContainedUnit;

	}
}
