import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Solution {
    private final static long mod = 1000000007;

    private static void printArr2(long arr[][]) {

        int i, j, n = arr.length, m = arr[0].length;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printArr(int arr[][]) {
        int i, j, n = arr.length, m = arr[0].length;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static long power(long x, long y) {
        long temp;
        if (y == 0)
            return 1;
        temp = power(x, y / 2);
        temp = (temp * temp) % mod;
        if (y % 2 == 0)
            return temp;
        else
            return ((x % mod) * temp) % mod;
    }


    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


    private static class Pair {
        int value;
        int idx;

        public Pair(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return value == pair.value &&
                    idx == pair.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, idx);
        }
    }

    private static int cmp(Pair p, Pair q){
        return p.value ==q.value ?p.idx -q.idx :p.value -q.value;
    }

    public static int solution(int[] A){
        if(A.length<3)return A.length;
        int first=A[0],second=A[0],firstCount=1,secondCount=1,i,j,ans=1,tmp;
        for(i=0,j=1;i<j&&j<A.length;j++){
            if(first==second){
                if(A[j]!=first){
                    second=A[j];
                    secondCount=1;
                }
                else{
                    firstCount++;
                    secondCount++;
                }
                ans=max(ans,j-i+1);
            }
            else{
                if(A[j]==first){
                    firstCount++;
                    ans=max(ans,j-i+1);
                }
                else if(A[j]==second){
                    secondCount++;
                    ans=max(ans,j-i+1);
                }
                else{
                    ans=max(ans,j-i);
                    if(A[j-1]==first){
                        for(;secondCount>0&&i<j;i++){
                            if(A[i]==second)secondCount--;
                            else if(A[i]==first)firstCount--;
                        }
                        second=A[j];
                        secondCount=1;
                    }
                    else if(A[j-1]==second){
                        for(;firstCount>0&&i<j;i++){
                            if(A[i]==second)secondCount--;
                            else if(A[i]==first)firstCount--;
                        }
                        first=A[j];
                        firstCount=1;
                    }
                }
            }
        }
        ans=max(ans,j-i);
        return ans;
    }

    private static boolean isValid(String words[], int parts){
        String currLine = "(1/"+parts+")";
        int currLineNo=1;
        for(String word: words){
            if(String.format("%s %s", currLine,word).length()>30){
                currLineNo++;
                currLine=String.format( "(%d/%d) %s", currLineNo,parts,word);
            }
            else{
                currLine= String.format("%s %s", currLine, word);
            }
        }
        return (currLineNo<=parts);

    }

    private static int countLines(String words[]) {
        int lo=1,hi=words.length,mid;
        while(lo<hi){
            mid=(lo+hi)/2;
            if(isValid(words, mid)){
                hi=mid;
            }
            else lo=mid+1;
        }
        return lo;

    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for(String s=br.readLine();s!=null;s=br.readLine()){
                String words[]=s.split(" ");
                System.out.println(countLines(words));
            }
        }
    }

    static class FastReader {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] nextIntArr(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        long[] nextLongArr(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

    }

    static class FastWriter {
        BufferedWriter bw;
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        FastWriter() {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        <T> void print(T obj) throws IOException {
            bw.write(obj.toString());
            bw.flush();
        }

        void println() throws IOException {
            print("\n");
        }

        <T> void println(T obj) throws IOException {
            print(obj.toString() + "\n");
        }

        <T> void printArrLn(T[] arr) throws IOException {
            for (int i = 0; i < arr.length; i++) {
                print(arr[i] + " ");
            }
            println();
        }

        void printCharN(char c, int n) throws IOException {
            for (int i = 0; i < n; i++) {
                print(c);
            }
        }
    }
}
