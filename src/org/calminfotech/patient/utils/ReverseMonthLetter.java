package org.calminfotech.patient.utils;

public class ReverseMonthLetter {	
	
	enum MonthLetter
	{
		A, B, C, D, E, F, G, H, I, J, K, L ;
	}
	

	//public String monthInLetters(int mnth)
	public String monthInLetters(String mnth)	
	{
		String value = mnth;
		MonthLetter letter = MonthLetter.valueOf(value);
		String character = "0";			
		
		
		switch (letter)		
		{
			case A:
				character = "01";
				break;
			
			case B:
				character = "02";
				break;
			
			case C:
				character = "03";
				break;
			
			case D:
				character = "04";
				break;
			
			case E:
				character = "05";
				break;
				
			case F:
				character = "06";
				break;
			
			case G:
				character = "07";
				break;
			
			case H:
				character = "08";
				break;
			
			case I:
				character = "09";
				break;
			
			case J:
				character = "10";
				break;
				
			case K:
				character = "11";
				break;
			
			case L:
				character = "12";
				break;

			default:
				break;
		}
		return character;		
	}	
	
	
	public static void main(String[] args) {
		
		ReverseMonthLetter monthLetter = new ReverseMonthLetter();
		System.out.println(monthLetter.monthInLetters("K"));

		

	}

}
