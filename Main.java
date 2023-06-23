/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
		ArrayList<Integer> narr=new ArrayList<Integer>(5);
		narr.add(1);
		narr.add(2);
		narr.add(6);
		narr.add(3);
		narr.add(2,5);
		narr.add(6);
		System.out.println(narr);
		narr.remove(2);
		System.out.println(narr);
		System.out.println("The element at index 2 is:"+narr.get(2));
		Collections.sort(narr);//USES MERGE SORT
		System.out.println(narr);//PRINTING AFTER SORT IS DONE
		narr.set(2,8); //SET METHOD
		System.out.println("After changing the element at index 2\n New ArrayList is:"+narr);
		for(int i=0;i<narr.size();i++)//ITERATING USING LOOP
		    System.out.println(narr.get(i));
		for(Integer in:narr)//BY USING FOR EACH LOOP
		    System.out.println("By using for each loop:"+in);
		System.out.println(narr.isEmpty());//CHECKS IF ARRAY LIST IS EMPTY OR NOT
		System.out.println(narr.contains(8));//CHECKS IF 8 IS IN THE ARRAY LIST R NOT
		System.out.println(narr.lastIndexOf(6));//RETURNS THE LAST OCCURENCE OF GIVEN VALUE(INDEX)
		System.out.println(narr.subList(2,4));//RETURNS SUBLIST FROM STARTING INDEX(INCLUDES) TO LAST INDEX(EXCLUDES)
	}
}
