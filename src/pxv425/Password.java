package pxv425;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Class which containts methods to encrypt and decrypt passwords
 * @author Panagiotis Vakalis
 * @version 28-07-2015
 *
 */
public class Password {

	private static final Map<Character, Integer> CHARACTER_TO_INTEGER = new HashMap<>();
	private static final Map<Integer, Character> INTEGER_TO_CHARACTER = new HashMap<>();
	private static StringBuilder passwordEncrypted = new StringBuilder();
	private static StringBuilder passwordDecrypted = new StringBuilder();
	private static ArrayList<Integer> integers = new ArrayList<>();
	private static ArrayList<Character> characters = new ArrayList<>();
	private static ArrayList<Integer> indexesBackwards = new ArrayList<>();
	private static int indexBack;
	private static ArrayList<Integer> added = new ArrayList<>();
	private static ArrayList<Integer> subtracted = new ArrayList<>();
	
	static{
		/*
		 * Initialize the two static maps
		 */
		CHARACTER_TO_INTEGER.put('a', 0);
		CHARACTER_TO_INTEGER.put('b', 1);
		CHARACTER_TO_INTEGER.put('c', 2);
		CHARACTER_TO_INTEGER.put('d', 3);
		CHARACTER_TO_INTEGER.put('e', 4);
		CHARACTER_TO_INTEGER.put('f', 5);
		CHARACTER_TO_INTEGER.put('g', 6);
		CHARACTER_TO_INTEGER.put('h', 7);
		CHARACTER_TO_INTEGER.put('i', 8);
		CHARACTER_TO_INTEGER.put('j', 9);
		CHARACTER_TO_INTEGER.put('k', 10);
		CHARACTER_TO_INTEGER.put('l', 11);
		CHARACTER_TO_INTEGER.put('m', 12);
		CHARACTER_TO_INTEGER.put('n', 13);
		CHARACTER_TO_INTEGER.put('o', 14);
		CHARACTER_TO_INTEGER.put('p', 15);
		CHARACTER_TO_INTEGER.put('q', 16);
		CHARACTER_TO_INTEGER.put('r', 17);
		CHARACTER_TO_INTEGER.put('s', 18);
		CHARACTER_TO_INTEGER.put('t', 19);
		CHARACTER_TO_INTEGER.put('u', 20);
		CHARACTER_TO_INTEGER.put('v', 21);
		CHARACTER_TO_INTEGER.put('w', 22);
		CHARACTER_TO_INTEGER.put('x', 23);
		CHARACTER_TO_INTEGER.put('y', 24);
		CHARACTER_TO_INTEGER.put('z', 25);
		
		CHARACTER_TO_INTEGER.put('A', 26);
		CHARACTER_TO_INTEGER.put('B', 27);
		CHARACTER_TO_INTEGER.put('C', 28);
		CHARACTER_TO_INTEGER.put('D', 29);
		CHARACTER_TO_INTEGER.put('E', 30);
		CHARACTER_TO_INTEGER.put('F', 31);
		CHARACTER_TO_INTEGER.put('G', 32);
		CHARACTER_TO_INTEGER.put('H', 33);
		CHARACTER_TO_INTEGER.put('I', 34);
		CHARACTER_TO_INTEGER.put('J', 35);
		CHARACTER_TO_INTEGER.put('K', 36);
		CHARACTER_TO_INTEGER.put('L', 37);
		CHARACTER_TO_INTEGER.put('M', 38);
		CHARACTER_TO_INTEGER.put('N', 39);
		CHARACTER_TO_INTEGER.put('O', 40);
		CHARACTER_TO_INTEGER.put('P', 41);
		CHARACTER_TO_INTEGER.put('Q', 42);
		CHARACTER_TO_INTEGER.put('R', 43);
		CHARACTER_TO_INTEGER.put('S', 44);
		CHARACTER_TO_INTEGER.put('T', 45);
		CHARACTER_TO_INTEGER.put('U', 46);
		CHARACTER_TO_INTEGER.put('V', 47);
		CHARACTER_TO_INTEGER.put('W', 48);
		CHARACTER_TO_INTEGER.put('X', 49);
		CHARACTER_TO_INTEGER.put('Y', 50);
		CHARACTER_TO_INTEGER.put('Z', 51);
		
		CHARACTER_TO_INTEGER.put('0', 52);
		CHARACTER_TO_INTEGER.put('1', 53);
		CHARACTER_TO_INTEGER.put('2', 54);
		CHARACTER_TO_INTEGER.put('3', 55);
		CHARACTER_TO_INTEGER.put('4', 56);
		CHARACTER_TO_INTEGER.put('5', 57);
		CHARACTER_TO_INTEGER.put('6', 58);
		CHARACTER_TO_INTEGER.put('7', 59);
		CHARACTER_TO_INTEGER.put('8', 60);
		CHARACTER_TO_INTEGER.put('9', 61);
		
		CHARACTER_TO_INTEGER.put('!', 62);
		CHARACTER_TO_INTEGER.put('@', 63);
		CHARACTER_TO_INTEGER.put('#', 64);
		CHARACTER_TO_INTEGER.put('$', 65);
		CHARACTER_TO_INTEGER.put('%', 66);
		CHARACTER_TO_INTEGER.put('^', 67);
		CHARACTER_TO_INTEGER.put('&', 68);
		CHARACTER_TO_INTEGER.put('*', 69);
		CHARACTER_TO_INTEGER.put('-', 70);
		CHARACTER_TO_INTEGER.put('_', 71);
		CHARACTER_TO_INTEGER.put('~', 72);
		
		INTEGER_TO_CHARACTER.put(0, 'a');
		INTEGER_TO_CHARACTER.put(1, 'b');
		INTEGER_TO_CHARACTER.put(2, 'c');
		INTEGER_TO_CHARACTER.put(3, 'd');
		INTEGER_TO_CHARACTER.put(4, 'e');
		INTEGER_TO_CHARACTER.put(5, 'f');
		INTEGER_TO_CHARACTER.put(6, 'g');
		INTEGER_TO_CHARACTER.put(7, 'h');
		INTEGER_TO_CHARACTER.put(8, 'i');
		INTEGER_TO_CHARACTER.put(9, 'j');
		INTEGER_TO_CHARACTER.put(10, 'k');
		INTEGER_TO_CHARACTER.put(11, 'l');
		INTEGER_TO_CHARACTER.put(12, 'm');
		INTEGER_TO_CHARACTER.put(13, 'n');
		INTEGER_TO_CHARACTER.put(14, 'o');
		INTEGER_TO_CHARACTER.put(15, 'p');
		INTEGER_TO_CHARACTER.put(16, 'q');
		INTEGER_TO_CHARACTER.put(17, 'r');
		INTEGER_TO_CHARACTER.put(18, 's');
		INTEGER_TO_CHARACTER.put(19, 't');
		INTEGER_TO_CHARACTER.put(20, 'u');
		INTEGER_TO_CHARACTER.put(21, 'v');
		INTEGER_TO_CHARACTER.put(22, 'w');
		INTEGER_TO_CHARACTER.put(23, 'x');
		INTEGER_TO_CHARACTER.put(24, 'y');
		INTEGER_TO_CHARACTER.put(25, 'z');
		
		INTEGER_TO_CHARACTER.put(26, 'A');
		INTEGER_TO_CHARACTER.put(27, 'B');
		INTEGER_TO_CHARACTER.put(28, 'C');
		INTEGER_TO_CHARACTER.put(29, 'D');
		INTEGER_TO_CHARACTER.put(30, 'E');
		INTEGER_TO_CHARACTER.put(31, 'F');
		INTEGER_TO_CHARACTER.put(32, 'G');
		INTEGER_TO_CHARACTER.put(33, 'H');
		INTEGER_TO_CHARACTER.put(34, 'I');
		INTEGER_TO_CHARACTER.put(35, 'J');
		INTEGER_TO_CHARACTER.put(36, 'K');
		INTEGER_TO_CHARACTER.put(37, 'L');
		INTEGER_TO_CHARACTER.put(38, 'M');
		INTEGER_TO_CHARACTER.put(39, 'N');
		INTEGER_TO_CHARACTER.put(40, 'O');
		INTEGER_TO_CHARACTER.put(41, 'P');
		INTEGER_TO_CHARACTER.put(42, 'Q');
		INTEGER_TO_CHARACTER.put(43, 'R');
		INTEGER_TO_CHARACTER.put(44, 'S');
		INTEGER_TO_CHARACTER.put(45, 'T');
		INTEGER_TO_CHARACTER.put(46, 'U');
		INTEGER_TO_CHARACTER.put(47, 'V');
		INTEGER_TO_CHARACTER.put(48, 'W');
		INTEGER_TO_CHARACTER.put(49, 'X');
		INTEGER_TO_CHARACTER.put(50, 'Y');
		INTEGER_TO_CHARACTER.put(51, 'Z');
		
		INTEGER_TO_CHARACTER.put(52, '0');
		INTEGER_TO_CHARACTER.put(53, '1');
		INTEGER_TO_CHARACTER.put(54, '2');
		INTEGER_TO_CHARACTER.put(55, '3');
		INTEGER_TO_CHARACTER.put(56, '4');
		INTEGER_TO_CHARACTER.put(57, '5');
		INTEGER_TO_CHARACTER.put(58, '6');
		INTEGER_TO_CHARACTER.put(59, '7');
		INTEGER_TO_CHARACTER.put(60, '8');
		INTEGER_TO_CHARACTER.put(61, '9');
		
		INTEGER_TO_CHARACTER.put(62, '!');
		INTEGER_TO_CHARACTER.put(63, '@');
		INTEGER_TO_CHARACTER.put(64, '#');
		INTEGER_TO_CHARACTER.put(65, '$');
		INTEGER_TO_CHARACTER.put(66, '%');
		INTEGER_TO_CHARACTER.put(67, '^');
		INTEGER_TO_CHARACTER.put(68, '&');
		INTEGER_TO_CHARACTER.put(69, '*');
		INTEGER_TO_CHARACTER.put(70, '-');
		INTEGER_TO_CHARACTER.put(71, '_');
		INTEGER_TO_CHARACTER.put(72, '~');
		
		
	}
	
