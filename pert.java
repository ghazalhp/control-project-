import java.util.ArrayList;
import java.util.Scanner;

public class pert {
	public static void dfs(int v, int[][] g, ArrayList<Integer> path,
			int[] mark, int[] tl, int[] te) {
		mark[v] = 1;
		if (te[v] != tl[v])
			return;
		
		path.add(v);
		for (int i = 0; i < te.length; i++)
			if (g[v][i] > 0 && mark[i] == 0)
				dfs(i, g, path, mark, tl, te);
		if (path.get(path.size() - 1) == (te.length - 1)) {
			for (int i = 0; i < path.size(); i++)
				System.out.print(path.get(i) + " ");
			System.out.println();
		}
		mark[v] = 0;
		path.remove(path.size()-1);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), e = in.nextInt();
		int[][] g = new int[n][n];
		int[] te = new int[n];
		int[] tl = new int[n];
		for (int k = 0; k < e; k++) {
			int i = in.nextInt(), j = in.nextInt();
			g[i][j] = in.nextInt();
		}
		te[0] = 0;
		for (int i = 1; i < n; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (g[j][i] > 0 && max < (te[j] + g[j][i]))
					max = te[j] + g[j][i];
			}
			te[i] = max;
		}
		tl[n - 1] = te[n - 1];
		for (int i = n - 2; i > -1; i--) {
			int min = 1000;
			for (int j = n - 1; j > i; j--) {
				if (g[i][j] > 0 && min > (tl[j] - g[i][j]))
					min = (tl[j] - g[i][j]);
			}
			tl[i] = min;
		}
		for (int i = 0; i < n; i++)
			System.out.println(" i : " + i + " te[i] " + te[i] + " tl[i] "
					+ tl[i]);
		ArrayList<Integer> path = new ArrayList<Integer>();
		int[] mark = new int[n];
		System.out.println("masir bohrani : ");
		dfs(0, g, path, mark, te, tl);
	}

}
