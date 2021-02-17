package edu.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.Greeter;

public class GreeterTest {

	/*
	  Tarefa
	  
	  	Dado o nome de uma pessoa, retorne um cumprimento apropriado, a saber:
	  	
	  	Não deve haver espaços em branco no começo ou no fim do cumprimento;
	  	Apenas a primeira letra do nome deve ser maiúscula;
	  	Se o horário do cumprimento for 06:00~12:00, o cumprimento deve ser 'Bom dia <nome>';
	  	Se o horário do cumprimento for 18:00~06:00, o cumprimento deve ser 'Boa noite <nome>'; 
	  	Se o horário do cumprimento for 12:00~18:00, o cumprimento deve ser 'Olá <nome>';
	*/
	
	private static List<String> getSampleNames() {
		List<String> sampleNames = new ArrayList<String>();
		
		sampleNames.add(null);
		sampleNames.add("");
		sampleNames.add("   \t\t  ");
		sampleNames.add("john");
		sampleNames.add("Mary");
		sampleNames.add("ELIZABETH");

		return sampleNames;
	}
	
	private static List<String> getFormattedSampleNames() {
		List<String> formattedSampleNames = new ArrayList<String>();
		
		formattedSampleNames.add("");
		formattedSampleNames.add("");
		formattedSampleNames.add("");
		formattedSampleNames.add("John");
		formattedSampleNames.add("Mary");
		formattedSampleNames.add("Elizabeth");	
		
		return formattedSampleNames;
	}
	
	private static List<LocalTime> getMorningTimes() {
		List<LocalTime> morningTimes = new ArrayList<LocalTime>();
		
		morningTimes.add(LocalTime.parse("06:00"));
		morningTimes.add(LocalTime.parse("07:35"));
		morningTimes.add(LocalTime.parse("11:59"));

		return morningTimes;
	}
	
	private static List<LocalTime> getNightTimes() {
		List<LocalTime> nightTimes = new ArrayList<LocalTime>();
		
		nightTimes.add(LocalTime.parse("18:00"));
		nightTimes.add(LocalTime.parse("23:59"));
		nightTimes.add(LocalTime.parse("00:00"));
		nightTimes.add(LocalTime.parse("05:59"));

		return nightTimes;
	}
	
	private static List<LocalTime> getHelloTimes() {
		List<LocalTime> helloTimes = new ArrayList<LocalTime>();
		
		helloTimes.add(LocalTime.parse("12:00"));
		helloTimes.add(LocalTime.parse("13:03"));
		helloTimes.add(LocalTime.parse("17:59"));

		return helloTimes;
	}
	
	
	@Test
	public void greetingIsTrimmed() {
		List<String> sampleNames = getSampleNames();

		for (String sampleName : sampleNames) {
			String greeting = Greeter.greet(sampleName, LocalTime.now());
			
			assertFalse(greeting.startsWith(" "));
			assertFalse(greeting.endsWith(" "));
		}
	}
	
	@Test
	public void canGreetNamelessGoodMorning() {
		List<String> sampleNames = getSampleNames();
		List<LocalTime> morningTimes = getMorningTimes();
		
		for (LocalTime morningTime : morningTimes) {
			for (int i = 0; i < 3; i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, morningTime);

				assertTrue(greeting.equals("Bom dia"));
			}
		}
	}

	@Test
	public void canGreetNamedGoodMorning() {
		List<String> sampleNames = getSampleNames();
		List<String> formattedSampleNames = getFormattedSampleNames();
		List<LocalTime> morningTimes = getMorningTimes();

		for (LocalTime morningTime : morningTimes) {
	 		for (int i = 3; i < sampleNames.size(); i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, morningTime);
				String formattedSampleName = formattedSampleNames.get(i);
				
				assertTrue(greeting.startsWith("Bom dia"));
				assertTrue(greeting.endsWith(" " + formattedSampleName));
			}
		}
	}
	
	@Test
	public void canGreetNamelessGoodNight() {
		List<String> sampleNames = getSampleNames();
		List<LocalTime> nightTimes = getNightTimes();
		
		for (LocalTime nightTime : nightTimes) {
			for (int i = 0; i < 3; i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, nightTime);

				assertTrue(greeting.equals("Boa noite"));
			}
		}
	}
	
	@Test
	public void canGreetNamedGoodNight() {
		List<String> sampleNames = getSampleNames();
		List<String> formattedSampleNames = getFormattedSampleNames();
		List<LocalTime> nightTimes = getNightTimes();

		for (LocalTime nightTime : nightTimes) {
	 		for (int i = 3; i < sampleNames.size(); i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, nightTime);
				String formattedSampleName = formattedSampleNames.get(i);
				
				assertTrue(greeting.startsWith("Boa noite"));
				assertTrue(greeting.endsWith(" " + formattedSampleName));
			}
		}
	}
	
	@Test
	public void canGreetNamelessHello() {
		List<String> sampleNames = getSampleNames();
		List<LocalTime> helloTimes = getHelloTimes();
		
		for (LocalTime helloTime : helloTimes) {
			for (int i = 0; i < 3; i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, helloTime);

				assertTrue(greeting.equals("Olá"));
			}
		}
	}
	
	@Test
	public void canGreetNamedHello() {
		List<String> sampleNames = getSampleNames();
		List<String> formattedSampleNames = getFormattedSampleNames();
		List<LocalTime> helloTimes = getHelloTimes();

		for (LocalTime helloTime : helloTimes) {
	 		for (int i = 3; i < sampleNames.size(); i++) {
				String sampleName = sampleNames.get(i);
				String greeting = Greeter.greet(sampleName, helloTime);
				String formattedSampleName = formattedSampleNames.get(i);
				
				assertTrue(greeting.startsWith("Olá"));
				assertTrue(greeting.endsWith(" " + formattedSampleName));
			}
		}
	}
}
