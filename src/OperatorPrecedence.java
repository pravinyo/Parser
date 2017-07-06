import java.util.Arrays;
import java.util.Stack;

public class OperatorPrecedence {
	public static void main(String args[])
    {	String ter = "i+*$";
        char prec[][] = {
        		{'-','>','<','>'},
        		{'<','>','<','>'},
        		{'<','>','>','>'},
        		{'<','<','<','A'}
        	};
        String prod[] = {"E","E+E","E*E","i"},
        		input = "i+i",
        		temp="";
        Stack<Character> stack = new Stack<Character>()
        		, ip = new Stack<Character>();
        stack.push((Character)'$');
        ip.push((Character)'$');
		int i,j,k;
		for(i=input.length()-1;i>=0;i--)
            ip.push((Character)input.charAt(i));
        char a,b,p;
		System.out.print("\n"+prod[0]+" -> ");
		for(i=1;i<prod.length;i++)
			if(i==prod.length-1)
				System.out.print(prod[i]);
			else
				System.out.print(prod[i]+" | ");
		System.out.print("\n\n\t");
		for(i=0;i<ter.length();i++)
			System.out.print(ter.charAt(i)+"\t");
		System.out.println("\n\t----------------------------------");
		for(i=0;i<ter.length();i++)
		{	System.out.print("\n"+ter.charAt(i));
				System.out.print("   |\t");
			for(j=0;j<ter.length();j++)
				System.out.print(prec[i][j]+"\t");	}
		System.out.printf("\n%20s%15s\n\n","\n\nSTACK","INPUT");
        while(true)
        {	for(i=0;i<stack.size();i++)
				System.out.print((stack.get(i)+" ").replaceAll("i","id"));
        
            if(stack.size()>3)
				System.out.print("\t");
            else
                System.out.printf("\t\t");
            
            for(i=ip.size()-1;i>=0;i--)
                System.out.print((ip.get(i)+" ").replaceAll("i","id"));
            
            
			System.out.println();
			
            a = (char)stack.peek();
			if(ter.indexOf(a)<0)
				a = (char)stack.get(stack.size()-2);
			else
				a = (char)stack.peek();
			
            b = (char)ip.peek();
            p = prec[ter.indexOf(a)][ter.indexOf(b)];
            
            if(p=='A')
            {	System.out.println("\nAccept.");
                break;	 
            }
            else if(p=='<'||p=='=')//precedence of input is higher or equal to stack symbol 
            {	ip.pop();
                stack.push((Character)b);	 
            }
            else if(p=='>')
			 {	
            	for(j=1;j<stack.size();j++)//for string of stack
				    temp = temp.concat(stack.get(j).toString());
					
			      for(j=0;j<temp.length();j++)//match substring for production rules if found reduce
					{	if(Arrays.asList(prod).contains(temp.substring(j)))
						{	for(k=0;k<temp.substring(j).length();k++)
								stack.pop();
							break;	 
						}	
					}
			 
			      //replce with start of the production
			        stack.push((Character)prod[0].charAt(0));
					temp="";	
				}
            	else{
            		System.out.println("\nERROR.");
			        break;		 
			        }
            }//end of while loop
        }//end of main
}
