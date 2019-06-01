package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CountDNA {
	public static void main(String[] args) throws FileNotFoundException {
	    if (args.length != 1) {
	      throw new RuntimeException("Missing argument. Usage: java MainSumNumbersInAFile fileName") ;
	    }

	    System.out.println("Arguments: " + args[0]) ;

	    long[] resultado = new long[]{0,0,0,0};
	    long initTime = System.currentTimeMillis();
	    try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
	      resultado = stream
	    		  
	              .map(
	            	line -> {
	            	  long[] localCounts = new long[4];
	            	  //
	            	  for (int i=0; i<line.length(); i++) {
	            		  char c = line.toUpperCase().charAt(i);
	            		  switch(c) {
	            		  case 'A' : localCounts[0] ++; break;
	            		  case 'C' : localCounts[1] ++; break;
	            		  case 'G' : localCounts[2] ++; break;
	            		  case 'T' : localCounts[3] ++; break;
	            		  }
	            	  }
	            	  return localCounts;
	              })
	              .reduce(
	            		  new long[]{0,0,0,0} , 
	            		  (vector1, vector2) -> { 
	            			  long[] totalCounts = new long[4];
	            			  for (int i = 0; i<vector1.length; i++) {
	            				  totalCounts[i] = vector1[i] + vector2[i];
	            			  }
	            			  return totalCounts;
	            		  });
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    long computingTime = System.currentTimeMillis() - initTime ;

	    System.out.println("Computing time: " + computingTime);
	    System.out.printf("%d %d %d %d", resultado[0], resultado[1], resultado[2], resultado[3]);
	  }
}