	/**
	 * Method to encypt password
	 * @param password
	 * @return encrypted password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static final String ENCRYPT_PASSWORD(String password){
		integers = new ArrayList<>();
		indexesBackwards = new ArrayList<>();
		added = new ArrayList<>();
		passwordEncrypted = new StringBuilder();
		/*
		 * This variable is initialised using the length of the string
		 * because it will be used into an arraylist which will count backwards
		 * the indexes
		 */
		indexBack = password.length();
		for (int i = 0; i < password.length(); i++) {
			/*
			 * For every character of the password get the corresponding
			 * integer and add the previous number into the indexesBackwards
			 * arraylist
			 */
			integers.add(CHARACTER_TO_INTEGER.get(password.charAt(i)));
			indexesBackwards.add(indexBack--);
		}
		/*
		 * This variable contains the integers which have been retrieved from map
		 * added by the index which is computed backwards
		 */
		added = addIndexesAndIntegers();
		
		for (int i = 0; i < added.size(); i++) {
			/*
			 * For every integer into the added arraylist get the corresponding character from
			 * the map. If this number is greater than 71 which is the upper limit of the map
			 * then use the ~ symbol and the number which has been assigned followed by the 
			 * ~ symbol in order to define that this is a number and not a sequence of integers.
			 * During the decryption the code will understand that this is the exanct number form the addition.
			 */
			if(added.get(i) <= 71){
				passwordEncrypted.append(INTEGER_TO_CHARACTER.get(added.get(i)).toString());
			}
			else{
				passwordEncrypted.append("~" + added.get(i).toString() + "~");
			}
		}
		return passwordEncrypted.toString();
	}
	
	/**
	 * Method to use the encrypt password method outside the class
	 * @param password
	 * @return encrypted password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public static final String USE_ENCRYPT_PASSWORD(String password){
		return ENCRYPT_PASSWORD(password);
	}
	
	/**
	 * Method which adds every index of the indexesBackwards arraylist
	 * with the integers which have been retrieved from the maps
	 * @return arraylist of integers containing the result of the addition
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static ArrayList<Integer> addIndexesAndIntegers(){
		ArrayList<Integer> aL = new ArrayList<>();
		
		for(int i = 0, j = 0; i < indexesBackwards.size() || j < integers.size(); i++, j++){
			aL.add(indexesBackwards.get(i) + integers.get(j));
		}
		return aL;
	}
	
	/**
	 * Method to decrypt a password
	 * @param decryptedPassword
	 * @return decrypted password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static final String DECRYPT_PASSWORD(String encryptedPassword){
		//Initialize in case of the previous times the user entered a wrong password
		passwordDecrypted = new StringBuilder();
		//If nullPonter exception is shown un comment the inithialization
		indexesBackwards = new ArrayList<>();
		integers = new ArrayList<>();
//		int length = decryptedPassword.length();
		
		//Get the length of the encrypted password
		indexBack = encryptedPassword.length();
		/*
		 * This is the variable where the numbers which are
		 * on the encypted password and are greater than 71
		 * will be stored
		 */
		StringBuilder numberMoreThan71 = new StringBuilder();
		
		/*
		 * If the encrypted password contains the ~ symbol
		 * means that the length of the encrypted password
		 * increased by 3 than the original.
		 * So, subtract 3 from the length.
		 */
		if(encryptedPassword.contains("~")){
			indexBack = indexBack - 3;
		}
		
		for(int j = indexBack; j > 0; j --){
			/*
			 * Put all the indexes
			 */
			indexesBackwards.add(j);
		}
		
		for (int i = 0; i < encryptedPassword.length(); i++) {
//			integers.add(CHARACTER_TO_INTEGER.get(decryptedPassword.charAt(i)));
			if(encryptedPassword.charAt(i) != '~'){
				/*
				 * if the character at the specific index is not ~,
				 * add the correspondint integer into the integer arraylist
				 */
				integers.add(CHARACTER_TO_INTEGER.get(encryptedPassword.charAt(i)));
//				indexesBackwards.add(indexBack--);
			}
			else{
				
				/*
				 * else parse the password until finds the other ~.
				 * While the character is not the ~ symbol add the characters
				 * which are numbers into the numberMoreThan71 variable.
				 * Also change the index of the array by increment the 
				 * value of the i variable.
				 */
//				int index = i;
				do{
					numberMoreThan71.append(encryptedPassword.charAt(i));
					i++;
//					indexesBackwards.add(indexBack--);
				}while(encryptedPassword.charAt(i) != '~');
				/*
				 * add the number without the ~ and continue the for loop
				 * using the last assigned i
				 */
				integers.add(Integer.parseInt(numberMoreThan71.substring(1)));
			}
		}
		
