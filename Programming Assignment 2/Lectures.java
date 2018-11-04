import java.io.*;
import java.util.*;

public class Lectures {
    private class LectureNode implements Comparable<LectureNode>{
        int start, end;

        public LectureNode(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean conflicts(LectureNode other) {
            if (other.end <= start || other.start >= end) {
                return false;
            }
            return true;
        }
        @Override
        public int compareTo(LectureNode other) {
            if (other.start != start) {
                return Integer.compare(start, other.start);
            } else {
                return Integer.compare(end, other.end);
            }
        }
    }

    private ArrayList<LectureNode> lectures;
    private int minHalls;
    private ArrayList<LinkedList<LectureNode>> lectureHalls;

    public Lectures() {
        lectures = new ArrayList<>();
        minHalls = 0;
        lectureHalls = new ArrayList<>();
    }

    private int calculateMinimumHalls(int N, int[] start, int[] end) {
    	for (int i = 0; i < N; i++) {
    	    lectureHalls.add(new LinkedList<>());
    	    lectures.add(new LectureNode(start[i], end[i]));
        }
        Collections.sort(lectures);
    	for (LectureNode l: lectures) {
    	    for (LinkedList<LectureNode> lh: lectureHalls) {
    	        if (lh.isEmpty()) {
    	            lh.add(l);
    	            minHalls++;
    	            break;
                }
                boolean flag = false;
                for (LectureNode o: lh) {
                    if (l.conflicts(o)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    lh.add(l);
                    break;
                }
            }
        }
        return minHalls;
    }

    private int calculateMinimumCancels(int N, int L, int[] start, int[] end) {
       int[] sizesOfLectureHalls = new int[minHalls];
       for (int i = 0; i < minHalls; i++) {
           sizesOfLectureHalls[i] = lectureHalls.get(i).size();
       }
       Arrays.sort(sizesOfLectureHalls);
       if (L >= minHalls) {
           return 0;
       }
       int minCancels = 0;
       for (int i = 0; i < minHalls - L; i++) {
           minCancels += sizesOfLectureHalls[i];
       }
       return minCancels;
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int testCases = sc.nextInt();
        for (int tc = 0; tc < testCases; tc++) {
            int N = sc.nextInt();
            int L = sc.nextInt();
            int[] start = new int[N];
            for (int i = 0; i < N; i++) {
                start[i] = sc.nextInt();
            }
            int[] end = new int[N];
            for (int i = 0; i < N; i++) {
                end[i] = sc.nextInt();
            }
            Lectures lectures = new Lectures();
            int minHalls = lectures.calculateMinimumHalls(N, Arrays.copyOf(start, start.length), Arrays.copyOf(end, end.length));
            System.out.println(minHalls);
            int minCancels = lectures.calculateMinimumCancels(N, L, start, end);
            System.out.println(minCancels);
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}