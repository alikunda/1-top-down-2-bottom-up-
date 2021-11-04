/**
 * Name: Mohammed Ali Kunda
 */

/**
 * this program implement two dynamic algorithm, 1) top down, 2) bottom up
 */

import java.util.Scanner;
public class assignment4 {

    /**
     * @param args the command line arguments
     */
    static  String yes_or_no;
    public assignment4() {
    }
      //global variable to keep the count of recursion calls & iteration
      static int counter_1 = 0;
      static int counter_2 = 0;
      static int[] arr1 = new int[10];

    public static void main(String[] args) {

        Scanner key = new Scanner(System.in);
        
        int[] array = {1,5,8,9,10,17,17,20};
        do{

            System.out.println("Index: 1 2 3 4 5  6  7  8  9  10");  //print the index
            print(array);   //print the array
            System.out.println("Enter the size of n: ");
             int n = key.nextInt();

            while(n > array.length)
            {
                System.out.println("ERROR! Value of n is out of bound, please enter the value of n(between 1 to 10): ");
                n = key.nextInt();
            }
            System.out.print("best decomposition: ");
            int max = memoized_Cut_Rod(array, n);
            System.out.println("\nCut Rod method: ");
            System.out.println("Max Revenue: "+max);
            System.out.println("Recursive call made: "+counter_1);
            System.out.println("-----------------------------------");
            System.out.println("extened bottom up cut rod method: ");
            int [][] max1 = extended_bottom_up_cut_rod(array, n);
            int maxx = max1[0][n];
            System.out.println("Max Revenue: "+maxx);
            System.out.println("iteration made: "+counter_2);
            System.out.println("-----------------------------------");
            System.out.println("Do you want to play it again: ");
            yes_or_no = key.next();

            if(yes_or_no.equalsIgnoreCase("No")||yes_or_no.equalsIgnoreCase("N")){
                System.out.println("Thank you!");
            }
           
        }
        while(yes_or_no.equalsIgnoreCase("yes")||yes_or_no.equalsIgnoreCase("Y") );
        {

        }


    }
    public static void print(int[] arr)
    {
        System.out.print("Price: ");
        for(int i = 0; i<arr.length;i++)
        {
            if(arr[arr.length-1]==arr[i])
            {
                System.out.print(arr[i]);
            }
            else {
                System.out.print(arr[i] + ",");
            }

        }
        System.out.println("");
    }
    public static int memoized_Cut_Rod(int[] P,int n)
    {
        int[] r = new int[n+1];
        for(int i = 0; i <r.length;i++)
        {
            r[i]=Integer.MIN_VALUE;
        }
        
        return memoized_cut_rod_aux(P, n, r);
    }
    public static int memoized_cut_rod_aux(int[] p, int n, int[]r)
    {
        
        if(r[n]>=0)
        {
            return r[n];
        }
        int q;

        if(n==0)
        {
            q=0;
        }
        else
        {
            q = Integer.MIN_VALUE;
            for(int i= 1; i <n;i++)
            {
                q= Math.max(q, p[i]+memoized_cut_rod_aux(p, n-i-1, r));
                
               
            }
        }
        counter_1+=1;
        r[n]= q;
        for(int i = 0; i < r.length;i++ )
        {
            if(q==r[i])
            {
                System.out.print(i+" ");
            }
            if(i>n)
            {
                break;
            }
           
        }
        
        
        return q;
    }


    public static int[][] extended_bottom_up_cut_rod(int[] p, int n)
    {
        int q;
        int[] r = new int[n+1];
        int[] s = new int[n+1];
        r[0]=0;
        for(int j =1;j<=n;j++)
        {
            q = Integer.MIN_VALUE;
            for(int i = 1; i < j;i++)
            {
                if(q<p[i]+r[j-i-1])
                {
                    q=p[i]+r[j-i-1];
                    s[j]=i+1;
                    counter_2+=1;     //keeping the track of the iterations
                }

            }
            r[j]=q;

        }

        int[][] r_s = new int[2][n + 1];
        for (int i = 0; i < n + 1; i++) {
            r_s[0][i] = r[i];
            r_s[1][i] = s[i];
        }
       
        return r_s;
    }
}
