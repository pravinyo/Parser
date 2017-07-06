import java.util.EmptyStackException;
import java.util.Stack;

public class PredictiveParser {
	public static void main(String args[]) {
		String var[] = {"E","F","T"},
				var1 = "EFT",
				ter[] = {"i","+","(",")","$"},
				ter1 = "i+()$",
				table [][]= {
						{"TF","-","TF","-","-"},
						{"-","+TF","-","e","e"},
						{"i","-","(E)","-","-"}
						},
				ip1 = "i+i$";
		int i,j;
		
		char ip2[] = new char[10], c1, c2;
		ip2 = ip1.toCharArray();
		Stack<String> ip = new Stack<String>();
		for(i = ip2.length-1;i >= 0;i--)
			ip.push(""+ip2[i]);
		Stack<String> stack = new Stack<String>();
		stack.push("$");
		stack.push(var[0]);
		String x, a, value = "";
		System.out.print("\nPredictive Parser / LL(1) Parser Table : \n\n\t ");
		for(i=0;i<ter1.length();i++)
			System.out.print(ter[i]+"\t");
		System.out.println("\n\t----------------------------------");
		for(i=0;i<var1.length();i++)
		{
			System.out.print("\n"+var[i]+"   |\t");
			for(j=0;j<ter1.length();j++)
				System.out.print(table[i][j]+"\t");
		}
		System.out.printf("\n%-20s %-20s\n\n","\n\nSTACK","INPUT");
		while(true) {
			for(i=0;i<stack.size();i++)
				System.out.print(stack.get(i)+" ");
			if(stack.size()>3)
				System.out.print("\t");
			else
				System.out.printf("\t\t");
			for(i=ip.size()-1;i>=0;i--)
				System.out.print(ip.get(i)+" ");
			System.out.println();
			x = stack.peek().toString();
			a = ip.peek().toString();
			c1 = x.charAt(0);
			c2 = a.charAt(0);
			if( (c1 == c2 && c2 == '$') || (c1 == 'e' && c2 == '$') )
			{
				System.out.printf("%-20s\n","Accept!");
				break;
			}
			else if(c1 == c2 && c2 != '$')
			{
				try
				{
					stack.pop();
					ip.pop(); 
				}
				catch(EmptyStackException e) {
					
				}
			}
			else if(table[searchvar(var1,x)][searchter(ter1,a)] != "-")
			{
				try
				{
					stack.pop(); 
				}
				catch(EmptyStackException e) {}
				value = table[searchvar(var1,x)][searchter(ter1,a)];
				for(i = (value.length())-1;i >= 0;i--)
					stack.push(""+(value.charAt(i)));
			}
			else {
				System.out.println("Error");
				break;
			}
		}
	}
	public static int searchvar(String var1,String x) {
		int q = var1.indexOf(x);
		return q;
	}
	public static int searchter(String ter1,String a) {
	int q = ter1.indexOf(a);
		return q;
	}

}
