package test;
import java.util.*;

public class huawei2 {



    public static class Main {
        static class Task {
            int id;
            int priority;
            int remainingTime;
            int orderAdded;

            public Task(int id, int priority, int time, int orderAdded) {
                this.id = id;
                this.priority = priority;
                this.remainingTime = time;
                this.orderAdded = orderAdded;
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            // 使用优先队列，按优先级排序（值越小优先级越高）
            // 优先级相同时按添加顺序排序
            PriorityQueue<Task> taskQueue = new PriorityQueue<>(
                    (t1, t2) -> t1.priority != t2.priority ?
                            t1.priority - t2.priority :
                            t1.orderAdded - t2.orderAdded
            );

            int orderCount = 0;

            while (scanner.hasNext()) {
                String command = scanner.next();

                if ("add".equals(command)) {
                    int taskId = scanner.nextInt();
                    int priority = scanner.nextInt();
                    int time = scanner.nextInt();
                    taskQueue.offer(new Task(taskId, priority, time, orderCount++));
                } else if ("run".equals(command)) {
                    int timeSlices = scanner.nextInt();
                    runTaskScheduler(taskQueue, timeSlices);
                }
            }

            scanner.close();
        }

        private static void runTaskScheduler(PriorityQueue<Task> taskQueue, int timeSlices) {
            int remainingTimeSlices = timeSlices;

            while (remainingTimeSlices > 0 && !taskQueue.isEmpty()) {
                Task currentTask = taskQueue.poll();

                if (currentTask.remainingTime <= remainingTimeSlices) {
                    // 任务可以完成
                    remainingTimeSlices -= currentTask.remainingTime;
                    // 任务完成，不需要放回队列
                } else {
                    // 任务未完成
                    currentTask.remainingTime -= remainingTimeSlices;
                    taskQueue.offer(currentTask); // 放回队列
                    remainingTimeSlices = 0;
                }
            }

            // 输出下一个要执行的任务ID
            if (taskQueue.isEmpty()) {
                System.out.println("idle");
            } else {
                System.out.println(taskQueue.peek().id);
            }
        }
    }


}
