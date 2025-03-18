// package com.diamond.diamond.services;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class ProcessExecutor {
//     public static String executeScript() {
//         try {
//             ProcessBuilder processBuilder = new ProcessBuilder("node", "test.js");
//             processBuilder.redirectErrorStream(true);
            
//             Process process = processBuilder.start();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
//             StringBuilder output = new StringBuilder();
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 output.append(line);
//             }
            
//             int exitCode = process.waitFor();
//             if (exitCode != 0) {
//                 throw new RuntimeException("Python script execution failed with exit code: " + exitCode);
//             }
            
//             return output.toString().trim();
            
//         } catch (IOException | InterruptedException | RuntimeException e) {
//             throw new RuntimeException("Failed to execute script", e);
//         }
//     }
    
// }
