package com.example.MPI_Project.volume_tests;

import com.example.MPI_Project.testHelpers.PgClient;

import java.util.ArrayList;

public class VolumeTestDataFiller {
    public static void main(String[] args) throws InterruptedException {
        truncate("task");
        truncate("order");
        writeTestData(100000, 25, "task");
        writeTestData(100000, 25, "order");
    }

    private static void writeTestData(int num, int threadCount, String entityName) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<PgClient> pgClients = new ArrayList<>();

        int countRows = num/threadCount;

        for (int i=0; i<threadCount; i++){
            pgClients.add(new PgClient());
        }

        switch (entityName) {
            case "order":
                for (int i = 0; i < threadCount; i++) {
                    int pgClientId = i;
                    threads.add(new Thread(() -> pgClients.get(pgClientId).writeOrderTableVolumeTestData(countRows)));
                }
                break;
            case "task":
                for (int i=0; i<threadCount; i++) {
                    int pgClientId = i;
                    threads.add(new Thread(() -> pgClients.get(pgClientId).writeTaskTableVolumeTestData(countRows)));
                }
                break;
            default:
                return;
        }

        for (Thread thread: threads){
            thread.start();
        }
        for (Thread thread: threads){
            thread.join();
        }
        for (PgClient pgClient: pgClients){
            pgClient.close();
        }
    }

    private static void truncate(String entityName) {
        PgClient pgClient = new PgClient();
        switch (entityName) {
            case "task":
                pgClient.truncateTaskTable();
                break;
            case "order":
                pgClient.truncateOrderTable();
                break;
            default:
                break;
        }
        pgClient.close();
    }
}
