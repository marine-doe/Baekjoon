import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	static Stack<Integer> st;
	static StringBuilder sb;
	static List<Integer>[] mama;
	static int[] seq;
	static int v;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc < 11; tc++) {
			v = sc.nextInt();
			int e = sc.nextInt();

			mama = new ArrayList[v + 1]; // 부모 리스트 생성

			for (int i = 1; i < v + 1; i++) {
				mama[i] = new ArrayList<>();
			}

			seq = new int[v + 1];

			visited = new boolean[v + 1];

			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				mama[a].add(b);
				seq[b]++;
			}

			sb = new StringBuilder("#" + tc + " ");
//			TopologicalSortingQueue();
			st = new Stack<>();
			for (int i = 1; i < v + 1; i++) {
				if(seq[i] == 0)
					TopologicalSortingStack(i);
			}
			while(!st.isEmpty()) {
				sb.append(st.pop() + " ");
			}

			System.out.println(sb);
		}
	}

	private static void TopologicalSortingStack(int node) {
		visited[node] = true;
		for (int i = 0; i < mama[node].size(); i++) {
			int adj = mama[node].get(i);
			if (!visited[adj]) {
				TopologicalSortingStack(adj);
			}
		}
		st.push(node);
	}

	private static void TopologicalSortingQueue() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < v + 1; i++) {
			if (seq[i] == 0) {
				q.offer(i);
				seq[i]--;
			}
		}

		while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node + " ");
			for (int i = 0; i < mama[node].size(); i++) {
				if (--seq[mama[node].get(i)] == 0) {
					q.offer(mama[node].get(i));
				}
			}
		}
	}
}