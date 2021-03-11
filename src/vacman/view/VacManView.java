package vacman.view;

/**
 * this class represents the view of the mvc model and contains different views.
 * 
 *
 */
public interface VacManView<VacManModel> {

	/**
	 * the render method updates the view.
	 * 
	 * @param model the model on which the map/view is created.
	 */
	public void render(VacManModel model);

	/**
	 * the createmap method as abstract method which is implemented in different
	 * views differently.
	 * 
	 * @param model the model of the game/map.
	 */
	public void createMap(VacManModel model);

	/**
	 * Getter for the resolution.
	 * 
	 * @return The resolution as an int.
	 */
	public int getRes();
}
