package org.pharmacy.domain;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@MockStaticEntityMethods
public class LetterTest {

    @Test
    public void testMethod() {
        int expectedCount = 13;
        Letter.countLetters();
        org.springframework.mock.staticmock.AnnotationDrivenStaticEntityMockingControl.expectReturn(expectedCount);
        org.springframework.mock.staticmock.AnnotationDrivenStaticEntityMockingControl.playback();
        org.junit.Assert.assertEquals(expectedCount, Letter.countLetters());
    }
    
    @SuppressWarnings("deprecation")
	@Test
    @Transactional
    public void testViolations() {
    	Letter letter = new Letter();
    	
    	//required fields
    	letter.setNumber("0123456789");
    	letter.setDate(new Date());
    	letter.setSubject("e.g.subject");
    	letter.setFileName("random.jpg");

    	letter.persist();
    	letter.flush();
    	letter.clear();
    	
    	Assert.assertNotNull(letter.getId());
    }
    
    @Transactional
    @Test(expected = ConstraintViolationException.class)
    public void testInvalidLetter() {
	    Letter letter = new Letter();
	    
	    //only optional fields
    	letter.setIsPublished(true);
    	letter.setNote("e.g.note");
    	
    	letter.persist();
    	letter.flush();
    	letter.clear();
    }
}