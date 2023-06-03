package com.krauze.testPLC;

import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;

import java.util.concurrent.ExecutionException;

public class PlcConnect {
    private static PlcConnection connection;


    public static boolean createConnection(String ip) {
        try {
            connection = new PlcDriverManager().getConnection("s7://" + ip);
            System.out.println("Connection OK");
            return true;
        } catch (PlcConnectionException e) {
            System.out.println("Connect not OK!");
            return false;
        }
    }


    public static PlcConnection getConnection() {
        return connection;
    }


    public static String readData(String dataAddress, String dataType) throws ExecutionException, InterruptedException {
        String result = "";
        PlcReadRequest.Builder builder = connection.readRequestBuilder();
        builder.addItem(dataAddress, "%" + dataAddress + ":" + dataType);
        PlcReadRequest readRequest = builder.build();

        PlcReadResponse response = readRequest.execute().get();
        for (String fieldName : response.getFieldNames()) {
            if (response.getResponseCode(fieldName) == PlcResponseCode.OK) {
                int numValues = response.getNumberOfValues(fieldName);
                result = response.getPlcValue(dataAddress).toString();
//                System.out.println("Значение энкодера -"  + response.getPlcValue(dataAddress));
            }
        }
        return result;
    }


    public static void writeData(String dataAddress, String dataType, String value) throws ExecutionException, InterruptedException {
        PlcWriteRequest.Builder builder = connection.writeRequestBuilder();
        builder.addItem("", "%" + dataAddress + ":" + dataType, value);
        PlcWriteRequest writeRequest = builder.build();
        PlcWriteResponse response1 = writeRequest.execute().get();
    }


    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionString)) {
//            System.out.println("Connection OK");
//
//            PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
//            builder.addItem("ID1000", "%ID1000:DWORD");
//            PlcReadRequest readRequest = builder.build();
//
//            if (!plcConnection.getMetadata().canWrite()) {
//                System.out.println("Data can write!");
//            }
//            PlcWriteRequest.Builder builder1 = plcConnection.writeRequestBuilder();
//            builder1.addItem("Q0.0", "%Q1.0:BOOL", true);
//            PlcWriteRequest writeRequest = builder1.build();
//            PlcWriteResponse response1 = writeRequest.execute().get();
//
//        } catch (PlcConnectionException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
