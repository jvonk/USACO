/*
ID: 10002181
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
  public static void main(String[] args) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    BufferedReader br = new BufferedReader(new FileReader("friday.in"));
    int years = Integer.parseInt(br.readLine())-1;
    int dayOfWeek = 2;
    int[] week = new int[] {0, 0, 0, 0, 0, 0, 0};
    int[] month = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    for (int y = 1900; y <= 1900+years; y++) {
        boolean leap = false;
        if (y % 400 == 0) {
            leap = true;
        } else if (y % 100 == 0) {
            leap = false;
        } else if (y % 4 == 0) {
            leap = true;
        } else {
            leap = false;
        }
        if (leap) {
            month[1] = 29;
        } else {
            month[1] = 28;
        }
        for (int m: month) {
            dayOfWeek+=12;
            dayOfWeek%=7;
            week[dayOfWeek]++;
            dayOfWeek+=m-12;
        }
    }
    out.println(week[0]+" "+week[1]+" "+week[2]+" "+week[3]+" "+week[4]+" "+week[5]+" "+week[6]);
    br.close();
    out.close();
  }
}