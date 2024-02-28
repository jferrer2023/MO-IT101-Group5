package motorph1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

//TEST PUSH GITHUB
public class MotorPH1 {
    private static final String[] usernames = {"user1", "user2", "user3"};
    private static final String[] passwords = {"123", "456", "789"};
    
    // Path to your attendance CSV file
    private static final String attendanceCsvFile = "C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Attendance Details.csv"; 
    private static final String csvSplitBy = ",";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String enteredUsername = "";
            String enteredPassword = "";
            
            while (true) {
                System.out.println("\nWELCOME TO MOTORPH");
                System.out.print("\nEnter username: ");
                enteredUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                enteredPassword = scanner.nextLine();

                int index = -1;
                for (int i = 0; i < usernames.length; i++) {
                    if (enteredUsername.equals(usernames[i]) && enteredPassword.equals(passwords[i])) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    // Authentication successful

                    boolean backToMainMenu = false;
                    boolean logout = false;

                    do {
                        System.out.println("\nMAIN MENU");
                        System.out.println("  Option [1] - Employee Personal Details");
                        System.out.println("  Option [2] - Employee Attendance");
                        System.out.println("  Option [3] - Employee Payroll Details");
                        System.out.println("  Option [4] - Logout");

                        System.out.print("\nPlease enter option: ");
                        int optionNumber = scanner.nextInt();
                        scanner.nextLine();

                        boolean repeatCurrentOption = false;
                        switch (optionNumber) {
                            case 1:
                                System.out.println("\nEmployee Personal Details:");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber = scanner.nextLine();
                                displayEmployeeDetails("C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Employee Details.csv", inputEmployeeNumber);
                                break;
                            case 2:
                                System.out.println("\nEmployee Attendance:");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber2 = scanner.nextLine();
                                System.out.print("Enter Month:");
                                String inputMonth = scanner.nextLine();
                                System.out.print("Enter Week:(Format- dd-mmm-yy:e.g. 03-jan-22):");
                                String inputWeek = scanner.nextLine();
                                displayEmployeeAttendance(inputEmployeeNumber2, inputMonth, inputWeek);
                                break;
                            case 3:
                                System.out.println("\nEmployee Payroll Details");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber3 = scanner.nextLine();
                                System.out.print("Enter Month:");
                                String inputMonth2 = scanner.nextLine();
                                System.out.print("Enter Week:(Format- dd-mmm-yy:e.g. 03-jan-22):");
                                String inputWeek2 = scanner.nextLine();
                                displayEmployeeAttendancePayroll(inputEmployeeNumber3, inputMonth2, inputWeek2);
                                break;
                            case 4:
                                System.out.println("\nExiting program...");
                                logout = true; 
                                break;
                            default:
                                System.out.println("\nOption not valid. Please enter a valid option.");
                                repeatCurrentOption = true;
                                break;
                    }

                        if (repeatCurrentOption) {
                            continue; // Repeat the current switch case
                        }

                        // Ask user if they want to go back to the main menu
                        if (!logout) {
                            System.out.print("\nBack to Main Menu? (Y/N): ");
                            String answer = scanner.nextLine();
                            backToMainMenu = answer.equalsIgnoreCase("Y");
                        }
                    } while (backToMainMenu && !logout); // Exit loop if user chooses "N" or if logout is true

                    // Ask for authentication variables after logout
                    enteredUsername = "";
                    enteredPassword = "";
                } else {
                    System.out.println("Wrong username/password. Please try again.");
                }
            }
        }
    }






    //-------------------OPTION 1 Data--------------------------------
    
    public static void displayEmployeeDetails(String csvFile, String employeeNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean employeeFound = false; // Flag to track if employee is found

        float basicSalary = 0;
        float riceSubsidy = 0;
        float phoneAllowance = 0;
        float clothingAllowance = 0;
        float grossSemiMonthRate = 0;
        float hourlyRate = 0;
        float minuteRate = 0;
      
            // Read and process CSV file
            while ((line = br.readLine()) != null) {
                String[] employeeData = line.split(csvSplitBy);

                // Check if the employee ID matches
                if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                    // Display employee details
                System.out.println("\nPERSONAL DETAILS:");
                System.out.println("  Employee Number: " + employeeData[0]);
                System.out.println("  Last Name: " + employeeData[1]);
                System.out.println("  First Name: " + employeeData[2]);
                System.out.println("  Birthdate: " + employeeData[3]);
                System.out.println("  Address: " + employeeData[4]);
                System.out.println("  Contact No.: " + employeeData[5]);
                System.out.println("  SSS No.: " + employeeData[6]);
                System.out.println("  Philhealth No.: " + employeeData[7]);
                System.out.println("  TIN No.: " + employeeData[8]);
                System.out.println("  Pagibig No.: " + employeeData[9]);
                System.out.println("  Status: " + employeeData[10]);
                System.out.println("  Position: " + employeeData[11]);
                System.out.println("  Immediate Supervisor: " + employeeData[12]);
                    // Display additional details as needed
                    
                basicSalary = Float.parseFloat(employeeData[13]);
                riceSubsidy = Float.parseFloat(employeeData[14]);
                phoneAllowance = Float.parseFloat(employeeData[15]);
                clothingAllowance = Float.parseFloat(employeeData[16]);
                grossSemiMonthRate = Float.parseFloat(employeeData[17]);
                hourlyRate = Float.parseFloat(employeeData[18]);
                minuteRate = Float.parseFloat(employeeData[19]);    
                    
                    
                 float gross = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance + grossSemiMonthRate + hourlyRate;
                
                System.out.println("\nSALARY DETAILS:");
                System.out.println("  Basic Salary: " + basicSalary);
                System.out.println("  Rice Subsidy: " + riceSubsidy);
                System.out.println("  Phone Allowance: " + phoneAllowance);
                System.out.println("  Clothing Allowance: " + clothingAllowance);
                System.out.println("  Gross Semi-monthly Rate: " + grossSemiMonthRate);
                System.out.println("  Hourly Rate: " + hourlyRate);
                System.out.println("  GROSS: " + gross);   
                    
                    
           
                    employeeFound = true; // Set flag to true
                    break; // Stop searching after finding the employee
                }
            }

            if (!employeeFound) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            
        }
    }

    
    //-------------------OPTION 2 Data--------------------------------
    
