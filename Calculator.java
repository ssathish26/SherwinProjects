package calculator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.ConsoleHandler;


  
public class Calculator {
	private static final Logger logger = Logger.getLogger(Calculator.class.getName());

	    public static void main(String[]args){
	    
	        Scanner obj = new Scanner(System.in);
	      while(true)
	        {
	        logger.info("For your calculations what data type will you be using? (str, int, double). When you're done press exit to leave.");
	        String type = obj.nextLine();
	          if(type.equalsIgnoreCase("exit"))
	          break;
	          else{
	            switch (type)
	            {
	                case "str":
	                    StringCalc();
	                    break;
	                case "int":
	                    IntCalc();
	                    break;
	                case "double":
	                    DoubleCalc();
	                    break;
	                default:
	                   logger.warning("Invalid data type");
	                    break;
	                
	            }
	          }
	        }
	    }
	    static void IntCalc(){
		    Scanner my = new Scanner(System.in);
		    int result=0;
		    String num_str1 = "";
		    String num_str2 = "";
		    int num2 =0;
		    int num1=0;
		    boolean val1 = true;
		    boolean val2 = true;
		    while(true)
		      {
		        while(val1)
		          {
		        	logger.info("What is the first number you want to use?");
		    //num1 = my.nextInt();
		      num_str1 = my.next();
		        try {
		            num1 = Integer.parseInt(num_str1.trim());
		            val1 = false;
		        }
		        catch(NumberFormatException nfe) {
		            val1 = true;
		            logger.warning("Sorry, this input is incorrect! Please try again.");
		        }
		          }
		        
		    while(val2)
		          {
		    	logger.info("What is the next number you want to use?");
		    //num1 = my.nextInt();
		      num_str2 = my.next();
		        try {
		            num2 = Integer.parseInt(num_str2.trim());
		            val2 = false;
		        }
		        catch(NumberFormatException nfe) {
		            val2 = true;
		            logger.warning("Sorry, this input is incorrect! Please try again.");
		        }
		          }
		    logger.info("What is the operator you want to use?(+, *, -, /)");
		    String operator = my.next();
		      switch (operator) {
		                    case "+":
		                        result = result + (num1 + num2);
		                        break;
		                    case "-":
		                        result = result + (num1 - num2);
		                        break;
		                    case "*":
		                        result = result + (num1 * num2);
		                        break;
		                    case "/":
		                        if (num2 == 0.0) {
		                        	logger.warning("Cannot divide by zero!");
		                            val1= true;
		                            val2 = true;
		                            continue;
		                        }
		                        result = result + (num1 / num2);
		                        break;
		                    default:
		                    	logger.warning("Invalid operator!");
		                        val1 = true;
		                        val2 = true;
		                        continue;
		                }
		      logger.info("The total result is "+result+". Press any key to continue or type return to change data type or type clear to clear the results."); 
		        String input = my.next();
		        val1 = true;
		        val2 = true;
		        if(input.equalsIgnoreCase("return"))
		          break;
		        else if(input.equalsIgnoreCase("clear"))
		        {
		          result = 0;
		          continue;
		        }
		      }
		  }
		  static void DoubleCalc(){
		    Scanner my = new Scanner(System.in);
		    double result=0;
		    String num_str1 = "";
		    String num_str2 = "";
		    double num2 = 0.0;
		    double num1 = 0.0;
		    boolean val1 = true;
		    boolean val2 = true;
		    while(true) {
		    while(val1)
		          {
		    	logger.info("What is the first number you want to use?");
		      num_str1 = my.next();
		        try {
		            num1 = Double.parseDouble(num_str1.trim());
		            val1 = false;
		        }
		        catch(NumberFormatException nfe) {
		            val1 = true;
		            logger.warning("Sorry, this input is incorrect! Please try again.");
		        }
		          }
		        
		    while(val2)
		          {
		    	logger.info("What is the next number you want to use?");
		    //num1 = my.nextInt();
		      num_str2 = my.next();
		        try {
		            num2 = Double.parseDouble(num_str2.trim());
		            val2 = false;
		        }
		        catch(NumberFormatException nfe) {
		            val2 = true;
		            logger.warning("Sorry, this input is incorrect! Please try again.");
		        }
		          }
		    logger.info("What is the operator you want to use?(+, *, -, /)");
		    String operator = my.next();
		      switch (operator) {
		                    case "+":
		                        result = result + (num1 + num2);
		                        break;
		                    case "-":
		                        result = result + (num1 - num2);
		                        break;
		                    case "*":
		                        result = result + (num1 * num2);
		                        break;
		                    case "/":
		                        if (num2 == 0.0) {
		                        	logger.warning("Cannot divide by zero!");
		                            val1= true;
		                            val2 = true;
		                            continue;
		                        }
		                        result = result + (num1 / num2);
		                        break;
		                    default:
		                    	logger.warning("Invalid operator!");
		                        val1 = true;
		                        val2 = true;
		                        continue;
		                }
		      logger.info("The total result is "+result+". Press any key to continue or type return to change data type or type clear to clear the results."); 
		        String input = my.next();
		        val1 = true;
		        val2 = true;
		        if(input.equalsIgnoreCase("return"))
		          break;
		        else if(input.equalsIgnoreCase("clear"))
		        {
		          result = 0;
		          continue;
		        }
		    }
		  }
		  static void StringCalc(){
		    Scanner my = new Scanner(System.in);
		    String result="";
		    while(true)
		      {
		    	logger.info("What is the first word you want to use?");
		    String word1 = my.next();
		    logger.info("What is the next word you want to use?");
		    String word2 = my.next();
		    result = result + word1.concat(word2);
		    logger.info("The total result is "+result+". Press any key to continue or type return to change data type or type clear to clear the results."); 
		        String input = my.next();
		        if(input.equalsIgnoreCase("return"))
		          break;
		        else if(input.equalsIgnoreCase("clear"))
		        {
		          result = "";
		          continue;
		        }
		      }
		  }
	}
