package ch.fhnw.edu.rental.gui;

import java.util.List;

import ch.fhnw.edu.rental.data.DataSet;
import ch.fhnw.edu.rental.model.IUser;
import ch.fhnw.edu.rental.model.User;

/**
 * Manages CRUD operations concerning User objects for the GUI.
 */
public class UserMgmt {
    /**
     * none.
     */
    private int userListId = 0;

    /**
     * none.
     */
    private boolean editMode = false;

    /**
     * none.
     */
    private List<IUser> userList;

    /**
     * The dataset to the database.
     */
    private DataSet dataset;

    /**
     * 
     * @param userList
     *          list if users
     * @param movieList
     *          list of movies
     * @param dataset
     *          reference to database
     */
    UserMgmt(List<IUser> userList, DataSet dataset) {
      this.userList = userList;
      this.dataset = dataset;
    }
    
    /**
     * retrieves the user at given position in the list.
     * @param pos position in the list
     * @return the user
     */
    IUser getUserAt(int pos) {
        return userList.get(pos);
    }
    
    /**
     * sets mode of GUI. 
     * Either editMode == true then the list is edited or
     *        editMode == false then a new entry was created.
     * @param mode edit or not
     */
    void setEditMode(boolean mode) {
        editMode = mode;
    }
    
    /**
     * @return whether the GUI is in edit mode or not
     */
    boolean isInEditMode() {
        return editMode;
    }
    
    /**
     * Removes user from list.
     * @param pos at which position to remove
     * @return true if user has been removed, false otherwise.
     */
    boolean removeUserAt(int pos) {
        try {
        	userList.get(pos);
        } catch (Exception e) {
        	return false;
        }
        return null != userList.remove(pos);
    }

    /**
     * saves changes to a user.
     * @param name of user
     * @param firstname of user
     * @param pos position in list if any
     * @return whether user has been saved or not
     */
    boolean saveUser(String name, String firstname, int pos) {
        boolean userSaved = !name.isEmpty();
        User currUser = new User(name, firstname);
        if (editMode) {
          currUser.setId(userList.get(pos).getId());
          userList.set(pos, currUser);

          // also update rentals
          dataset.updateRentalsWithUser(currUser);

          editMode = false;
        } else {
          currUser.setId(userListId + 1);
          userList.add(currUser);
          userListId++;
        }
        return userSaved && dataset.saveOrUpdateUser(currUser);
    }
    
    Object[][] getUserListAsObject() {
        return dataset.getUserListAsObject();
    }
    
}
