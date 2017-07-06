import java.util.Arrays;
import java.util.Stack;

public class LALR {

	public static void main(String args[])
	{
		String table[][] = {
				{"S36","S47","-","1","2"},
				{"-","-","Accept!","-","-"},
				{"S36","S47","-","-","5"},
				{"S36","S47","-","-","89"},
				{"r3","r3","r3","-","-"},
				{"-","-","r1","-","-"},
				{"r2","r2","r2","-","-",}
			}, 
				r[][] = {
						{"S","AA"},
						{"A","aA"},
						{"A","b"}
					};;
					
		String ter = "ab$SA",
				input = "aabb",
				stat[] = {"0","1","2","36","47","5","89"};
		
		Stack<String> stack = new Stack<String>(),
				ip = new Stack<String>();
		
		
		stack.push("$");
		stack.push("0");
		ip.push("$");
		
		
		int i,j;
		
		for(i=input.length()-1;i>=0;i--)
			ip.push(input.charAt(i)+"");
		
		System.out.println("\nAugmented Grammer :\n");
		
		for(i=0;i<r.length;i++)
			System.out.println(i+1+") "+r[i][0]+" -> "+r[i][1]);
		
		System.out.print("\nLALR Parser Table : \n\n\t ");
		for(i=0;i<ter.length();i++)
			System.out.print(ter.charAt(i)+"\t");
		System.out.println("\n\t----------------------------------");
		for(i=0;i<stat.length;i++)
		{
			System.out.print("\n"+stat[i]);
			if(stat[i].length()==1)
				System.out.print("   |\t");
			else
				System.out.print("  |\t");
			for(j=0;j<ter.length();j++)
				System.out.print(table[i][j]+"\t");
		}
		
		
		System.out.printf("\n%20s%24s\n\n","\n\nSTACK","INPUT");
		String x,a,t,x2;
		
		while(true)
		{
			for(i=0;i<stack.size();i++)
				System.out.print(stack.get(i)+" ");
			if(stack.size()<4)
				System.out.print("\t\t\t");
			else if(stack.size()>3&&stack.size()<7)
				System.out.print("\t\t");
			else
				System.out.print("\t");
			for(i=ip.size()-1;i>=0;i--)
				System.out.print(ip.get(i)+" ");
			System.out.println();
			x = stack.peek().toString();
			a = ip.peek().toString();
			if(Arrays.asList(stat).contains(x))
			{
				t = table[search(stat,x)][ter.indexOf(a)];
				if(t.contains("S"))
				{
ip.pop();
					stack.push(a);
					stack.push(table[search(stat,x)][ter.indexOf(a)].substring(1));	
}
				else if(t.contains("r"))
				{	
for(i=0;i<r[Integer.parseInt(t.substring(1))-1][1].length();i++)
						{	stack.pop();
							stack.pop();	}
					stack.push(r[Integer.parseInt(t.substring(1))-1][0]);	 }
				else
				{	System.out.println("\nAccept!");
					break;	 }	}
			else
			{	x2 = (stack.get(stack.size()-2)).toString();
				t = table[search(stat,x2)][ter.indexOf(x)];
				stack.push(t);	 }	}	}
	static int search(String stat[],String x)
	{	int i;
		for(i=0;i<stat.length;i++)
		{	if(stat[i].equals(x))
				break;	 }
		return i;	 }
}
