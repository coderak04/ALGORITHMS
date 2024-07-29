import java.util.*; 
public class coinProblem 
{ 
    public static void main(String[] args){ 
        Scanner sc=new Scanner(System.in); 
        int denomination[]={1,2,5,10,20,50}; 
        Arrays.sort(denomination);
        System.out.println("The denomination:");
        for(int i=0;i<denomination.length;i++){
            System.out.print(denomination[i]+" ");
        } 
        System.out.print("\nEnter Amount:");
        int amount=sc.nextInt(); 
        int len=denomination.length; 
        List<Integer> ans=new ArrayList<>(); 
        for(int i=len-1;i>=0;i--)
        { 
            while(amount>=denomination[i])
            { 
                amount-=denomination[i]; 
                ans.add(denomination[i]); 
            } 
        }
        System.out.println("Coins required are:");
        for(int i=0;i<ans.size();i++)
        { 
            System.out.print(ans.get(i)+" "); 
        } 
        sc.close(); 
    }
}