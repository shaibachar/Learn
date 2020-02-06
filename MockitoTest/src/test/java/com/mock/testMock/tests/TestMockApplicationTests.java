package com.mock.testMock.tests;

import com.mock.testMock.config.MyConfig;
import com.mock.testMock.service.AnotherService;
import com.mock.testMock.service.MyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MyConfig.class})
public class TestMockApplicationTests {

    @Spy
    private MyService myService;

    @InjectMocks
	private AnotherService anotherService;

    @Before
    public void initMocks() {
        System.out.println("init mocks");
        when(myService.add(anyInt(), anyInt())).thenReturn(10);
        when(myService.sayHello()).thenReturn("shalom");
    }

    @Test
    public void testFixCalculation() {
        System.out.println("test");
        int calculate = myService.calculate(6, 7);
        assertEquals(10, calculate);

        String s = myService.sayHello();
		System.out.printf("saying hello:%s%n", s);
        assertEquals("shalom", s);

    }

	@Test
	public void testInjection() {
		String calculationMessage = anotherService.getCalculationMessage();
		System.out.println(calculationMessage);
		assertEquals("calculationResults of 6,7:10", calculationMessage);
	}
}
