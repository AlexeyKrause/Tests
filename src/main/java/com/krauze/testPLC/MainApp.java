package com.krauze.testPLC;

import java.util.concurrent.ExecutionException;

public class MainApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String connectionString = "192.168.1.101";

        boolean statusConnection = PlcConnect.createConnection(connectionString);

        System.out.println(PlcConnect.readData("ID1000", "DWORD"));

        System.out.println(PlcConnect.readData("Q1.0", "BOOL"));
        PlcConnect.writeData("Q1.0", "BOOL", "false");
        System.out.println(PlcConnect.readData("Q1.0", "BOOL"));

        System.out.println(PlcConnect.readData("Q1.1", "BOOL"));
        PlcConnect.writeData("Q1.1", "BOOL", "false");
        System.out.println(PlcConnect.readData("Q1.1", "BOOL"));

        PlcConnect.closeConnection();
//        plcConnect.writeData("Q0.0", "BOOL", "false");
//        plcConnect.writeData("Q0.1", "BOOL", "false");
//        plcConnect.writeData("Q0.2", "BOOL", "false");
//        plcConnect.writeData("Q0.3", "BOOL", "false");

//        String connectionString = "s7://192.168.1.101";
//        try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionString)) {
//            System.out.println("Connection OK");
//
//            PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
//            builder.addItem("ID1000", "%ID1000:DWORD");
//            PlcReadRequest readRequest = builder.build();
//
//            PlcReadResponse response = readRequest.execute().get();
//            for (String fieldName : response.getFieldNames()) {
//                if (response.getResponseCode(fieldName) == PlcResponseCode.OK) {
//                    int numValues = response.getNumberOfValues(fieldName);
////                    System.out.println(numValues);
////                    System.out.println(response.getFieldNames());
//                    System.out.println("Значение энкодера -"  + response.getPlcValue("ID1000"));
//                }
//            }
//
//            if (!plcConnection.getMetadata().canWrite()) {
//                System.out.println("Data can write!");
//            }
//            PlcWriteRequest.Builder builder1 = plcConnection.writeRequestBuilder();
//            builder1.addItem("Q0.0", "%Q1.0:BOOL", true);
//            System.out.println();
//
//            PlcWriteRequest writeRequest = builder1.build();
//            PlcWriteResponse response1 = writeRequest.execute().get();
//            System.out.println("end");
//        } catch (PlcConnectionException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
