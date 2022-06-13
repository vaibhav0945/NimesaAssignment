import processor.DataProcessor;
import userdefinedclass.OutputData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {


        public static void main(String[] args) throws IOException, InterruptedException {
            final String URL  = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
            DataProcessor dataProcessor = new DataProcessor(URL);
            String inputJsonString = dataProcessor.fetchInputData();
            Map<String, OutputData> dateOutputMap = dataProcessor.preProcessData(inputJsonString);

            ///looping condition
            while (true) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Get the information by pressing the input of choice: ");
                System.out.println("Press 1 for Weather Info");
                System.out.println("Press 2 Wind Speed Info");
                System.out.println("Press 3 Pressure Info");
                System.out.println("Press 0 to Exit");
                System.out.println("Enter your choice : ");
                int n = Integer.parseInt(bf.readLine());
                if (n == 0) {
                    System.out.println("Exiting Now...");
                    break;
                } else {
                    try {
                        if (n == 1) {
                            System.out.println("Enter a date : ");
                            String date = bf.readLine();
                            System.out.println("Temperature is : " + dateOutputMap.get(date).getTemp() + "\n");
                        } else if (n == 2) {
                            System.out.println("Enter a date : ");
                            String date = bf.readLine();
                            System.out.println("Speed of wind is : " + dateOutputMap.get(date).getSpeed() + "\n");
                        } else if (n == 3) {
                            System.out.println("Enter a date : ");
                            String date = bf.readLine();
                            System.out.println("Pressure is : " + dateOutputMap.get(date).getPressure() + "\n");
                        } else {
                            System.out.println("Enter a valid choice" + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter a valid date" + "\n");
                    }
                }
            }
        }
    }