//		for(Integer a : integers){
//			System.out.println(a);
//		}
		
		/*
		 * Call the methdo which sumbtract the indexes from the integers
		 * and assign the result into the subtracted variable
		 */
		subtracted = subtractIndexesFromIntegers();
		
//		for(Integer a : subtracted){
//			System.out.println(a);
//		}
		
		for (int i = 0; i < subtracted.size(); i++) {
			/*
			 * For every integer into the subtracted arraylist
			 * get the corresponding character and append it into the
			 * passwordDecrypted variable.
			 */
			passwordDecrypted.append(INTEGER_TO_CHARACTER.get(subtracted.get(i)).toString());
		}
		
		return passwordDecrypted.toString();
	}
	
	/**
	 * Method which subtracts the backwards indexes from the integers
	 * @return arraylist of integers which is the result of the subtraction
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static ArrayList<Integer> subtractIndexesFromIntegers(){
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 0, j = 0; i < indexesBackwards.size() || j < integers.size(); i++, j++){
			//add the subtracted result
			result.add(integers.get(j) - indexesBackwards.get(i));
		}
		
		return result;
	}
	
	/**
	 * Method to use the decrypt password method outside the class
	 * @param decryptedPassword
	 * @return decrypted password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public static final String USE_DECRYPT_PASSWORD(String encryptedPassword){
		return DECRYPT_PASSWORD(encryptedPassword);
	}
}
