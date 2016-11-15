package ch.fhnw.edu.rental.model;

/**
 * Abstract price category class for movie rentals.
 * 
 */
public abstract class PriceCategory {
	/**
	 * none.
	 */
	private static final int ID = 0;

	/**
	 * @return none.
	 */
	public int getId() {
		return PriceCategory.ID;
	}

	/**
	 * @param daysRented
	 *            none.
	 * @return none.
	 */
	public abstract double getCharge(int daysRented);

	/**
	 * @param daysRented
	 *            none.
	 * @return none.
	 */
	public int getFrequentRenterPoints(int daysRented) {
		return daysRented > 0 ? 1 : 0;
	}

	public double calcDiscount(int daysRented) {
		if (getCharge(daysRented) > 5.0) {
			return 0.1;
		} else {
			return 0;
		}
	}

}
