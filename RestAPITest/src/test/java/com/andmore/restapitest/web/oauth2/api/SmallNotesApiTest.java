package com.andmore.restapitest.web.oauth2.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.andmore.restapitest.service.BudgetService;

 

/**
 * Unit tests for class SmallNotesApiImpl
 * @author Andres
 */
@RunWith(MockitoJUnitRunner.class)
public class SmallNotesApiTest {

	@Mock
	private SecurityContext securityContext;
	
	@Mock
	private OAuth2Authentication principal;
	
	@Mock
	private BudgetService budgetService;
	
	@Mock
	private Authentication authentication;
	
	//@Mock
	//private User loggedUser;
	
	
	@Before
	public void initAuthentication(){
		final String USER_EMAIL_1 = "cgpcosmad@gmail.com";
		
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		Mockito.when(authentication.getPrincipal()).thenReturn(principal);
		
		Mockito.when(principal.getUserAuthentication()).thenReturn(authentication);		
		Mockito.when(authentication.getName()).thenReturn(USER_EMAIL_1);
	//	Mockito.when(smallNotesService.getUserByEmail(USER_EMAIL_1)).thenReturn(loggedUser);
		
		SecurityContextHolder.setContext(securityContext);
	}
	
//	@Test
//	public void shouldGetAllUserNotesWhenNoFilter(){
//		Mockito.when(smallNotesService.getAll(loggedUser, null)).thenReturn(null);
//		
//		SmallNotesAPI smallNotesApi = new SmallNotesAPI(smallNotesService);
//		
//		smallNotesApi.getNotes(null, null);
//		
//		// Verify that getAll method has been called
//		Mockito.verify(smallNotesService).getAll(loggedUser, null);
//	}
	
	
//	@Test
//	public void shouldCreateNote(){
//		Note note = Mockito.mock(Note.class);
//		Mockito.when(note.getId()).thenReturn(null);
//		
//		SmallNotesAPI smallNotesApi = new SmallNotesAPI(smallNotesService);
//		smallNotesApi.createNote(note);
//		
//		// Verify that create method has been called
//		Mockito.verify(smallNotesService).create(note);
//	}
	
	
//	@Test
//	public void shouldDeleteNote(){
//		final int NOTE_ID_TO_BE_DELETED = 1;
//		
//		Note note = Mockito.mock(Note.class);
//		Mockito.when(note.getId()).thenReturn(NOTE_ID_TO_BE_DELETED);
//		Mockito.when(note.getOwner()).thenReturn(this.loggedUser);
//		Mockito.when(smallNotesService.getNote(NOTE_ID_TO_BE_DELETED)).thenReturn(note);
//		
//		SmallNotesAPI smallNotesApi = new SmallNotesAPI(smallNotesService);
//		smallNotesApi.deleteNote(NOTE_ID_TO_BE_DELETED);
//		
//		// Verify that delete method has been called. 
//		Mockito.verify(smallNotesService).delete(note);
//	}
}