public static void displayEmployeeAttendance(String employeeNumber, String month, String week) {
    try (BufferedReader br = new BufferedReader(new FileReader(attendanceCsvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found
        double totalWorkHours = 0; // Use double for decimal values
        double attendanceCount = 0; // Counter for attendance entries

        // Read and process attendance CSV file
        while ((line = br.readLine()) != null) {
            String[] attendanceData = line.split(csvSplitBy);

            // Check if the attendance record matches employee ID, month, and week
            if (attendanceData.length > 9 && 
                attendanceData[0].trim().equals(employeeNumber.trim()) && 
                attendanceData[7].trim().equalsIgnoreCase(month.trim()) && 
                attendanceData[8].trim().equalsIgnoreCase(week.trim())) {
                System.out.println("  Last Name: " + attendanceData[1]);
                System.out.println("  First Name: " + attendanceData[2]);
                // Increment attendance count
                attendanceCount++;
                
             
             
                // Display attendance details
                System.out.println("\nATTENDANCE DETAILS:");
                System.out.println("  Employee Number: " + attendanceData[0]);
                
                System.out.println("  Date: " + attendanceData[3]);
                System.out.println("  Time In: " + attendanceData[4]);
                System.out.println("  Time Out: " + attendanceData[5]);
                
                // Calculate work hours as decimal
                LocalTime timeIn = LocalTime.parse(attendanceData[4]);
                LocalTime timeOut = LocalTime.parse(attendanceData[5]);
                Duration duration = Duration.between(timeIn, timeOut);
                double decimalHours = duration.toMinutes() / 60.0 - 1; // Convert minutes to decimal hours
                //System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours ) ;
               
                //Grace Period, absences and halfday
                if (decimalHours > 7.82) {
                decimalHours = 8.0;
                } else if (decimalHours <= 0) {
                 decimalHours = 0.0;
                } else if (decimalHours <= 4) {
                 decimalHours = decimalHours + 1;
                }
                
                
                System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours);

                // Accumulate total work hours
                totalWorkHours += decimalHours;

                // Display additional details as needed
                employeeFound = true; // Set flag to true
                
             
            }
        }
        
        

        if (employeeFound) {
            double AttendanceTotal = (attendanceCount * 8);
          
            System.out.println("\nTotal Attendance Entries:" + attendanceCount);
            System.out.printf("Total Hours Entries:%.2f hrs\n" , AttendanceTotal);
            System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
            double Tardiness = (AttendanceTotal - totalWorkHours);
            System.out.printf("Tardiness: %.2f hrs\n", Tardiness);

            
        } else {
            System.out.println("Attendance not found for the employee in the specified month and week.");
        }
    } catch (IOException e) {
        
        }
    }


    //-------------------OPTION 3 Data--------------------------------

  public static void displayEmployeeAttendancePayroll(String employeeNumber, String month, String week) {
    try (BufferedReader br = new BufferedReader(new FileReader(attendanceCsvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found
        double totalWorkHours = 0; // Use double for decimal values
        double attendanceCount = 0; // Counter for attendance entries

        float basicSalary = 0; // Initialize basicsalary rate 
        float riceSubsidy = 0; // Initialize riceSubsidy rate
        float phoneAllowance = 0; // Initialize phone allowance rate
        float clothingAllowance = 0; // Initialize clothing allowance rate
        float grossSemiMonthRate = 0; // Initialize clothing allowance rate
        float hourlyRate = 0; // Initialize hourly rate
        float taxRate = 0; 
        float sssRate = 0; 
        float philhealthRate = 0;        
        float pagibigRate = 0;
        float tardiness = 0;
        
        String employeeLastName = "";
        String employeeFirstName = "";
     
     
        // Read and process attendance CSV file
        while ((line = br.readLine()) != null) {
            String[] attendanceData = line.split(csvSplitBy);

            // Check if the attendance record matches employee ID, month, and week
            if (attendanceData.length > 9 && 
                attendanceData[0].trim().equals(employeeNumber.trim()) && 
                attendanceData[7].trim().equalsIgnoreCase(month.trim()) && 
                attendanceData[8].trim().equalsIgnoreCase(week.trim())) {
                
                // Increment attendance count
                attendanceCount++;
                
             
             
                /* Display attendance details
                System.out.println("\nATTENDANCE DETAILS:");
                System.out.println("  Employee Number: " + attendanceData[0]);
                
                System.out.println("  Date: " + attendanceData[3]);
                System.out.println("  Time In: " + attendanceData[4]);
                System.out.println("  Time Out: " + attendanceData[5]);*/
                
                // Calculate work hours as decimal
                LocalTime timeIn = LocalTime.parse(attendanceData[4]);
                LocalTime timeOut = LocalTime.parse(attendanceData[5]);
                Duration duration = Duration.between(timeIn, timeOut);
                double decimalHours = duration.toMinutes() / 60.0 - 1; // Convert minutes to decimal hours
                //System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours ) ;
               
                //Grace Period, absences and halfday
                if (decimalHours > 7.82) {
                decimalHours = 8.0;
                } else if (decimalHours <= 0) {
                 decimalHours = 0.0;
                } else if (decimalHours <= 4) {
                 decimalHours = decimalHours + 1;
                }
                //System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours);

                // Accumulate total work hours
                totalWorkHours += decimalHours;

                
                // Retrieve hourly rate from the employee details
                String employeeCsvFile = "C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Employee Details.csv";
                basicSalary = getbasicSalaryFromEmployeeDetails(employeeCsvFile, employeeNumber);
                riceSubsidy = getriceSubsidyFromEmployeeDetails(employeeCsvFile, employeeNumber);
                phoneAllowance = getphoneAllowanceFromEmployeeDetails(employeeCsvFile, employeeNumber);
                clothingAllowance = getclothingAllowanceFromEmployeeDetails(employeeCsvFile, employeeNumber);
                grossSemiMonthRate = getgrossSemiMonthRateFromEmployeeDetails(employeeCsvFile, employeeNumber);
                hourlyRate = gethourlyRateFromEmployeeDetails(employeeCsvFile, employeeNumber);
                
                
                employeeLastName = attendanceData[1].trim(); 
                employeeFirstName = attendanceData[2].trim(); 
                
                employeeFound = true; 
               //break;
            }
        }
                
             

        if (employeeFound) {
            if (totalWorkHours > 0) {
                float weeklyGross = hourlyRate * (float) totalWorkHours;
                float allowances = (riceSubsidy + phoneAllowance + clothingAllowance) / 4; 
        
    System.out.println("\nEmployee Name: " + employeeFirstName + " " + employeeLastName);
    System.out.println("\nEMPLOYEE RATE : ");  
    System.out.println("Monthly Basic : " + basicSalary + ", Weekly Basic: " + basicSalary / 4);  
    System.out.println("Monthly RiceSubsidy: " + riceSubsidy + ", Weekly RiceSubsidy: " + riceSubsidy / 4);  
    System.out.println("Monthly Phone Allowance: " + phoneAllowance + ", Weekly Phone Allowance: " + phoneAllowance / 4);
    System.out.println("Monthly Clothing Allowance : " + clothingAllowance + ", Weekly Clothing Allowance : " + clothingAllowance / 4);
   
    System.out.println("Gross Semi-Month Rate : " + grossSemiMonthRate);
    
    
    System.out.printf("\nEARNINGS:");
    System.out.printf("\nHourly Rate: %.2f hrs\n", hourlyRate);
    System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
    
    
    
    System.out.printf("\nWeekly GROSS (HourlyRate * HoursWorkedForTheWeek): %.2f\n", weeklyGross); //%.2f in the printf to print 2 decimal amount
    System.out.println("Weekly Allowances (riceSubsidy + phoneAllowance + clothingAllowance): " + allowances);
                
    float earnings = (weeklyGross + (allowances));  // + allowances     
                System.out.printf("\nTOTAL EARNINGS:%.2f\n", earnings);
    
                // ----------------DEDUCTIONS EE SHARE----------------
                System.out.printf("\nDEDUCTIONS (Employee Deductions & EE Share):");
                
                //Tardiness
             //   System.out.printf("\nTardiness: ");
                //tardiness
             double AttendanceTotal = (attendanceCount * 8);
             System.out.println("\nTotal Attendance Entries:" + attendanceCount);
             System.out.printf("Total Hours Entries:%.2f hrs\n" , AttendanceTotal);
             System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
             double Tardiness = (AttendanceTotal - totalWorkHours);
             System.out.printf("Tardiness: %.2f hrs \n", Tardiness);
             //System.out.println("Tardiness is already deducted from the Work Hours This Week");
                //Pagibig =(IF(basicSalary<=1500,(basicSalary*1%),(basicSalary*2%)))/4
              
                if (basicSalary <= 999) {
                pagibigRate = 0;
                } else if (basicSalary <= 1500) {
                pagibigRate = (float) (basicSalary * 0.01 / 4);
                } else if (basicSalary >= 1501) {
                pagibigRate = (float) (basicSalary * 0.02 / 4);
                }
                // Check if pagibigRate exceeds 100 after calculations
                if (pagibigRate >= 100) {
                pagibigRate = 25;
                }
                System.out.printf("\nPagibig Rate: %.2f\n", pagibigRate);
                

                //Philhealth 
                
                if (basicSalary > 0)   {
                philhealthRate = (float) (basicSalary * 0.03 / 4 / 2);
                }
                System.out.printf("Philhealth Rate: %.2f\n", philhealthRate);

                
                //SSS variable: sssRate
                
                if (basicSalary <= 21750) {
                sssRate = 990f / 4;
                } else if (basicSalary > 22250 && basicSalary <= 22750) {
                sssRate = 1012.50f / 4;
                } else if (basicSalary > 22750 && basicSalary <= 23250) {
                sssRate = 1035.00f / 4;
                } else if (basicSalary > 23250 && basicSalary <= 23750) {
                sssRate = 1057.50f /4;
                } else if (basicSalary > 23750 && basicSalary <= 24250) {
                sssRate = 1080.00f /4;
                } else if (basicSalary > 24250 && basicSalary <= 24750) {
                sssRate = 1102.50f /4;
                } else if (basicSalary < 24750) {
                sssRate = 1125.00f /4;
                }
                System.out.printf("SSS Rate: %.2f\n", sssRate);
                
                //Tax variable: taxRate
                
                if (basicSalary <= 20832) {
                taxRate = 0;
                } else if (basicSalary >= 20883 && basicSalary <= 33333) {
                taxRate = (float) (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 20833) * 0.20f / 4;
                } else if (basicSalary >= 33334 && basicSalary <= 66667) {
                taxRate = (float) (2500 + (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4) - 33333) * 0.25f) / 4;
                } else if (basicSalary >= 66668 && basicSalary <= 166667) {
                taxRate = (float) (10833 + (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 66667) * 0.30f) / 4;
                } else if (basicSalary >= 166668 && basicSalary <= 666667) {
                taxRate = (float) (40833.33 + basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4) - 166667) * 0.32f / 4;
                } else if (basicSalary >= 666667) {
                taxRate = (float) (200833.33 + basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 666667) * 0.35f / 4;
                }

                System.out.printf("Tax Rate: %.2f\n", taxRate);
   

                
                
                // ----------------TOTAL DEDUCTIONS ER SHARE----------------
                System.out.printf("\nDEDUCTIONS (ER Employer Share):");
                System.out.printf("\nPaibig Rate: %.2f\n", pagibigRate );
                System.out.printf("Philhealth Rate: %.2f\n", philhealthRate);
                System.out.printf("SSS Rate: %.2f\n", sssRate);
                
                // ----------------TOTAL DEDUCTIONS EE SHARE----------------
                float deductions = (pagibigRate + philhealthRate + taxRate + sssRate);  //+ sssRate + philhealthRate +  + taxRate;
                System.out.printf("\nTOTAL EMPLOYEE DEDUCTIONS :%.2f\n", deductions);
                
                // ----------------NetPay----------------
                float netPay = (earnings - deductions);  //+ sssRate + philhealthRate +  + taxRate;
                System.out.printf("\nNET PAY :%.2f\n", netPay);   

            } else {
                System.out.println("Employee found but no attendance recorded for the specified month and week.");
            }
        } else {
            System.out.println("Employee not found or no attendance recorded for the specified month and week.");
        }
    } catch (IOException e) {
       // e.printStackTrace();
    }
}
  



    //------------------- Payroll Wage Elements --------------------------------
  
public static float getbasicSalaryFromEmployeeDetails(String csvFile, String employeeNumber) {
    float basicSalary = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                basicSalary = Float.parseFloat(employeeData[13]); // Assuming hourly rate is at index 13
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return basicSalary;
    } 
  
  
 
public static float getriceSubsidyFromEmployeeDetails(String csvFile, String employeeNumber) {
    float riceSubsidy = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                riceSubsidy = Float.parseFloat(employeeData[14]); // Assuming hourly rate is at index 14
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return riceSubsidy;
    }


public static float getphoneAllowanceFromEmployeeDetails(String csvFile, String employeeNumber) {
    float phoneAllowance = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                phoneAllowance = Float.parseFloat(employeeData[15]); // Assuming hourly rate is at index 15
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return phoneAllowance;
    }

public static float getclothingAllowanceFromEmployeeDetails(String csvFile, String employeeNumber) {
    float clothingAllowance = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                clothingAllowance = Float.parseFloat(employeeData[16]); // Assuming hourly rate is at index 16
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return clothingAllowance;
    }



public static float getgrossSemiMonthRateFromEmployeeDetails(String csvFile, String employeeNumber) {
    float grossSemiMonthRate = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                grossSemiMonthRate = Float.parseFloat(employeeData[17]); // Assuming hourly rate is at index 17
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return grossSemiMonthRate;
    }



public static float gethourlyRateFromEmployeeDetails(String csvFile, String employeeNumber) {
    float hourlyRate = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                hourlyRate = Float.parseFloat(employeeData[18]); // Assuming hourly rate is at index 18
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return hourlyRate;
    }
}




