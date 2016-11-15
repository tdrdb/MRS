package ch.fhnw.edu.rental.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.edu.rental.data.DataSet;
import ch.fhnw.edu.rental.model.IUser;
import ch.fhnw.edu.rental.model.User;

public class UserMgmtTest {
	private UserMgmt um;
	private DataSet dataSet;
	private List<IUser> userList;
	private Object[][] userObjectList;

    private Object[][] getUserListAsObject() {
        int listSize = userList != null ? userList.size() : 0;
        Object[][] userArray = new Object[listSize][3];

        if (userList != null) {
            int i = 0;
            for (IUser u : userList) {
                userArray[i][0] = u.getId();
                userArray[i][1] = u.getName();
                userArray[i][2] = u.getFirstName();
                i++;
            }
        }
        return userArray;
    }

	@Before
	public void setup() throws Exception {
		userList = new LinkedList<IUser>();
		userList.add(new User("Muster", "Hans"));
		userList.add(new User("Beispiel", "Pia"));
		userList.add(new User("Sixpack", "Jonny"));
		userObjectList = getUserListAsObject();
		dataSet = mock(DataSet.class);
		when(dataSet.getUserListAsObject()).thenReturn(userObjectList);
		when(dataSet.saveOrUpdateUser(any())).thenReturn(true);
		um = new UserMgmt(userList, dataSet);
	}
	
	@Test
	public void testUserMgmt() {
		assertEquals(3, um.getUserListAsObject().length);
	}

	@Test
	public void testGetUserAt() {
		assertEquals("Beispiel", um.getUserAt(1).getName());
	}

	@Test
	public void testEditMode() {
		um.setEditMode(true);
		assertTrue(um.isInEditMode());
		um.setEditMode(false);
		assertFalse(um.isInEditMode());
	}

	@Test
	public void testRemoveUserAt() {
		assertTrue(um.removeUserAt(1));
		assertTrue(um.removeUserAt(1));
		assertTrue(um.removeUserAt(0));
	}

	@Test
	public void testEditSaveUser() {
		um.setEditMode(true);
		assertTrue(um.saveUser("Duck", "Daisy", 1));
		assertFalse(um.isInEditMode());
	}

	@Test
	public void testNewSaveUser() {
		um.setEditMode(false);
		assertTrue(um.saveUser("Duck", "Donald", -1));
		assertFalse(um.isInEditMode());
	}

	@Test
	public void testGetUserListAsObject() {
		Object[][] o = um.getUserListAsObject();
		assertEquals("Sixpack", o[2][1]);
		assertEquals(3, o.length);
	}

}
