package BankerAlgorithm;

import java.util.Scanner;
public class banker {

	//processes=pro, resources=re.
	private int need[][], allocate[][], max[][], avail[], pro, re;
	
	private void input(){
		Scanner sn = new Scanner (System.in);
		System.out.print("no. of processes: ");
		pro = sn.nextInt();
		System.out.print("no. of resources: ");
		re = sn.nextInt();
		
		need = new int [pro][re];
		allocate = new int [pro][re];
		max = new int [pro][re];
		avail = new int [re];
		
		System.out.println("Enter allocation number");
		for (int i=0;i<pro;i++)
			for (int j=0;j<re;j++)
				allocate[i][j] = sn.nextInt();
		
		System.out.println("Enter max number");
		for (int i=0;i<pro;i++)
			for (int j=0;j<re;j++)
				max[i][j] = sn.nextInt();
		
		System.out.println("Enter available number");
		for (int j=0;j<re;j++)
			avail[j] = sn.nextInt();
		
		sn.close();
	}
	
	private int [][] cal_need(){
		for (int i=0;i<pro;i++)
			for (int j=0;j<re;j++)
				need[i][j] = max[i][j] - allocate[i][j];
		return need;
	}
	private boolean check(int i){
		for (int j=0;j<re;j++)
			if (avail[j]>need[i][j])
				return true;
		return false;
	}
	
	public void isSafe(){
		input();
		cal_need();
		boolean done[] = new boolean [pro];
		int j = 0;
		while(j<pro){//until all process allocate
			boolean allocated = false;
			for (int i=0;i<pro;i++)
				if (!done[i] && check(i)){
					for (int k=0;k<re;k++)
						avail[k] = avail[k] - need[i][k] + max[i][k];
					System.out.println("Allocated process: "+ i);
					allocated = done[i] = true;
					j++;
				}
			if (!allocated) break;
		}
		if (j == pro)
			System.out.println("\n Safety allocated");
		else
			System.out.println("All process can't be allocated safely");
	}
	
	public static void main (String agrs[]){
		new banker().isSafe();
	}
}
