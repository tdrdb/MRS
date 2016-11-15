package ch.fhnw.edu.rental.gui;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import ch.fhnw.edu.rental.data.DataSet;
import ch.fhnw.edu.rental.model.IUser;
import ch.fhnw.edu.rental.model.User;


public class ITDbToGUITest {
	
	private DataSet mockDataSet = mock(DataSet.class);
	private UserMgmt usrMgmt;
	
	@Before
	public void setup()  {
		
		List<IUser> users = new ArrayList<>();
		users.add(new User("Obrist", "Roger"));
		users.add(new User("LastName", "FirstName"));
		Whitebox.setInternalState(mockDataSet, "userList", users);
		when(mockDataSet.getUserList()).thenReturn(users);
		when(mockDataSet.getUserListAsObject()).thenCallRealMethod();
		
		
		
		usrMgmt = new UserMgmt(users, mockDataSet);
	}
	
	@Test
	public void testDbToGUIIT() {
		UserPanel up = new UserPanel(null, null, mockDataSet);
		up.get
	}
	
}
