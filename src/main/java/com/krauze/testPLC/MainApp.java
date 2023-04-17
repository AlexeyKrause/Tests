package com.krauze.testPLC;

import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

public class MainApp {
    public static void main(String[] args) {
        String connectionString = "s7://192.168.1.101";

        try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionString)) {
            System.out.println("Connection OK");

            PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
            builder.addItem("ID1000", "%ID1000:DWORD");
            PlcReadRequest readRequest = builder.build();

//            CompletableFuture<? extends PlcReadResponse> asyncResponse = readRequest.execute();
//            asyncResponse.whenComplete((response, throwable) -> {
//                try {
//                    System.out.println(response.toString());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            });

            PlcReadResponse response = readRequest.execute().get();
            for (String fieldName : response.getFieldNames()) {
                if (response.getResponseCode(fieldName) == PlcResponseCode.OK) {
                    int numValues = response.getNumberOfValues(fieldName);
                    System.out.println(numValues);
                    System.out.println(response.getFieldNames());
                    System.out.println("Значение энкодера -"  + response.getPlcValue("ID1000"));
                }
            }

            if (!plcConnection.getMetadata().canWrite()) {
                System.out.println("Data can write!");
            }
            PlcWriteRequest.Builder builder1 = plcConnection.writeRequestBuilder();
            builder1.addItem("Q0.0", "%Q1.0:BOOL", true);
            System.out.println();
//            builder1.addItem("Q0.1", "%Q0.1:BOOL", false);
//            builder1.addItem("Q0.2", "%Q0.2:BOOL", true);
//            builder1.addItem("Q0.3", "%Q0.3:BOOL", false);
//            builder1.addItem("Q0.4", "%Q0.4:BOOL", true);
//            builder1.addItem("Q0.5", "%Q0.5:BOOL", false);
            PlcWriteRequest writeRequest = builder1.build();
            PlcWriteResponse response1 = writeRequest.execute().get();

        } catch (PlcConnectionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
