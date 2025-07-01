package test;
import java.util.*;
public class huawei {


    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // 读取乡镇个数
            int n = scanner.nextInt();

            // 创建距离矩阵，大小为(n+1)*(n+1)，因为包含救援物质集结点
            int[][] graph = new int[n + 1][n + 1];

            // 读取距离矩阵
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }

            // 读取目标乡镇序号
            int target = scanner.nextInt();

            // 计算最短路径
            int shortestPath = dijkstra(graph, 0, target, n + 1);

            // 输出结果
            System.out.println(shortestPath);

            scanner.close();
        }

        // Dijkstra算法实现
        private static int dijkstra(int[][] graph, int start, int end, int vertices) {
            // 存储从起点到每个顶点的最短距离
            int[] distance = new int[vertices];
            // 标记顶点是否已被访问
            boolean[] visited = new boolean[vertices];
            // 初始化距离数组
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[start] = 0;
            for (int count = 0; count < vertices - 1; count++) {
                // 找出当前未访问的距离最小的顶点
                int u = minDistance(distance, visited, vertices);
                // 标记该顶点为已访问
                visited[u] = true;
                // 更新所有相邻顶点的距离
                for (int v = 0; v < vertices; v++) {
                    // 如果顶点v未被访问，存在从u到v的边，且从起点经过u到v的距离小于当前记录的距离
                    if (!visited[v] &&
                            graph[u][v] != 0 &&
                            distance[u] != Integer.MAX_VALUE &&
                            distance[u] + graph[u][v] < distance[v]) {
                        distance[v] = distance[u] + graph[u][v];
                    }
                }
            }
            return distance[end];
        }

        // 找出未访问顶点中距离最小的顶点
        private static int minDistance(int[] distance, boolean[] visited, int vertices) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && distance[v] <= min) {
                    min = distance[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }
    }

}
