public class test {
    public static void main(String[] args) {
        long prev1 = 0;
        long prev2 = 1;
        System.out.println(prev1);
        System.out.println(prev2);
        for (int i = 2; i < 1000; i++) {
            long next = Long.signum(prev2-prev1)*(i%7==0?-1:1)*(i%8==0?2:1)+prev2;
            System.out.println(i+"\t"+next+"\t"+(next-prev2));
            prev1=prev2;
            prev2=next;
        }
    }
}